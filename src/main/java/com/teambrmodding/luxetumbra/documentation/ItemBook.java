package com.teambrmodding.luxetumbra.documentation;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.core.container.ContainerGeneric;
import com.teambrmodding.luxetumbra.core.iterfaces.IOpensGui;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 10/4/2016
 */
public class ItemBook extends Item implements IOpensGui {

    public ItemBook() {
        super();
        setUnlocalizedName(Constants.MOD_ID + ":book");
        setCreativeTab(LuxetUmbra.tabLuxetUmbra);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        playerIn.openGui(LuxetUmbra.INSTANCE, 0, worldIn,
                playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    /*******************************************************************************************************************
     * IOpensGui                                                                                                       *
     *******************************************************************************************************************/

    /**
     * Used to get the client side info for the gui. This should be an instance of Gui
     * @param ID The GUI id, can be used in some cases
     * @param player The player
     * @param world The world
     * @param x The x pos
     * @param y The y pos
     * @param z The z pos
     * @return The gui client object, null if none
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiBook();
    }

    /**
     * Used to get the server side info for the gui. This should be an instance of Container
     * @param ID The GUI id, can be used in some cases
     * @param player The player
     * @param world The world
     * @param x The x pos
     * @param y The y pos
     * @param z The z pos
     * @return The gui server object, null if none
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerGeneric();
    }
}
