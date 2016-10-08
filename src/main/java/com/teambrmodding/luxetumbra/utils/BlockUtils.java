package com.teambrmodding.luxetumbra.utils;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 10/8/2016
 */
public class BlockUtils {

    /**
     * Returns a collection of {@link net.minecraft.util.math.BlockPos} that are not Air
     *
     * @param size              the amount of blocks from the center block
     * @param mop               the block the player is looking at {@link net.minecraft.util.math.RayTraceResult}
     * @param world             {@link net.minecraft.world.World}
     * @return                  An {@link java.util.ArrayList} of {@link net.minecraft.util.math.BlockPos}
     */
    public static List<BlockPos> getBlockList(int size, RayTraceResult mop, World world) {

        BlockPos pos1;
        BlockPos pos2;
        List<BlockPos> actualList = new ArrayList<>();

        if (mop.sideHit.getAxis().isHorizontal()) {
            if (mop.sideHit == EnumFacing.NORTH || mop.sideHit == EnumFacing.SOUTH) {
                pos1 = mop.getBlockPos().offset(EnumFacing.UP, size).offset(EnumFacing.EAST, size);
                pos2 = mop.getBlockPos().offset(EnumFacing.DOWN, size).offset(EnumFacing.WEST, size);
            } else {
                pos1 = mop.getBlockPos().offset(EnumFacing.UP, size).offset(EnumFacing.SOUTH, size);
                pos2 = mop.getBlockPos().offset(EnumFacing.DOWN, size).offset(EnumFacing.NORTH, size);
            }

            while(pos2.getY() < mop.getBlockPos().getY() - 1) {
                pos1 = pos1.offset(EnumFacing.UP);
                pos2 = pos2.offset(EnumFacing.UP);
            }
        } else {
            pos1 = mop.getBlockPos().offset(EnumFacing.NORTH, size).offset(EnumFacing.WEST, size);
            pos2 = mop.getBlockPos().offset(EnumFacing.SOUTH, size).offset(EnumFacing.EAST, size);
        }

        for (BlockPos pos : BlockPos.getAllInBox(pos1, pos2)) {
            if (!world.isAirBlock(pos))
                actualList.add(pos);
        }

        return actualList;
    }
}
