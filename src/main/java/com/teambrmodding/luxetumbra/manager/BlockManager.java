package com.teambrmodding.luxetumbra.manager;

import com.teambrmodding.luxetumbra.common.blocks.BlockAltar;
import com.teambrmodding.luxetumbra.common.tiles.TileAltar;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 10/11/2016
 */
public class BlockManager {

    public static Block blockAltar = new BlockAltar();

    public static void preInit() {
        registerBlock(blockAltar, "blockAltar", TileAltar.class);
    }

    private static void registerBlock(Block block, String name, Class<? extends TileEntity> tile) {
        registerBlock(block, name, tile, null);
    }

    private static void registerBlock(Block block, String name, Class<? extends TileEntity> tile, String oreDict) {
        block.setRegistryName(Constants.MOD_ID, name);
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(Constants.MOD_ID, name);
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        if (tile != null)
            GameRegistry.registerTileEntity(tile, name);
        if (oreDict != null)
            OreDictionary.registerOre(oreDict, block);
    }
}
