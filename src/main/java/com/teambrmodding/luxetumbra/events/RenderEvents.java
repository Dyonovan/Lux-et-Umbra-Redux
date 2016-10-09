package com.teambrmodding.luxetumbra.events;

import com.teambrmodding.luxetumbra.common.items.ItemExchanger;
import com.teambrmodding.luxetumbra.manager.ItemManager;
import com.teambrmodding.luxetumbra.utils.BlockUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
public class RenderEvents implements IResourceManagerReloadListener {

    private TextureAtlasSprite[] destroyedIcons = new TextureAtlasSprite[10];

    @SubscribeEvent
    public void renderBlockEvent(RenderWorldLastEvent event) {
        PlayerControllerMP controllerMP = Minecraft.getMinecraft().playerController;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        World world = player.worldObj;
        List<BlockPos> blockList;

        //Add lines around blocks
        if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().isItemEqual(new ItemStack(ItemManager.exchanger))) {
            RayTraceResult mop = player.rayTrace(controllerMP.getBlockReachDistance(), event.getPartialTicks());
            if (mop != null && !world.isAirBlock(mop.getBlockPos())) {
                ItemExchanger wand = (ItemExchanger) player.getHeldItemMainhand().getItem();
                int size =  ((ItemExchanger) player.getHeldItemMainhand().getItem()).getSize();
                blockList =  BlockUtils.getBlockList(size, mop.sideHit, mop.getBlockPos(), world);
                ItemStack centerStack = new ItemStack(world.getBlockState(mop.getBlockPos()).getBlock(), 1, world.getBlockState(mop.getBlockPos()).getBlock().getMetaFromState(world.getBlockState(mop.getBlockPos())));
                for (BlockPos pos : blockList) {
                    ItemStack stack = new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
                    if (centerStack.isItemEqual(stack))
                        event.getContext().drawSelectionBox(player, new RayTraceResult(new Vec3d(0, 0, 0), null, pos), 0, event.getPartialTicks());
                }
            }
        }
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        TextureMap tm = Minecraft.getMinecraft().getTextureMapBlocks();
        for (int x = 0; x < destroyedIcons.length; x++) {
            destroyedIcons[x] = tm.getAtlasSprite("minecraft:blocks/destroy_stage_" + x);
        }
    }
}
