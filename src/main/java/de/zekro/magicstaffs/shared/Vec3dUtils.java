package de.zekro.magicstaffs.shared;

import net.minecraft.util.math.Vec3d;

/**
 * Contains utils for calculating with Vec3d's.
 */
public class Vec3dUtils {

    /**
     * Multiply Vec3d by another Vec3d by multiplying each
     * value by the passed vectors counter part.
     * @param vec vector
     * @param by multiply by
     * @return result vector
     */
    public static Vec3d multiply(Vec3d vec, Vec3d by) {
        return new Vec3d(vec.x * by.x, vec.y * by.y, vec.z * by.z);
    }

    /**
     * Multiply a Vec3d by a double value by multiplying each
     * value of the vector with it.
     * @param vec vector
     * @param by multiply by
     * @return result
     */
    public static Vec3d multiply(Vec3d vec, float by) {
        return multiply(vec, new Vec3d(by, by, by));
    }
}
