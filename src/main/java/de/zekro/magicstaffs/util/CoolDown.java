package de.zekro.magicstaffs.util;

import net.minecraft.world.World;

/**
 * General simple cool down manager.
 *
 * The cool down is calculated with the global
 * total world tick time.
 */
public class CoolDown {
    private long coolDown;
    private long lastActivation;

    /**
     * Create new instance of CoolDown with the
     * defined cool down value in world ticks.
     * 1 Tick ~ 1/20 second
     * @param cd cool down in world ticks
     */
    public CoolDown(long cd) {
        coolDown = cd;
    }

    /**
     * Try to take an action.
     * Returns false if on cool down. Else, returns
     * true and sets to cool down.
     * @param world world in
     * @return cool down status
     */
    public boolean take(World world) {
        if (onCoolDown(world))
            return false;

        lastActivation = world.getTotalWorldTime();
        return true;
    }

    /**
     * Returns if on cool down.
     * This does not set to cool down.
     * @param world world in
     * @return cool down state
     */
    public boolean onCoolDown(World world) {
        return lastActivation + coolDown >= world.getTotalWorldTime();
    }
}
