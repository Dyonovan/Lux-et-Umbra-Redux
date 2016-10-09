package com.teambrmodding.luxetumbra.documentation.data.entries.intro;

import com.teambrmodding.luxetumbra.documentation.data.Page;
import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
import com.teambrmodding.luxetumbra.documentation.data.pages.intro.PageIntroduction;
import com.teambrmodding.luxetumbra.documentation.data.pages.intro.PageTitle;
import com.teambrmodding.luxetumbra.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

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
public class EntryIntroduction extends Entry {

    /**
     * The instance of this entry
     */
    public static EntryIntroduction INSTANCE = new EntryIntroduction();

    /**
     * Main constructor for the entry
     */
    public EntryIntroduction() {
        super(ClientUtils.translate("entry.title.name"), null);
    }

    /**
     * Used to add pages
     * @param pages The object to add pages to
     */
    @Override
    protected void addPages(ArrayList<Page> pages) {
        pages.add(PageTitle.INSTANCE);

        // Pull up text
        String introText = ClientUtils.translate("page.intro.text");

        // Create a list for the text
        FontRenderer fontRenderObj = Minecraft.getMinecraft().fontRendererObj;
        boolean uni = fontRenderObj.getUnicodeFlag();
        fontRenderObj.setUnicodeFlag(true);
        List<String> textList = fontRenderObj.listFormattedStringToWidth(introText, Page.PAGE_WIDTH - 3);
        fontRenderObj.setUnicodeFlag(uni);


        String managedString = "";
        String forNextLine = "";
        for(String string : textList) {
            int newLineCount = 0;
            string = forNextLine + string;
            forNextLine = "";
            for(char character : string.toCharArray()) {
                if(character == '¶')
                    newLineCount++;
            }
            String[] split = string.split("¶");
            managedString += split[0] + " ";
            for(int i = 0; i < newLineCount; i++)
                managedString += "\n ";
            for(String left : split) {
                if(!left.equals(split[0]) && !left.equals("¶") && !left.isEmpty())
                    forNextLine = left + " ";
            }
        }

        // Manage new lines
        List<String> introTextList = fontRenderObj.listFormattedStringToWidth(managedString, Page.PAGE_WIDTH + 20);

        // Create page one
        ArrayList<String> pageOneText = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            if(i < introTextList.size())
                pageOneText.add(introTextList.get(i));
            else
                break;
        }

        pages.add(new PageIntroduction(true, pageOneText));

        // Add more pages as needed
        if(introTextList.size() >= 8) {
            Queue<String> leftoverText = new LinkedTransferQueue<>();
            // Pull the strings left to new list
            for(int i = 8; i < introTextList.size(); i++)
                leftoverText.add(introTextList.get(i));

            // Loop as many times as we need pages
            while(!leftoverText.isEmpty()) {
                // Create new list to store this pages text
                ArrayList<String> pageText = new ArrayList<>();
                for(int j = 0; j < 32; j++) {
                    if(!leftoverText.isEmpty())
                        pageText.add(leftoverText.poll());
                }
                pages.add(new PageIntroduction(false, pageText));
            }
        }
    }
}
