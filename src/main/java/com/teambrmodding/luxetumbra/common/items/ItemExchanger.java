package com.teambrmodding.luxetumbra.common.items;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
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
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                    exchangeBlock = new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
                    String blockAdded = I18n.translateToLocal("luxetumbra:exchanger.blockSet") + " " + exchangeBlock.getDisplayName();
                    player.addChatComponentMessage(new TextComponentString(blockAdded));
            } else if (exchangeBlock != null) {
                List<BlockPos> posList = BlockUtils.getBlockList(size, facing, pos, world);
                    ItemStack compareStack = new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
                    for (BlockPos blockPos : posList) {
                        ItemStack changeStack = new ItemStack(world.getBlockState(blockPos).getBlock(), 1, world.getBlockState(blockPos).getBlock().getMetaFromState(world.getBlockState(blockPos)));
                        if (compareStack.isItemEqual(changeStack)) {
                            if (world.isAirBlock(blockPos.offset(facing)) || pos.equals(blockPos))
                                if (player.capabilities.isCreativeMode || (!player.capabilities.isCreativeMode && player.inventory.clearMatchingItems(exchangeBlock.getItem(), exchangeBlock.getItemDamage(), 1, null) == 1))
                                    world.setBlockState(blockPos, Block.getBlockFromItem(exchangeBlock.getItem()).getStateFromMeta(exchangeBlock.getItemDamage()));
                        }
                    }
            }
        }

        if (world.isRemote)
            world.playSound(player, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 0.3F, 0.5F);

        return EnumActionResult.SUCCESS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool) {
        if (exchangeBlock != null)
            list.add("Set Block: " + exchangeBlock.getDisplayName());
        else
            list.add("Set Block: No Block Set!");
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
