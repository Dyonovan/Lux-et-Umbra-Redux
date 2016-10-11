package com.teambrmodding.luxetumbra.documentation.data.chapters;

import com.teambrmodding.luxetumbra.documentation.Documentation;
import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

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
public abstract class Chapter {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The name of this chapter
     */
    private String chapterTitle;

    /**
     * Storage for all entries for this chapter
     */
    private ArrayList<Entry> entries = new ArrayList<>();

    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Add entries to this chapter
     * @param entries The entries to add
     */
    protected abstract void addEntries(final ArrayList<Entry> entries);

    /***
     * Defines what to render on this chapters bookmark. Return null to prevent bookmark from being added
     * @return The itemstack to display
     */
    protected abstract ItemStack getBookmarkDisplay();

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

    /**
     * Default constructor
     * @param title The title of this chapter
     */
    public Chapter(String title) {
        chapterTitle = title;
        addEntries(entries);
        if(getBookmarkDisplay() != null) {
            Documentation.bookmarks.add(new Tuple<>(new Tuple<>(this, getBookmarkDisplay()), entries.get(0).getPages().get(0)));
        }
        Documentation.chapters.add(this);
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    /**
     * The title of this chapter
     * @return The title to display
     */
    public String getChapterTitle() {
        return chapterTitle;
    }

    /**
     * Set the title of this chapter
     * @param chapterTitle The title of this chapter
     */
    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    /**
     * Get the entries for this chapter
     * @return The entries
     */
    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
