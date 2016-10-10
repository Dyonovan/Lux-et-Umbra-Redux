package com.teambrmodding.luxetumbra.documentation.data.entries.contents;

import com.teambrmodding.luxetumbra.documentation.data.Page;
import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
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
 * @since 10/9/2016
 */
public class EntryContents extends Entry {

    public static EntryContents INSTANCE = new EntryContents();

    /**
     * Main constructor for the entry
     */
    public EntryContents() {
        super(ClientUtils.translate("entry.content.name"), null);
    }

    @Override
    protected void addPages(ArrayList<Page> pages) {

    }
}
