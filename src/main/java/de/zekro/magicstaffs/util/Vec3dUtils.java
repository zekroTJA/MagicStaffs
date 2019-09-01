package de.zekro.magicstaffs.util;

import net.minecraft.util.math.Vec3d;

public class Vec3dUtils {
    public static Vec3d multiply(Vec3d vec, Vec3d by) {
        return new Vec3d(vec.x * by.x, vec.y * by.y, vec.z * by.z);
    }

    public static Vec3d multiply(Vec3d vec, double by) {
        return multiply(vec, new Vec3d(by, by, by));
    }
}
