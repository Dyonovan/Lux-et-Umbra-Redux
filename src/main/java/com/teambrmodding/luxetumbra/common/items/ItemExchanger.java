package com.teambrmodding.luxetumbra.common.items;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

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

    Item exchangeItem;
    int size;

    public ItemExchanger() {
        super();
        this.setUnlocalizedName(Constants.MOD_ID + ":exchanger");
        this.setCreativeTab(LuxetUmbra.tabLuxetUmbra);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        //If sneaking set block to change
        if (player.isSneaking()) {
            //RayTraceResult mop = player.rayTrace(4.5f, world.getPa)
        }
        return super.onItemRightClick(stack, world, player, hand);
    }
}
