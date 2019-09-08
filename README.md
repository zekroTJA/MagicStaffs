# MagicStaffs &nbsp; [![](https://travis-ci.org/zekroTJA/MagicStaffs.svg?branch=master)](https://travis-ci.org/zekroTJA/MagicStaffs) [![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/zekroTJA/MagicStaffs?include_prereleases)](https://github.com/zekroTJA/MagicStaffs/releases)

> **ATTENTION**  
> This project is in a very early WIP status and not that usable at the moment.

A Minecraft Forge modification implementing magic staffs like an electric, medic, poisson or fire staff. This modification is created as adaption to the staffs from the [Better Dungeons](https://www.curseforge.com/minecraft/mc-mods/better-dungeons) mod which is no longer maintained for versions beyond Minecraft 1.7.x.

## Documentation

Documentaion can be found on the official [**CurseForge page**](https://www.curseforge.com/minecraft/mc-mods/magicstaffs).

## Download

You can download the latest stable pre release versions from [**Releases**](https://github.com/zekroTJA/MagicStaffs/releases).  
The releases consists of the following packages:  
  - `MagicStaffs-<MCVersion>-<ModVersion>.jar` - The actual mod package
  - `MagicStaffs-<MCVersion>-<ModVersion>-deobf.jar` - The deobfuscated mod package
  - `MagicStaffs-<MCVersion>-<ModVersion>-javadoc.jar` - The generated Java documentation files package
  - `MagicStaffs-<MCVersion>-<ModVersion>-sources.jar` - The source code package
  
## Compiling

For compiling you need to have installed Java JDK 8.

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
Textures and models are created by luxtracon ([@luxtracon](https://twitter.com/luxtracon)) and zekro, and some are stolen and modified from the original Minecraft textures.

---

© 2019 zekro Development (Ringo Hoffmann)  
Covered by MIT Licence.
