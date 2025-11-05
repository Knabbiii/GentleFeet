package dev.knabbiii.gentlefeet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * GentleFeet - Prevents your tootsies trampling crops, if you are not wearing boots!
 * 
 * Features:
 * - Barefoot protection (original FineFeet functionality)
 * - Feather falling enchantment provides percentage-based crop protection
 * - Configurable permissions and mechanics
 * - Compatible with Spigot, Paper, Purpur and all Bukkit forks
 * - Supports Minecraft 1.20 - 1.21.10
 * 
 * Original concept credit: sammyt291 (FineFeet)
 * 
 * @author knabbiii
 * @version 1.0
 */
public final class GentleFeet extends JavaPlugin implements Listener {
    
    private static GentleFeet instance;
    private Random random;
    
    @Override
    public void onEnable() {
        instance = this;
        this.random = new Random();
        
        // Initialize configuration
        saveDefaultConfig();
        loadConfigValues();
        
        // Initialize metrics (only if enabled)
        if (Config.enableMetrics) {
            Metrics metrics = new Metrics(this, 27864);
        }
        
        // Register events
        getServer().getPluginManager().registerEvents(this, this);
        
        // Register commands
        GentleFeetCommand commandHandler = new GentleFeetCommand(this);
        getCommand("gentlefeet").setExecutor(commandHandler);
        getCommand("gentlefeet").setTabCompleter(commandHandler);
        
        // Startup message
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "[" + 
            ChatColor.GOLD + "GentleFeet" + ChatColor.WHITE + "] " + 
            ChatColor.GREEN + "enabled v" + getDescription().getVersion());
        
        if (Config.debugMode) {
            getLogger().info("Debug mode enabled");
            getLogger().info("Barefoot protection: " + Config.enableBarefootProtection);
            getLogger().info("Feather falling protection: " + Config.enableFeatherFalling);
            getLogger().info("Feather falling chance per level: " + Config.featherFallingChancePerLevel + "%");
        }
    }
    
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "[" + 
            ChatColor.GOLD + "GentleFeet" + ChatColor.WHITE + "] " + 
            ChatColor.RED + "disabled");
    }
    
    /**
     * Reload the plugin configuration
     */
    public void reloadPluginConfig() {
        loadConfigValues();
        getLogger().info("Configuration reloaded");
    }
    
    /**
     * Load configuration values into the Config class
     */
    private void loadConfigValues() {
        reloadConfig();
        
        Config.usePermissions = getConfig().getBoolean("permissions.use-permissions", true);
        Config.useFeatherFallPermissions = getConfig().getBoolean("permissions.use-featherfall-permissions", true);
        
        Config.enableFeatherFalling = getConfig().getBoolean("feather-falling.enable", true);
        Config.featherFallingChancePerLevel = Math.max(1, Math.min(100, 
            getConfig().getInt("feather-falling.chance-per-level", 25)));
        Config.maxFeatherFallingLevel = Math.max(1, 
            getConfig().getInt("feather-falling.max-level", 4));
            
        Config.enableBarefootProtection = getConfig().getBoolean("barefoot.enable", true);
        
        Config.debugMode = getConfig().getBoolean("debug.enable", false);
        Config.notifyPlayers = getConfig().getBoolean("debug.notify-players", false);
        Config.enableMetrics = getConfig().getBoolean("metrics.enable", true);
        
        Config.barefootProtectionMessage = ChatColor.translateAlternateColorCodes('&',
            getConfig().getString("messages.barefoot-protection", "&7Your gentle steps protected the crops!"));
        Config.featherFallProtectionMessage = ChatColor.translateAlternateColorCodes('&',
            getConfig().getString("messages.featherfall-protection", "&7Your feather falling boots (Level {level}) protected the crops!"));
    }
    
    /**
     * Handle player physical interaction with blocks (trampling)
     */
    @EventHandler
    public void onPlayerTramplingCrops(PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) {
            return;
        }
        
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        
        // Validate block is farmland
        if (block == null || block.getType() != Material.FARMLAND) {
            return;
        }
        
        if (Config.debugMode) {
            getLogger().info("Player " + player.getName() + " stepped on farmland at " + 
                block.getLocation().toString());
        }
        
        ItemStack boots = player.getInventory().getBoots();
        boolean hasBoots = boots != null && boots.getType() != Material.AIR;
        
        // Check barefoot protection first
        if (!hasBoots && Config.enableBarefootProtection) {
            if (handleBarefootProtection(player, event)) {
                return;
            }
        }
        
        // Check feather falling protection if player has boots
        if (hasBoots && Config.enableFeatherFalling) {
            handleFeatherFallingProtection(player, boots, event);
        }
    }
    
    /**
     * Handle barefoot protection (original FineFeet functionality)
     */
    private boolean handleBarefootProtection(Player player, PlayerInteractEvent event) {
        // Check permissions
        if (Config.usePermissions && !player.hasPermission("gentlefeet.use")) {
            if (Config.debugMode) {
                getLogger().info("Player " + player.getName() + " lacks gentlefeet.use permission");
            }
            return false;
        }
        
        // Cancel the trampling
        event.setCancelled(true);
        
        if (Config.debugMode) {
            getLogger().info("Barefoot protection activated for " + player.getName());
        }
        
        // Send notification if enabled
        if (Config.notifyPlayers && !Config.barefootProtectionMessage.isEmpty()) {
            player.sendMessage(Config.barefootProtectionMessage);
        }
        
        return true;
    }
    
    /**
     * Handle feather falling enchantment protection
     */
    private void handleFeatherFallingProtection(Player player, ItemStack boots, PlayerInteractEvent event) {
        // Check permissions
        if (Config.useFeatherFallPermissions && !player.hasPermission("gentlefeet.featherfall")) {
            if (Config.debugMode) {
                getLogger().info("Player " + player.getName() + " lacks gentlefeet.featherfall permission");
            }
            return;
        }
        
        // Get feather falling level
        int featherFallLevel = boots.getEnchantmentLevel(Enchantment.FEATHER_FALLING);
        
        if (featherFallLevel <= 0) {
            if (Config.debugMode) {
                getLogger().info("Player " + player.getName() + " has boots but no feather falling enchantment");
            }
            return;
        }
        
        // Cap the level to configured maximum
        int effectiveLevel = Math.min(featherFallLevel, Config.maxFeatherFallingLevel);
        
        // Calculate protection chance
        int protectionChance = effectiveLevel * Config.featherFallingChancePerLevel;
        protectionChance = Math.min(100, protectionChance); // Cap at 100%
        
        // Roll for protection
        boolean isProtected = random.nextInt(100) < protectionChance;
        
        if (Config.debugMode) {
            getLogger().info("Player " + player.getName() + " feather falling level: " + featherFallLevel + 
                " (effective: " + effectiveLevel + "), protection chance: " + protectionChance + 
                "%, protected: " + isProtected);
        }
        
        if (isProtected) {
            // Cancel the trampling
            event.setCancelled(true);
            
            // Send notification if enabled
            if (Config.notifyPlayers && !Config.featherFallProtectionMessage.isEmpty()) {
                String message = Config.featherFallProtectionMessage.replace("{level}", 
                    String.valueOf(featherFallLevel));
                player.sendMessage(message);
            }
        }
    }
    
    /**
     * Get the plugin instance
     */
    public static GentleFeet getInstance() {
        return instance;
    }
}