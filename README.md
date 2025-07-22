# Industrial Revolution EMI plugin
Adds recipes from [Industrial Revolution](https://github.com/GabrielOlvH/Industrial-Revolution) to EMI recipe viewer including all allowed ores in the Mining Rig. Additionally translates tags and fixes missing items. Almost every feature of the mod is configurable.

Tested only with Industrial Revolution version 1.16.7-BETA

# Download

Modrinth (still waiting for approval): [https://modrinth.com/project/industrial-revolution-emi-plugin](https://modrinth.com/project/industrial-revolution-emi-plugin)

CurseForge: [https://www.curseforge.com/minecraft/mc-mods/industrial-revolution-emi-plugin](https://www.curseforge.com/minecraft/mc-mods/industrial-revolution-emi-plugin)

# Configuration

A simple config is provided to fine tune all features of this mod. The config is reloaded from the disk every time EMI registers its plugins (basically when joining a world/server).

Default config file:
```
{
  "addMachineRecipes": true,
  "addMiningRigRecipes": true,
  "addChargedVersions": true,
  "hideNotImplementedItems": true,
  "addMissingItems": true,
  "disableExtraModIntegration": true
}
```

 - `addMachineRecipes`: when enabled, adds recipes for all machines as well as registers these machines as workstations. This option also adds electric furnaces to the smelting category.
 - `addMiningRigRecipes`: adds recipes for all allowed ores in the Mining Rig.
 - `addChargedVersions`: adds charged versions of drills, modular armor, portable charger, battery and gamer axe just like the official REI integration.
 - `hideNotImplementedItems`: force-hides items that are not fully implemented yet. This will only have an effect when EMI's Index Source setting is set to include Registered items. As some people play with this setting enabled for various reasons, this option is enabled by default to hide clutter.
 - `addMissingItems`: adds missing items to EMI index - ores, dusts, plates, armor, etc.
 - `disableExtraModIntegration`: disable Extra Mod Integration's Industrial Revolution integration (so many tions lol) to prevent duplicate recipes and machines in the index when it's installed. In case one prefers the visuals provided by this mod, set this and addMachineRecipes to false. All other enabled features will still work.
