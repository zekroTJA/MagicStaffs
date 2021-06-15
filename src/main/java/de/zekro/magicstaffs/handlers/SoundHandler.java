package de.zekro.magicstaffs.handlers;

import de.zekro.magicstaffs.MagicStaffs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {

    public static SoundEvent ELECTRIC_STAFF_ACTIVATE;
    public static SoundEvent FIRE_STAFF_ACTIVATE;
    public static SoundEvent HEAL_STAFF_ACTIVATE;
    public static SoundEvent TOXIC_STAFF_ACTIVATE;
    public static SoundEvent INFUSER_INFUSION;

    public static void registerSounds() {
        ELECTRIC_STAFF_ACTIVATE = registerSound("staffs.electric.activate");
        FIRE_STAFF_ACTIVATE = registerSound("staffs.fire.activate");
        HEAL_STAFF_ACTIVATE = registerSound("staffs.heal.activate");
        TOXIC_STAFF_ACTIVATE = registerSound("staffs.toxic.activate");
        INFUSER_INFUSION = registerSound("infuser.infusion");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation loc = new ResourceLocation(MagicStaffs.MOD_ID, name);
        SoundEvent e = new SoundEvent(loc);
        e.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(e);

        return e;
    }

}
