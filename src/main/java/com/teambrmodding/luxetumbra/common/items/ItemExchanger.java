package com.teambrmodding.luxetumbra.common.items;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
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
                if (world.getTileEntity(pos) == null) {
                    setTags(stack, new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos))));
                    String blockAdded = I18n.translateToLocal("luxetumbra:exchanger.blockSet") + " " + getSetStack(stack).getDisplayName();
                    player.addChatComponentMessage(new TextComponentString(blockAdded));
                }
            } else if (getSetStack(stack) != null) {
                List<BlockPos> posList = BlockUtils.getBlockList(getSize(stack), facing, pos, world);
                    ItemStack compareStack = new ItemStack(world.getBlockState(pos).getBlock(), 1, world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
                posList.stream().filter(blockPos -> world.getTileEntity(blockPos) == null).forEach(blockPos -> {
                    ItemStack changeStack = new ItemStack(world.getBlockState(blockPos).getBlock(), 1, world.getBlockState(blockPos).getBlock().getMetaFromState(world.getBlockState(blockPos)));
                    if (compareStack.isItemEqual(changeStack)) {
                        if (world.isAirBlock(blockPos.offset(facing)) || pos.equals(blockPos))
                            if (player.capabilities.isCreativeMode || (player.inventory.clearMatchingItems(getSetStack(stack).getItem(), getSetStack(stack).getItemDamage(), 1, null) == 1)) {
                                world.setBlockState(blockPos, Block.getBlockFromItem(getSetStack(stack).getItem()).getStateFromMeta(getSetStack(stack).getItemDamage()));
                                if (!player.capabilities.isCreativeMode)
                                    player.inventory.addItemStackToInventory(changeStack);
                            }
                    }
                });
            }
        }

        if (world.isRemote)
            world.playSound(player, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 0.3F, 0.5F);

        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote && player.isSneaking()) {
            if (getSize(stack) == 4)
                setSize(stack, 1);
            else
                setSize(stack, getSize(stack) + 1);

            String newSize = I18n.translateToLocal("luxetumbra:exchanger.sizeSet") + " " + getSizeString(stack);
            player.addChatComponentMessage(new TextComponentString(newSize));
        }
        return new ActionResult(EnumActionResult.PASS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean bool) {
        list.add("Size: " + getSizeString(stack));
        if (getSetStack(stack) != null)
            list.add("Set Block: " + getSetStack(stack).getDisplayName());
        else
            list.add("Set Block: No Block Set!");
    }

    /*
        Accessors and Mutators
    */

    /**
     * Returns the size or 1
     *
     * @param stack     The {@link net.minecraft.item.ItemStack} that is being checked
     * @return          Current saved size or 1
     */
    public int getSize(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("Size") ? stack.getTagCompound().getInteger("Size") : 1;
    }

    /**
     * Sets the current size of replacement blocks
     *
     * @param stack     The {@link net.minecraft.item.ItemStack} to set the size to
     * @param size      The size to set
     */
    public void setSize(ItemStack stack, int size) {
        NBTTagCompound tag;

        if (stack.hasTagCompound())
            tag = stack.getTagCompound();
        else
            tag = new NBTTagCompound();

        tag.setInteger("Size", size);
        stack.setTagCompound(tag);
    }

    /**
     * Gets the current saved ItemStack
     *
     * @param stack     The {@link net.minecraft.item.ItemStack} that is being checked
     * @return          The saved {@link net.minecraft.item.ItemStack} or null
     */
    public @Nullable ItemStack getSetStack(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("Stack") ? ItemStack.loadItemStackFromNBT((NBTTagCompound) stack.getTagCompound().getTag("Stack")) : null;
    }

    /*
        Helper Methods
    */

    /**
     * Sets the {@link net.minecraft.nbt.NBTTagCompound}
     *
     * @param stackIn           The {@link net.minecraft.item.ItemStack} to set the {@link net.minecraft.nbt.NBTTagCompound} to
     * @param stackExchange     The {@link net.minecraft.item.ItemStack} to save
     */
    private void setTags(ItemStack stackIn, ItemStack stackExchange) {
        NBTTagCompound mainTag = new NBTTagCompound();
        NBTTagCompound stackTag = new NBTTagCompound();

        stackExchange.writeToNBT(stackTag);

        mainTag.setInteger("Size", getSize(stackIn));
        mainTag.setTag("Stack", stackTag);

        stackIn.setTagCompound(mainTag);
    }

    /**
     * Returns a string value for size
     *
     * @param stack     The {@link net.minecraft.item.ItemStack} to get the size from
     * @return          String of the size
     */
    private String getSizeString(ItemStack stack) {
        switch (getSize(stack)) {
            case 1:
                return "3x3";
            case 2:
                return "5x5";
            case 3:
                return "7x7";
            case 4:
                return "9x9";
            default:
                return "Opps. Something went wrong...";
        }
    }
}
