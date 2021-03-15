# MagicStaffs &nbsp; ![Main CI](https://github.com/zekroTJA/MagicStaffs/workflows/Main%20CI/badge.svg) [![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/zekroTJA/MagicStaffs?include_prereleases)](https://github.com/zekroTJA/MagicStaffs/releases) [![](https://img.shields.io/badge/On-CursForge-9e0cce)](https://www.curseforge.com/minecraft/mc-mods/magicstaffs)

A Minecraft Forge modification implementing magic staffs like an electric, fire, life and toxic staff. This modification is created as adaption to the staffs from the [Better Dungeons](https://www.curseforge.com/minecraft/mc-mods/better-dungeons) mod which is no longer maintained for versions beyond Minecraft 1.7.x.

## Documentation

Documentation can be found on the official [**CurseForge page**](https://www.curseforge.com/minecraft/mc-mods/magicstaffs).

## Download

You can download the latest stable pre release versions from [**Releases**](https://github.com/zekroTJA/MagicStaffs/releases).  
The releases consists of the following packages:  
  - `MagicStaffs-<MCVersion>-<ModVersion>.jar` - The actual mod package
  - `MagicStaffs-<MCVersion>-<ModVersion>-deobf.jar` - The deobfuscated mod package
  - `MagicStaffs-<MCVersion>-<ModVersion>-javadoc.jar` - The generated Java documentation files package
  - `MagicStaffs-<MCVersion>-<ModVersion>-sources.jar` - The source code package
  
## Compiling

For compiling you need to have Java JDK 8 installed.

1. Clone the Repository:  
   ```
   $ git clone https://github.com/zekroTJA/MagicStaffs.git .
   ```
   
2. Decompile Minecraft sources:  
   ```
   $ ./gradlew setupDecompWorkspace
   ```
   
 3. Compile packages  
    ```
    $ ./gradlew build
    ```
    
Then, the packages are located in the `./build/libs` location.

## Contributors

Code is written by zekro.  
Sounds and textures by luxtracon ([@luxtracon](https://twitter.com/luxtracon)).

---

© 2021 zekro Development (Ringo Hoffmann)  
Covered by MIT Licence.
