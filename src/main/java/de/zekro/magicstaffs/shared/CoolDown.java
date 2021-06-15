package de.zekro.magicstaffs.shared;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

/**
 * General simple cool down manager.
 *
 * The cool down is calculated with the global
 * total world tick time.
 */
public class CoolDown {

    private long coolDown;
    private HashMap<UUID, Long> activations;

    /**
     * Create new instance of CoolDown with the
     * defined cool down value in world ticks.
     * 1 Tick ~ 1/20 second
     * @param cd cool down in world ticks
     */
    public CoolDown(long cd) {
        coolDown = cd;
        activations = new HashMap<>();
    }

    /**
     * Sets the value for cool down.
     * @param coolDown cool down in ticks
     */
    public void setCoolDown(long coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * Try to take an action.
     * Returns false if on cool down. Else, returns
     * true and sets to cool down.
     * @param world world in
     * @return cool down status
     */
    public boolean take(World world, EntityPlayer player) {
        final UUID uid = player.getUniqueID();

        if (coolDown <= 0)
            return true;

        if (onCoolDown(world, uid))
            return false;

        activations.put(uid, world.getTotalWorldTime());
        return true;
    }

    /**
     * Returns if on cool down.
     * This does not set to cool down.
     * @param world world in
     * @return cool down state
     */
    public boolean onCoolDown(World world, UUID uid) {
        Long acts = activations.get(uid);
        if (acts == null) acts = 0L;
        return acts + coolDown >= world.getTotalWorldTime();
    }
}
