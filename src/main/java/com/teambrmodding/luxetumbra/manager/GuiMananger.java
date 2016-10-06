package com.teambrmodding.luxetumbra.manager;

import com.teambrmodding.luxetumbra.core.iterfaces.IOpensGui;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis <pauljoda>
 * @since 10/5/2016
 */
public class GuiMananger implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // Hold local BlockPos, no need to keep making
        BlockPos pos = new BlockPos(x, y, z);

        // Check for tile entity with interface
        TileEntity tile = world.getTileEntity(pos);
        if(tile != null && tile instanceof IOpensGui)
            return ((IOpensGui)tile).getClientGuiElement(ID, player, world, x, y, z);

        // Check for blocks
        Block block = world.getBlockState(pos).getBlock();
        if(block != null && block instanceof IOpensGui)
            return ((IOpensGui)block).getClientGuiElement(ID, player, world, x, y, z);

        // Check Main Hand
        ItemStack rightStack = player.getHeldItem(EnumHand.MAIN_HAND);
        if(rightStack != null && rightStack.getItem() instanceof IOpensGui)
            return ((IOpensGui)rightStack.getItem()).getClientGuiElement(ID, player, world, x, y, z);

        // Check Off Hand
        ItemStack leftStack = player.getHeldItem(EnumHand.OFF_HAND);
        if(leftStack != null && leftStack.getItem() instanceof IOpensGui)
            return ((IOpensGui)leftStack.getItem()).getClientGuiElement(ID, player, world, x, y, z);

        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // Hold local BlockPos, no need to keep making
        BlockPos pos = new BlockPos(x, y, z);

        // Check for tile entity with interface
        TileEntity tile = world.getTileEntity(pos);
        if(tile != null && tile instanceof IOpensGui)
            return ((IOpensGui)tile).getServerGuiElement(ID, player, world, x, y, z);

        // Check for blocks
        Block block = world.getBlockState(pos).getBlock();
        if(block != null && block instanceof IOpensGui)
            return ((IOpensGui)block).getServerGuiElement(ID, player, world, x, y, z);

        // Check Main Hand
        ItemStack rightStack = player.getHeldItem(EnumHand.MAIN_HAND);
        if(rightStack != null && rightStack.getItem() instanceof IOpensGui)
            return ((IOpensGui)rightStack.getItem()).getServerGuiElement(ID, player, world, x, y, z);

        // Check Off Hand
        ItemStack leftStack = player.getHeldItem(EnumHand.OFF_HAND);
        if(leftStack != null && leftStack.getItem() instanceof IOpensGui)
            return ((IOpensGui)leftStack.getItem()).getServerGuiElement(ID, player, world, x, y, z);

        return null;
    }
}
