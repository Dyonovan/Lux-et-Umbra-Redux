package com.teambrmodding.luxetumbra.common.blocks;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.common.tiles.TileAltar;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

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
public class BlockAltar extends BlockContainer {

    public BlockAltar() {
        super(Material.IRON);
        this.setUnlocalizedName(Constants.MOD_ID + ":blockAltar");
        this.setCreativeTab(LuxetUmbra.tabLuxetUmbra);
        this.setHardness(2.0F);
    }



    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAltar();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) { return false; }
}
