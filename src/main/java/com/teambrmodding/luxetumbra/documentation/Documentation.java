package com.teambrmodding.luxetumbra.documentation;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.documentation.data.Page;
import com.teambrmodding.luxetumbra.documentation.data.chapters.Chapter;
import com.teambrmodding.luxetumbra.documentation.data.chapters.ChapterIntro;
import com.teambrmodding.luxetumbra.utils.ClientUtils;
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
 * @since 10/4/2016
 */
public class Documentation {

    /**
     * The list of pages in the book
     */
    public static ArrayList<Page> pages = new ArrayList<>();

    /**
     * The list of all chapters
     */
    public static ArrayList<Chapter> chapters = new ArrayList<>();

    /**
     * The object that holds bookmarks. Holds the chapter and display stack, and page to open
     */
    public static ArrayList<Tuple<Tuple<Chapter, ItemStack>, Page>> bookmarks = new ArrayList<>();

    /**
     * Loads the book
     */
    public static void init() {
        LuxetUmbra.logger.info(ClientUtils.translate("log.generateDocs"));

        // Add the intro
        ChapterIntro.INSTANCE = new ChapterIntro();

        LuxetUmbra.logger.info(ClientUtils.translate("log.generatedDocs"));
    }
}
