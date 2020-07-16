package de.zekro.magicstaffs.shared;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

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
    public static List<Entity> getEntitiesInAimDirection(int range, EntityPlayer player, World world,
                                                         @Nullable Predicate<? super Entity> predicate) {

        final ArrayList<Entity> res = new ArrayList<>();

        final Predicate<? super Entity> nonNullPredicate = predicate != null ? predicate : (v) -> true;

        iterateLinearAimDirection(range, player, (vec) -> {

            final AxisAlignedBB section = new AxisAlignedBB(
                    vec.x - 1, vec.y - 1, vec.z - 1,
                    vec.x + 1, vec.y + 1, vec.z + 1);

            if (!world.getBlockState(new BlockPos(vec.x, vec.y, vec.z)).getBlock().equals(Blocks.AIR))
                return false;

            res.addAll(world.getEntitiesInAABBexcluding(player, section, nonNullPredicate::test));

            return true;
        });

        return res;
    }

//    public static Block getBlockInAimDirection(int range, EntityPlayer player, World world,
//                                                @Nullable Predicate<? super Block> predicate) {
//
//        final AtomicReference<Block> res = new AtomicReference<>();
//        final Predicate<? super Block> nonNullPredicate = predicate != null ? predicate : (v) -> true;
//
//        iterateLinearAimDirection(range, player, (vec) -> {
//
//            final Block cBlock = world.getBlockState(new BlockPos(vec.x, vec.y, vec.z)).getBlock();
//            if (!nonNullPredicate.test(cBlock))
//                return true;
//
//            res.set(cBlock);
//            return false;
//        });
//
//        return res.get();
//    }

    public static void iterateLinearAimDirection(int range, EntityPlayer player,
                                                  Predicate<Vec3d> callback) {

        final Vec3d playerPos = player.getPositionEyes(1);
        final Vec3d aim = player.getLookVec();

        for (int i = 0; i < range; i++) {
            final Vec3d cPos = new Vec3d(
                    playerPos.x + (aim.x * i),
                    playerPos.y + (aim.y * i),
                    playerPos.z + (aim.z * i));

            if (!callback.test(cPos))
                break;
        }
    }
}
