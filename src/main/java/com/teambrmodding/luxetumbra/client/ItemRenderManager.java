package com.teambrmodding.luxetumbra.client;

import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.manager.ItemManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis <pauljoda>
 * @since 10/4/2016
 */
public class ItemRenderManager {

    /**
     * Add the item renderers to the game
     */
    public static void registerItemRenderers() {
        registerItem(ItemManager.book);
    }

    /**
     * Register items to the client renderer
     * @param item The item to register
     */
    public static void registerItem(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
            new ModelResourceLocation(item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * Register a block model
     * @param block The block
     * @param name The name
     * @param variants The variants
     * @param meta The meta
     */
    public static void registerBlockModel(Block block, String name, String variants, int meta) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                meta, new ModelResourceLocation(new ResourceLocation(Constants.MOD_ID, name), variants));
    }

    /**
     * Registers an item model
     * @param item The item model
     * @param name The name
     * @param variants The variants
     * @param meta The meta
     */
    public static void registerItemModel(Item item, String name, String variants, int meta) {
        ModelLoader.setCustomModelResourceLocation(item,
                meta, new ModelResourceLocation(new ResourceLocation(Constants.MOD_ID, name), variants));
    }
}
