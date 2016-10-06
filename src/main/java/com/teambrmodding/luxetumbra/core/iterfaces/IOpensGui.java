package com.teambrmodding.luxetumbra.core.iterfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * The interface to identify that something opens a gui. Can be an Item, Block, or TileEntity
 *
 * In order to use this annotation, you will need to use the instance of Lux-et-Umbra as the id and call
 * player.openGui(MOD, ID, WORLD, X, Y, Z);
 *
 * @author Paul Davis <pauljoda>
 * @since 10/5/2016
 */
public interface IOpensGui {

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
    Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);

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
    Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
}
