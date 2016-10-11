package com.teambrmodding.luxetumbra.documentation.data.chapters;

import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
import com.teambrmodding.luxetumbra.manager.ItemManager;
import com.teambrmodding.luxetumbra.utils.ClientUtils;
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
 * @since 10/9/2016
 */
public class ChapterContents extends Chapter {

    /**
     * The instance of this chapter
     */
    public static ChapterContents INSTANCE;

    /**
     * Default constructor
     */
    public ChapterContents() {
        super(ClientUtils.translate("chapter.content.name"));
    }

    /**
     * Add entries for chapter, not really more than one for this
     * @param entries The entries to add
     */
    @Override
    protected void addEntries(ArrayList<Entry> entries) {

    }

    /**
     * The display icon
     * @return The book itself
     */
    @Override
    protected ItemStack getBookmarkDisplay() {
        return new ItemStack(ItemManager.book);
    }
}
