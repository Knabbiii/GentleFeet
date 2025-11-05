package dev.knabbiii.gentlefeet;

/**
 * Configuration class for CropGuard plugin.
 * 
 * Handles all configurable options including permissions, feather falling mechanics,
 * and crop protection settings.
 */
public class Config {
    
    // =====================
    // === MAIN SETTINGS ===
    // =====================
    
    /**
     * Should the plugin require the player to have the "cropguard.use" permission?
     * Default: true
     */
    public static boolean usePermissions = true;
    
    /**
     * Should the plugin require the player to have the "cropguard.featherfall" permission 
     * to benefit from feather falling crop protection?
     * Default: true
     */
    public static boolean useFeatherFallPermissions = true;
    
    // ===========================
    // === FEATHER FALL SETTINGS ===
    // ===========================
    
    /**
     * Enable feather falling enchantment crop protection mechanics.
     * When enabled, each level of feather falling provides a percentage chance
     * to avoid destroying crops when jumping on them.
     * Default: true
     */
    public static boolean enableFeatherFalling = true;
    
    /**
     * Percentage chance per feather falling level to avoid crop destruction.
     * For example: 25 means 25% per level (Level IV = 100% protection)
     * Valid range: 1-100
     * Default: 25
     */
    public static int featherFallingChancePerLevel = 25;
    
    /**
     * Maximum feather falling level to consider for crop protection.
     * Levels beyond this will be treated as this maximum.
     * Default: 4 (matches vanilla max enchantment level)
     */
    public static int maxFeatherFallingLevel = 4;
    
    // ========================
    // === BAREFOOT SETTINGS ===
    // ========================
    
    /**
     * Protect crops when players are not wearing boots (barefoot protection).
     * This is the original FineFeet functionality.
     * Default: true
     */
    public static boolean enableBarefootProtection = true;
    
    // ======================
    // === DEBUG SETTINGS ===
    // ======================
    
    /**
     * Enable debug messages for troubleshooting.
     * Shows detailed information about crop protection events in console.
     * Default: false
     */
    public static boolean debugMode = false;
    
    /**
     * Send protection messages to players when crops are protected.
     * Default: false
     */
    public static boolean notifyPlayers = false;
    
    /**
     * Enable bStats metrics collection.
     * Default: true
     */
    public static boolean enableMetrics = true;
    
    // =======================
    // === MESSAGE SETTINGS ===
    // =======================
    
    /**
     * Message sent to players when barefoot protection saves crops.
     * Set to empty string to disable message.
     * Supports color codes with &
     */
    public static String barefootProtectionMessage = "&7Your gentle steps protected the crops!";
    
    /**
     * Message sent to players when feather falling protection saves crops.
     * Set to empty string to disable message.
     * Supports color codes with &
     * {level} will be replaced with the feather falling level
     */
    public static String featherFallProtectionMessage = "&7Your feather falling boots (Level {level}) protected the crops!";
}