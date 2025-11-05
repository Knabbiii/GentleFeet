<div align="center">

![GentleFeet Banner](.github/assets/gentlefeet-banner.png)

[![Spigot Downloads](https://img.shields.io/spiget/downloads/129991?style=for-the-badge&logo=spigotmc&color=79c10f)](https://www.spigotmc.org/resources/gentlefeet.129991/)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/gentlefeet?logo=modrinth&style=for-the-badge&label=Downloads&color=79c10f)](https://modrinth.com/plugin/gentlefeet)
[![CodeFactor](https://img.shields.io/codefactor/grade/github/knabbiii/gentlefeet?style=for-the-badge&logo=codefactor&color=79c10f&label=Code%20Quality)](https://www.codefactor.io/repository/github/knabbiii/gentlefeet)
[![License: MIT](https://img.shields.io/github/license/Knabbiii/GentleFeet?color=79c10f&label=License&style=for-the-badge&logo=github)](https://opensource.org/licenses/MIT)
[![GitHub release](https://img.shields.io/github/v/release/knabbiii/gentlefeet?style=for-the-badge&label=Release&color=79c10f&logo=github)](https://github.com/Knabbiii/gentlefeet/releases)

</div>

> **Gentle steps for gentle farmers - protect your precious crops with style!**

A professional Minecraft plugin that prevents crop trampling through intelligent protection mechanics. Walk barefoot or use enchanted boots - your crops stay safe either way!

**Enhanced with modern features and comprehensive admin controls**

## Features

- **Barefoot protection** - Walk gently without boots and never trample crops again
- **Feather Falling enhancement** - Enchanted boots provide percentage-based protection 
- **Live configuration** - Modify settings without server restart using admin commands
- **Professional permissions** - Granular control over who can use what features
- **Debug mode** - Comprehensive logging and troubleshooting tools
- **Player notifications** - Optional messages when crops are protected
- **Lightweight** - Minimal performance impact with efficient event handling
- **Modern compatibility** - Works with Spigot, Paper, Purpur and all modern versions
- **Optional metrics** - bStats integration that can be disabled anytime

## Installation

1. Download the latest `.jar` file from the [releases page](https://github.com/Knabbiii/GentleFeet/releases)
2. Place it in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/GentleFeet/config.yml`

## Configuration

The plugin creates a comprehensive `config.yml` file with detailed options:

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

### Configuration Options

| Option | Description | Default |
|--------|-------------|---------|
| `barefoot.enable` | Enable barefoot crop protection | `true` |
| `feather-falling.enable` | Enable feather falling protection | `true` |
| `feather-falling.chance-per-level` | Protection chance per enchantment level | `25` |
| `feather-falling.max-level` | Maximum feather falling level to consider | `4` |
| `debug.enable` | Enable debug logging | `false` |
| `debug.notify-players` | Show protection messages to players | `false` |
| `metrics.enable` | Enable bStats metrics collection | `true` |

## How to Use

1. **Barefoot Protection**: Simply walk around without boots - crops won't be trampled
2. **Feather Falling Protection**: Wear boots with feather falling enchantment for percentage-based protection
3. **Admin Features**: Use `/gentlefeet` commands to manage the plugin live
4. **Customize**: Modify settings in config.yml or use admin commands

## Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/gentlefeet info` | None | Show plugin information and current config |
| `/gentlefeet reload` | `gentlefeet.admin` | Reload plugin configuration |
| `/gentlefeet toggle <setting>` | `gentlefeet.admin` | Toggle features (barefoot, featherfall, notify-players, debug, metrics) |
| `/gentlefeet set <setting> <value>` | `gentlefeet.admin` | Set values (featherfall-chance 1-100, max-level 1-10) |

**Aliases:** `/gf`, `/gentlefeet`

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `gentlefeet.use` | Allows using barefoot crop protection | `true` |
| `gentlefeet.featherfall` | Allows using feather falling crop protection | `true` |  
| `gentlefeet.admin` | Allows access to admin commands | `op` |
| `gentlefeet.*` | Grants all permissions | `op` |

## Requirements

- **Minecraft:** 1.20+ (compatible with 1.20.x and 1.21.x)
- **Server:** Spigot, Paper, or compatible
- **Java:** 21+ (LTS recommended)

## Credits

**Original Concept:** [sammyt291](https://github.com/sammyt291) - [FineFeet](https://github.com/sammyt291/FineFeet)

**This Version:** Completely rewritten and enhanced implementation with:
- Modern command system and admin features
- Live configuration editing without restarts
- Enhanced feather falling mechanics with configurable chances  
- Professional permission system
- Debug mode and comprehensive logging
- Optional bStats integration

Combining the original concept with modern plugin development standards and enhanced functionality.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

**Original Developer:** [sammyt291](https://github.com/sammyt291)  
**Original Plugin:** [FineFeet](https://github.com/sammyt291/FineFeet)

This is a completely rewritten and modernized version of the original concept with extensive enhancements and compatibility for current Minecraft versions.

---

*Made with care for the Minecraft community*

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