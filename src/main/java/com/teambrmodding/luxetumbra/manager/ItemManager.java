package com.teambrmodding.luxetumbra.manager;

import com.teambrmodding.luxetumbra.common.items.ItemExchanger;
import com.teambrmodding.luxetumbra.documentation.ItemBook;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
public class ItemManager {

    public static ItemBook book;

    public static ItemExchanger exchanger;

    /**
     * Register the items
     */
    public static void preInit() {
        book = registerItem(new ItemBook(), "book");

        exchanger = registerItem(new ItemExchanger(), "exchanger");
    }

    /**
     * Registers an item into the game
     * @param item The item object
     * @param name The name of the item
     * @param oreDict The ore dict tag
     * @return The item object passed
     */
    public static <T extends Item> T registerItem(T item, String name, String oreDict) {
        item.setRegistryName(Constants.MOD_ID, name);
        GameRegistry.register(item);
        if(oreDict != null)
            OreDictionary.registerOre(oreDict, item);
        return item;
    }

    /**
     * A short hand for no ore dict tag
     * @param item The item to register
     * @param name The name to register under
     * @return The item passed in
     */
    public static <T extends Item> T registerItem(T item, String name) {
        return registerItem(item, name, null);
    }
}
