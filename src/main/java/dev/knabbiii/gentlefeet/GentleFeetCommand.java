package dev.knabbiii.gentlefeet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command handler for GentleFeet plugin
 */
public class GentleFeetCommand implements CommandExecutor, TabCompleter {
    
    private final GentleFeet plugin;
    
    public GentleFeetCommand(GentleFeet plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("gentlefeet")) {
            return false;
        }
        
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        
        String subCommand = args[0].toLowerCase();
        
        switch (subCommand) {
            case "reload":
                return handleReload(sender);
            case "set":
                return handleSet(sender, args);
            case "toggle":
                return handleToggle(sender, args);
            case "info":
                return handleInfo(sender);
            case "help":
                sendHelp(sender);
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command. Use /gentlefeet help for available commands.");
                return true;
        }
    }
    
    private boolean handleReload(CommandSender sender) {
        if (!sender.hasPermission("gentlefeet.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to reload the plugin.");
            return true;
        }
        
        plugin.reloadPluginConfig();
        sender.sendMessage(ChatColor.GREEN + "GentleFeet configuration reloaded successfully!");
        return true;
    }
    
    private boolean handleSet(CommandSender sender, String[] args) {
        if (!sender.hasPermission("gentlefeet.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to modify settings.");
            return true;
        }
        
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: /gentlefeet set <setting> <value>");
            sender.sendMessage(ChatColor.YELLOW + "Available settings: featherfall-chance, max-level");
            return true;
        }
        
        String setting = args[1].toLowerCase();
        String value = args[2];
        
        switch (setting) {
            case "featherfall-chance":
            case "chance":
                return setFeatherFallChance(sender, value);
            case "max-level":
            case "maxlevel":
                return setMaxLevel(sender, value);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown setting: " + setting);
                sender.sendMessage(ChatColor.YELLOW + "Available settings: featherfall-chance, max-level");
                return true;
        }
    }
    
    private boolean handleToggle(CommandSender sender, String[] args) {
        if (!sender.hasPermission("gentlefeet.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to modify settings.");
            return true;
        }
        
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /gentlefeet toggle <setting>");
            sender.sendMessage(ChatColor.YELLOW + "Available settings: barefoot, featherfall, notify-players, debug, metrics");
            return true;
        }
        
        String setting = args[1].toLowerCase();
        
        switch (setting) {
            case "barefoot":
                return toggleBarefootProtection(sender);
            case "featherfall":
            case "feather-fall":
                return toggleFeatherFall(sender);
            case "notify-players":
            case "notify":
                return toggleNotifyPlayers(sender);
            case "debug":
                return toggleDebug(sender);
            case "metrics":
                return toggleMetrics(sender);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown setting: " + setting);
                sender.sendMessage(ChatColor.YELLOW + "Available settings: barefoot, featherfall, notify-players, debug, metrics");
                return true;
        }
    }
    
    private boolean handleInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== GentleFeet Configuration ===");
        sender.sendMessage(ChatColor.YELLOW + "Barefoot Protection: " + (Config.enableBarefootProtection ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
        sender.sendMessage(ChatColor.YELLOW + "Feather Falling: " + (Config.enableFeatherFalling ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
        sender.sendMessage(ChatColor.YELLOW + "Feather Fall Chance: " + ChatColor.WHITE + Config.featherFallingChancePerLevel + "% per level");
        sender.sendMessage(ChatColor.YELLOW + "Max Feather Fall Level: " + ChatColor.WHITE + Config.maxFeatherFallingLevel);
        sender.sendMessage(ChatColor.YELLOW + "Notify Players: " + (Config.notifyPlayers ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
        sender.sendMessage(ChatColor.YELLOW + "Debug Mode: " + (Config.debugMode ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
        sender.sendMessage(ChatColor.YELLOW + "Metrics Collection: " + (Config.enableMetrics ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
        return true;
    }
    
    private boolean setFeatherFallChance(CommandSender sender, String value) {
        try {
            int chance = Integer.parseInt(value);
            if (chance < 1 || chance > 100) {
                sender.sendMessage(ChatColor.RED + "Feather fall chance must be between 1 and 100.");
                return true;
            }
            
            Config.featherFallingChancePerLevel = chance;
            plugin.getConfig().set("feather-falling.chance-per-level", chance);
            plugin.saveConfig();
            
            sender.sendMessage(ChatColor.GREEN + "Feather fall chance set to " + chance + "% per level.");
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid number: " + value);
            return true;
        }
    }
    
    private boolean setMaxLevel(CommandSender sender, String value) {
        try {
            int level = Integer.parseInt(value);
            if (level < 1 || level > 10) {
                sender.sendMessage(ChatColor.RED + "Max level must be between 1 and 10.");
                return true;
            }
            
            Config.maxFeatherFallingLevel = level;
            plugin.getConfig().set("feather-falling.max-level", level);
            plugin.saveConfig();
            
            sender.sendMessage(ChatColor.GREEN + "Max feather fall level set to " + level + ".");
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid number: " + value);
            return true;
        }
    }
    
    private boolean toggleBarefootProtection(CommandSender sender) {
        Config.enableBarefootProtection = !Config.enableBarefootProtection;
        plugin.getConfig().set("barefoot.enable", Config.enableBarefootProtection);
        plugin.saveConfig();
        
        String status = Config.enableBarefootProtection ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
        sender.sendMessage(ChatColor.YELLOW + "Barefoot protection " + status + ChatColor.YELLOW + ".");
        return true;
    }
    
    private boolean toggleFeatherFall(CommandSender sender) {
        Config.enableFeatherFalling = !Config.enableFeatherFalling;
        plugin.getConfig().set("feather-falling.enable", Config.enableFeatherFalling);
        plugin.saveConfig();
        
        String status = Config.enableFeatherFalling ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
        sender.sendMessage(ChatColor.YELLOW + "Feather falling protection " + status + ChatColor.YELLOW + ".");
        return true;
    }
    
    private boolean toggleNotifyPlayers(CommandSender sender) {
        Config.notifyPlayers = !Config.notifyPlayers;
        plugin.getConfig().set("debug.notify-players", Config.notifyPlayers);
        plugin.saveConfig();
        
        String status = Config.notifyPlayers ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
        sender.sendMessage(ChatColor.YELLOW + "Player notifications " + status + ChatColor.YELLOW + ".");
        return true;
    }
    
    private boolean toggleDebug(CommandSender sender) {
        Config.debugMode = !Config.debugMode;
        plugin.getConfig().set("debug.enable", Config.debugMode);
        plugin.saveConfig();
        
        String status = Config.debugMode ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
        sender.sendMessage(ChatColor.YELLOW + "Debug mode " + status + ChatColor.YELLOW + ".");
        return true;
    }
    
    private boolean toggleMetrics(CommandSender sender) {
        Config.enableMetrics = !Config.enableMetrics;
        plugin.getConfig().set("metrics.enable", Config.enableMetrics);
        plugin.saveConfig();
        
        String status = Config.enableMetrics ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled";
        sender.sendMessage(ChatColor.YELLOW + "Metrics collection " + status + ChatColor.YELLOW + ".");
        sender.sendMessage(ChatColor.GRAY + "Note: Restart required for this change to take effect.");
        return true;
    }
    
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== GentleFeet Commands ===");
        sender.sendMessage(ChatColor.YELLOW + "/gentlefeet info " + ChatColor.WHITE + "- Show current configuration");
        
        if (sender.hasPermission("gentlefeet.admin")) {
            sender.sendMessage(ChatColor.YELLOW + "/gentlefeet reload " + ChatColor.WHITE + "- Reload configuration");
            sender.sendMessage(ChatColor.YELLOW + "/gentlefeet toggle <setting> " + ChatColor.WHITE + "- Toggle a setting");
            sender.sendMessage(ChatColor.GRAY + "  Settings: barefoot, featherfall, notify-players, debug, metrics");
            sender.sendMessage(ChatColor.YELLOW + "/gentlefeet set <setting> <value> " + ChatColor.WHITE + "- Set a value");
            sender.sendMessage(ChatColor.GRAY + "  Settings: featherfall-chance (1-100), max-level (1-10)");
        }
        
        sender.sendMessage(ChatColor.GOLD + "Prevents your tootsies trampling crops!");
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> subCommands = Arrays.asList("info", "help");
            if (sender.hasPermission("gentlefeet.admin")) {
                subCommands = Arrays.asList("reload", "set", "toggle", "info", "help");
            }
            return subCommands.stream()
                    .filter(cmd -> cmd.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (args.length == 2 && sender.hasPermission("gentlefeet.admin")) {
            if (args[0].equalsIgnoreCase("toggle")) {
                return Arrays.asList("barefoot", "featherfall", "notify-players", "debug", "metrics").stream()
                        .filter(cmd -> cmd.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(Collectors.toList());
            } else if (args[0].equalsIgnoreCase("set")) {
                return Arrays.asList("featherfall-chance", "max-level").stream()
                        .filter(cmd -> cmd.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        
        if (args.length == 3 && sender.hasPermission("gentlefeet.admin") && args[0].equalsIgnoreCase("set")) {
            if (args[1].equalsIgnoreCase("featherfall-chance")) {
                return Arrays.asList("25", "50", "75", "100");
            } else if (args[1].equalsIgnoreCase("max-level")) {
                return Arrays.asList("1", "2", "3", "4", "5");
            }
        }
        
        return null;
    }
}