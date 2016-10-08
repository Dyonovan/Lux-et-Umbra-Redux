package com.teambrmodding.luxetumbra.documentation.data.entries;

import com.teambrmodding.luxetumbra.documentation.data.Page;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 10/8/2016
 */
public abstract class Entry {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * Storage for all pages for this entry
     */
    private ArrayList<Page> pages = new ArrayList<>();

    /**
     * The name of this entry
     */
    private String entryTitle;

    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Add pages for this entry, called by constructor
     * @param pages The object to add pages to
     */
    protected abstract void addPages(ArrayList<Page> pages);

    /**
     * Sends an object in, check if any pages allow this object as search object
     * @param obj The object to check
     * @return The page that uses this as a search object, null if not found
     */
    protected abstract Page getPageForSearchObject(Object obj);
}
