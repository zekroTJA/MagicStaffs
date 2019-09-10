package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {

    public static SoundEvent ELECTRIC_STAFF_ACTIVATE;
    public static SoundEvent FIRE_STAFF_ACTIVATE;

    public static void registerSounds() {
        ELECTRIC_STAFF_ACTIVATE = registerSound("staffs.electric.activate");
        FIRE_STAFF_ACTIVATE = registerSound("staffs.fire.activate");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation loc = new ResourceLocation(MagicStaffs.MOD_ID, name);
        SoundEvent e = new SoundEvent(loc);
        e.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(e);

        return e;
    }

}