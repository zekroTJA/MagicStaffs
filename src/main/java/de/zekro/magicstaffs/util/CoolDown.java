package de.zekro.magicstaffs.util;

import net.minecraft.world.World;

public class CoolDown {
    private long coolDown;
    private long lastActivation;

    public CoolDown(long cd) {
        coolDown = cd;
    }

    public boolean take(World world) {
        if (onCoolDown(world))
            return false;

        lastActivation = world.getTotalWorldTime();
        return true;
    }

    public boolean onCoolDown(World world) {
        return lastActivation + coolDown >= world.getTotalWorldTime();
    }
}
