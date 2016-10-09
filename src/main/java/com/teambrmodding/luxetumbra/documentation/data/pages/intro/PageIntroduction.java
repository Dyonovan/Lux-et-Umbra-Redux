package com.teambrmodding.luxetumbra.documentation.data.pages.intro;

import com.teambrmodding.luxetumbra.core.client.elements.Element;
import com.teambrmodding.luxetumbra.core.client.elements.ElementText;
import com.teambrmodding.luxetumbra.documentation.data.Page;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

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
public class PageIntroduction extends Page {

    /**
     * Tells us if this is the first page
     */
    private boolean firstPage;

    /**
     * The lines of text to display
     */
    private List<String> displayText;

    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    public PageIntroduction(boolean isFirst, List<String> text) {
        firstPage = isFirst;
        displayText = new ArrayList<>();
        displayText.addAll(text);
        addPageElementsLate();
    }

    /**
     * Add the elements for this page
     * @param elements The object to hold the elements
     */
    @Override
    protected void addPageElements(ArrayList<Element> elements) {
        if(displayText != null) {
            if (firstPage)
                elements.add(new ElementText("page.intro.title",
                        Page.RIGHT_PAGE_X_OFFSET + (Page.PAGE_WIDTH / 2),
                        20, 2.0, true, ElementText.ENUM_TEXT_ALIGN.CENTER));

            int y = !firstPage ? 4 : 70;
            int mod = 0;
            int num = 1;
            for (String string : displayText) {
                elements.add(new ElementText(string, 5 + (num > 16 || firstPage ? Page.RIGHT_PAGE_X_OFFSET : 5),
                        y + mod, 1.0, true, ElementText.ENUM_TEXT_ALIGN.LEFT));
                mod += 9;
                num++;
                if(num == 17)
                    mod = 0;
            }
        }
    }
}
