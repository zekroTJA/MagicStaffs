package de.zekro.magicstaffs.shared;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Provide utilities for Staffs.
 */
public class StaffUtil {

    /**
     * Returns a list of entities in the players aim direction line
     * in the given effective range.
     * With the predicate, you can define which entities will
     * be collected and returned.
     * @param range effective range in aim direction
     * @param player player
     * @param world world in
     * @param predicate predicate for selecting entities
     * @return list of entities
     */
    public static List<Entity> getEntitiesInAimDirection(int range, EntityPlayer player, World world, @Nullable Predicate<? super Entity> predicate) {
        final Vec3d playerPos = player.getPositionEyes(1);
        final Vec3d aim = player.getLookVec();

        final ArrayList<Entity> res = new ArrayList<>();

        for (int i = 0; i < range; ++i) {
            final Vec3d cPos = new Vec3d(
                    playerPos.x + (aim.x * i),
                    playerPos.y + (aim.y * i),
                    playerPos.z + (aim.z * i));

            final AxisAlignedBB AABB = new AxisAlignedBB(
                    cPos.x - 1, cPos.y - 1, cPos.z - 1,
                    cPos.x + 1, cPos.y + 1, cPos.z + 1);

            if (!world.getBlockState(new BlockPos(cPos.x, cPos.y, cPos.z)).getBlock().equals(Blocks.AIR))
                break;

            res.addAll(world.getEntitiesInAABBexcluding(player, AABB, predicate));
        }

        return res;
    }

}
