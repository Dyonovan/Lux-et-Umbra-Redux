package com.teambrmodding.luxetumbra.documentation.data.entries;

import com.teambrmodding.luxetumbra.documentation.Documentation;
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
     * The name of this entry
     */
    private String entryTitle;

    /**
     * Display stack used for the index, null for no stack
     */
    private ItemStack displayStack;

    /**
     * Storage for all pages for this entry
     */
    private ArrayList<Page> pages = new ArrayList<>();


    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Add pages for this entry, called by constructor
     * @param pages The object to add pages to
     */
    protected abstract void addPages(ArrayList<Page> pages);

    /*******************************************************************************************************************
     * Constructor                                                                                                     *
     *******************************************************************************************************************/

    /**
     * Main constructor for the entry
     * @param title The entry title
     * @param stack The entry stack to display, null for no display
     */
    public Entry(String title, ItemStack stack) {
        entryTitle = title;
        displayStack = stack;
        addPages(pages);
        Documentation.pages.addAll(pages);
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Sends an object in, check if any pages allow this object as search object
     * @param obj The object to check
     * @return The page that uses this as a search object, null if not found
     */
    protected Page getPageForSearchObject(Object obj) {
        for(Page page : pages) {
            for(Object pageObj : page.getSearchObjects()) {
                if(pageObj.equals(obj))
                    return page;
            }
        }
        return null;
    }

    /*******************************************************************************************************************
     * Accessors / Mutators                                                                                            *
     *******************************************************************************************************************/

    /**
     * The entry title
     * @return The entry title
     */
    public String getEntryTitle() {
        return entryTitle;
    }

    /**
     * Set the entry title
     * @param entryTitle The title to set
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    /**
     * Get the display stack
     * @return The stack to display
     */
    public ItemStack getDisplayStack() {
        return displayStack;
    }

    /**
     * Set the display stack
     * @param displayStack The stack to display
     */
    public void setDisplayStack(ItemStack displayStack) {
        this.displayStack = displayStack;
    }

    /**
     * Get the list of pages
     * @return The page list
     */
    public ArrayList<Page> getPages() {
        return pages;
    }
}
