package com.teambrmodding.luxetumbra.common.items;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
public class ItemExchanger extends Item {

    private ItemStack exchangeBlock;
    private int size = 1;

    public ItemExchanger() {
        super();
        this.setUnlocalizedName(Constants.MOD_ID + ":exchanger");
        this.setCreativeTab(LuxetUmbra.tabLuxetUmbra);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        if (!world.isRemote) {
            //If sneaking set block to change
            if (player.isSneaking()) {
                RayTraceResult mop = player.rayTrace(4.5f, 1.0f);
                if (mop != null) {
                    exchangeBlock = new ItemStack(world.getBlockState(mop.getBlockPos()).getBlock(), 1, world.getBlockState(mop.getBlockPos()).getBlock().getMetaFromState(world.getBlockState(mop.getBlockPos())));
                    player.addChatComponentMessage(new TextComponentTranslation("luxetumbra:exchanger.blockSet"));
                }
            } else if (exchangeBlock != null) {
                RayTraceResult mop = player.rayTrace(4.5f, 1.0f);
                List<BlockPos> posList = BlockUtils.getBlockList(size, mop, world);
                if (mop != null) {
                    ItemStack compareStack = new ItemStack(world.getBlockState(mop.getBlockPos()).getBlock(), 1, world.getBlockState(mop.getBlockPos()).getBlock().getMetaFromState(world.getBlockState(mop.getBlockPos())));
                    for (BlockPos pos : posList) {
                        ItemStack changeStack = new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
                        if (compareStack.isItemEqual(changeStack)) {
                            if (world.isAirBlock(pos.offset(mop.sideHit)))
                                world.setBlockState(pos, Block.getBlockFromItem(exchangeBlock.getItem()).getStateFromMeta(exchangeBlock.getItemDamage()));
                        }
                    }
                }
            }
        }
        return super.onItemRightClick(stack, world, player, hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool) {
        if (exchangeBlock != null)
            list.add("Set Block: " + exchangeBlock.getDisplayName());
        else
            list.add("Set Block: " + "No Block Set!");
    }


    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    /**
     * Gets the current size the Exchanger is set to
     *
     * @return  current set size
     */
    public int getSize() { return size; }
}
