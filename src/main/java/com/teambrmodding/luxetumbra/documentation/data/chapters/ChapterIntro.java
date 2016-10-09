package com.teambrmodding.luxetumbra.documentation.data.chapters;

import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
import com.teambrmodding.luxetumbra.documentation.data.entries.intro.EntryIntroduction;
import com.teambrmodding.luxetumbra.utils.ClientUtils;

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
public class ChapterIntro extends Chapter {

    public static ChapterIntro INSTANCE;

    /**
     * Default constructor
     */
    public ChapterIntro() {
        super(ClientUtils.translate("chapter.intro.name"));
    }

    /**
     * Add entries for intro
     * @param entries The entries to add
     */
    @Override
    protected void addEntries(ArrayList<Entry> entries) {
        entries.add(EntryIntroduction.INSTANCE);
    }
}
