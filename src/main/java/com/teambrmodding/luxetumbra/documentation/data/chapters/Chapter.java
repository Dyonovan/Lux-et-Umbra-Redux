package com.teambrmodding.luxetumbra.documentation.data.chapters;

import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;

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
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/
}
