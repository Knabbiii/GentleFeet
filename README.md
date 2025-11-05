# GentleFeet

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20--1.21-green.svg)](https://minecraft.net/)
[![bStats](https://img.shields.io/badge/bStats-27864-blue.svg)](https://bstats.org/plugin/bukkit/GentleFeet/27864)

**Prevents your tootsies trampling crops, if you are not wearing boots!**

## 🌾 Features

### Core Protection
- **Barefoot Protection**: Prevents crop trampling when players aren't wearing boots (original FineFeet functionality)
- **Feather Falling Enhancement**: Each level of feather falling provides configurable protection chance (default 25% per level)
- **Full Compatibility**: Works with Spigot, Paper, Purpur, and all Bukkit forks
- **Modern Support**: Supports Minecraft versions 1.20 through 1.21

### Command System
- **Live Configuration**: Modify settings without server restart
- **Admin Commands**: `/gentlefeet reload`, `toggle`, `set`, `info`
- **Tab Completion**: Full command completion support
- **Permission System**: Granular control over features

### Advanced Configuration
- **Granular Permissions**: Separate permissions for barefoot protection and feather falling mechanics
- **Customizable Mechanics**: Adjust feather falling protection percentages and maximum levels
- **Debug Mode**: Comprehensive logging for troubleshooting
- **Player Notifications**: Optional messages when crops are protected
- **Optional Metrics**: bStats integration can be disabled

## 📋 Requirements

- **Java 21** or higher
- **Bukkit/Spigot/Paper** 1.20 - 1.21
- **Gradle 8.11** (for building from source)

## 🚀 Installation

1. Download the latest `GentleFeet.jar` from the releases page
2. Place the jar file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/GentleFeet/config.yml` (optional)

## 🎮 Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/gentlefeet info` | `gentlefeet.use` | Show current configuration |
| `/gentlefeet reload` | `gentlefeet.admin` | Reload configuration |
| `/gentlefeet toggle <setting>` | `gentlefeet.admin` | Toggle features (barefoot, featherfall, notify-players, debug, metrics) |
| `/gentlefeet set <setting> <value>` | `gentlefeet.admin` | Set values (featherfall-chance 1-100, max-level 1-10) |

## 🔑 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `gentlefeet.use` | Allows barefoot crop protection & info command | `true` |
| `gentlefeet.featherfall` | Allows feather falling crop protection | `true` |
| `gentlefeet.admin` | Administrative permissions for all commands | `op` |

## ⚙️ Configuration

The plugin creates a `config.yml` file with the following options:

```yaml
# === PERMISSIONS ===
permissions:
  use-permissions: true              # Require gentlefeet.use permission
  use-featherfall-permissions: true # Require gentlefeet.featherfall permission

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

# === METRICS SETTINGS ===
metrics:
  enable: true          # Enable bStats metrics collection

# === MESSAGE SETTINGS ===
messages:
  barefoot-protection: "&7Your gentle steps protected the crops!"
  featherfall-protection: "&7Your feather falling boots (Level {level}) protected the crops!"
```

## 🎮 How It Works

### Barefoot Protection (Original FineFeet)
- When a player without boots steps on farmland, trampling is prevented
- Requires `gentlefeet.use` permission (if enabled in config)

### Feather Falling Protection (Enhanced Feature)
- Each level of feather falling on boots provides a percentage chance to avoid crop trampling
- Default: 25% per level (Level I = 25%, Level II = 50%, Level III = 75%, Level IV = 100%)
- Requires `gentlefeet.featherfall` permission (if enabled in config)
- Works independently of barefoot protection

## 🏗️ Building from Source

```bash
git clone https://github.com/knabbiii/GentleFeet.git
cd GentleFeet
./gradlew build
```

The compiled jar will be in `build/libs/GentleFeet-1.0.jar`

## 📊 Statistics

This plugin uses [bStats](https://bstats.org/plugin/bukkit/GentleFeet/27864) to collect anonymous usage statistics. 

**To disable:** Set `metrics.enable: false` in config.yml or `enabled: false` in `plugins/bStats/config.yml`

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Original Credit
Original concept and idea credit: **sammyt291** (FineFeet plugin)

## 🔗 Links

- [Issues](https://github.com/knabbiii/GentleFeet/issues)
- [Releases](https://github.com/knabbiii/GentleFeet/releases)
- [bStats](https://bstats.org/plugin/bukkit/GentleFeet/27864)

## 📈 Changelog

### 1.0
- Initial release: Professional crop protection plugin
- Barefoot protection (prevents crop trampling without boots)
- Feather falling enchantment mechanics with configurable chances
- Complete command system with live configuration editing
- Permission system and admin controls
- Optional bStats metrics integration
- Professional code structure and documentation
- Compatible with Minecraft 1.20-1.21 on Spigot/Paper/Purpur

---

Made with ❤️ for the Minecraft community