# GentleFeet

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20--1.21.10-green.svg)](https://minecraft.net/)

**Prevents your tootsies trampling crops, if you are not wearing boots!**

## 🌾 Features

### Core Protection
- **Barefoot Protection**: Prevents crop trampling when players aren't wearing boots (original FineFeet functionality)
- **Feather Falling Enhancement**: Each level of feather falling provides a 25% chance (configurable) to avoid crop destruction
- **Full Compatibility**: Works with Spigot, Paper, Purpur, and all Bukkit forks
- **Modern Support**: Supports Minecraft versions 1.20.0 through 1.21.10

### Advanced Configuration
- **Granular Permissions**: Separate permissions for barefoot protection and feather falling mechanics
- **Customizable Mechanics**: Adjust feather falling protection percentages and maximum levels
- **Debug Mode**: Comprehensive logging for troubleshooting
- **Player Notifications**: Optional messages when crops are protected
- **Performance Optimized**: Lightweight with minimal server impact

## 📋 Requirements

- **Java 21** or higher
- **Bukkit/Spigot/Paper** 1.20.0 - 1.21.10
- **Gradle 8.10.2** (for building from source)

## 🚀 Installation

1. Download the latest `GentleFeet.jar` from the releases page
2. Place the jar file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/GentleFeet/config.yml` (optional)

## ⚙️ Configuration

The plugin creates a `config.yml` file with the following options:

```yaml
# === PERMISSIONS ===
permissions:
  use-permissions: true              # Require cropguard.use permission for barefoot protection
  use-featherfall-permissions: true # Require cropguard.featherfall permission for feather falling

# === FEATHER FALL SETTINGS ===
feather-falling:
  enable: true           # Enable feather falling crop protection
  chance-per-level: 25   # Percentage chance per level (25% = Level IV gives 100% protection)
  max-level: 4          # Maximum enchantment level to consider

# === BAREFOOT SETTINGS ===
barefoot:
  enable: true          # Enable barefoot crop protection (original FineFeet functionality)

# === DEBUG SETTINGS ===
debug:
  enable: false         # Enable debug logging
  notify-players: false # Send protection messages to players

# === MESSAGE SETTINGS ===
messages:
  barefoot-protection: "&7Your gentle steps protected the crops!"
  featherfall-protection: "&7Your feather falling boots (Level {level}) protected the crops!"
```

## 🔑 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `cropguard.use` | Allows barefoot crop protection | `true` |
| `cropguard.featherfall` | Allows feather falling crop protection | `true` |
| `cropguard.admin` | Administrative permissions | `op` |

## 🎮 How It Works

### Barefoot Protection (Original FineFeet)
- When a player without boots steps on farmland, trampling is prevented
- Requires `cropguard.use` permission (if enabled in config)

### Feather Falling Protection (New Feature)
- Each level of feather falling on boots provides a percentage chance to avoid crop trampling
- Default: 25% per level (Level I = 25%, Level II = 50%, Level III = 75%, Level IV = 100%)
- Requires `cropguard.featherfall` permission (if enabled in config)
- Works independently of barefoot protection

## 🏗️ Building from Source

```bash
git clone https://github.com/knabbiii/GentleFeet.git
cd GentleFeet
./gradlew build
```

The compiled jar will be in `build/libs/GentleFeet-2.0.0.jar`

## 📊 Statistics

This plugin uses bStats to collect anonymous usage statistics. You can opt-out by setting `enabled` to `false` in `plugins/bStats/config.yml`.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Original Credit
Original concept and idea credit: **sammyt291** (FineFeet plugin)

## 🔗 Links

- [Issues](https://github.com/knabbiii/GentleFeet/issues)
- [Releases](https://github.com/knabbiii/GentleFeet/releases)
- [bStats](https://bstats.org/plugin/bukkit/GentleFeet)

## 📈 Changelog

### 2.0.0
- Complete rewrite and rebrand from FineFeet
- Added feather falling enchantment mechanics (25% chance per level)
- Upgraded to Java 21 and Gradle build system
- Added comprehensive configuration system
- Updated for Minecraft 1.20-1.21.10 support
- Added MIT license for commercial use
- Enhanced permissions system
- Added debug mode and player notifications

---

Made with ❤️ for the Minecraft community
