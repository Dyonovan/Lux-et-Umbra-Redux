package com.teambrmodding.luxetumbra.documentation.data.pages.intro;

import com.teambrmodding.luxetumbra.core.client.elements.Element;
import com.teambrmodding.luxetumbra.core.client.elements.ElementText;
import com.teambrmodding.luxetumbra.documentation.data.Page;

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
public class PageTitle extends Page {

    /**
     * Instance of this page
     */
    public static PageTitle INSTANCE = new PageTitle();

    /**
     * Add the elements of this page
     * @param elements The object to hold the elements
     */
    @Override
    protected void addPageElements(ArrayList<Element> elements) {
        // University
        elements.add(new ElementText("page.title.university1", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 10, 0.6,
                true, ElementText.ENUM_TEXT_ALIGN.CENTER));
        elements.add(new ElementText("page.title.university2", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 15, 0.6,
                true, ElementText.ENUM_TEXT_ALIGN.CENTER));

        // Title
        elements.add(new ElementText("page.title.title", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 53, 2.0, true,
                ElementText.ENUM_TEXT_ALIGN.CENTER));

        // Subtitle
        elements.add(new ElementText("page.title.subtitle", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 72, 0.75,
                true, ElementText.ENUM_TEXT_ALIGN.CENTER));

        // Credits
        elements.add(new ElementText("page.title.credit1", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 130, 0.6,
                true, ElementText.ENUM_TEXT_ALIGN.CENTER));
        elements.add(new ElementText("page.title.credit2", RIGHT_PAGE_X_OFFSET + (PAGE_WIDTH / 2), 135, 0.6,
                true, ElementText.ENUM_TEXT_ALIGN.CENTER));

    }
}
