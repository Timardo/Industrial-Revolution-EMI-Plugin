# Industrial Revolution EMI plugin
Adds recipes for [Industrial Revolution](https://github.com/GabrielOlvH/Industrial-Revolution) to EMI recipe viewer. Additionally fixes missing items from the list - ores, dusts, ingots, tools, armor etc. Configurable to prevent issues with Extra Mod Integrations' IndRev integration.

Requires at least Industrial Revolution version 1.16.5-BETA

# Configuration

A simple config is provided to fine tune all features of this mod. The config is reloaded from the disk every time EMI registers its plugins (basically when joining a world/server).

Default config file:
```
{
  "addMachineRecipes": true,
  "addChargedVersions": true,
  "hideNotImplementedItems": true,
  "addMissingItems": true,
  "disableExtraModIntegration": true
}
```

 - `addMachineRecipes`: when enabled, adds recipes for all machines as well as registers these machines as workstations. This option also adds electric furnaces to the smelting category.
 - `addChargedVersions`: adds charged versions of drills, modular armor, portable charger, battery and gamer axe to mimic the official REI integration.
 - `hideNotImplementedItems`: force-hides items that are not fully implemented yet. This will only have an effect when EMI's Index Source setting has been set to include Registered items. As some people play with this option on for various reasons, this option is on by default since those items are of no use anyway.
 - `addMissingItems`: adds missing items to EMI index - ores, dusts, plates, armor, etc.
 - `disableExtraModIntegration`: disable Extra Mod Integration's Industrial Revolution integration (so many tions lol) to prevent duplicate recipes and machines in the index when it's installed. In case one prefers the visuals provided by this mod, set this and addMachineRecipes to false. All other features of IREMIPlugin will still work.

# Download

Modrinth: TBD

CurseForge: TBD
