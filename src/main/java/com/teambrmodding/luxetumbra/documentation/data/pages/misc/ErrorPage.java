package com.teambrmodding.luxetumbra.documentation.data.pages.misc;

import com.teambrmodding.luxetumbra.core.client.elements.Element;
import com.teambrmodding.luxetumbra.core.client.elements.display.ElementText;
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
public class ErrorPage extends Page {

    /**
     * Instance of this page
     */
    public static ErrorPage INSTANCE = new ErrorPage();

    /**
     * Add the elements of this page
     *
     * @param elements The object to hold the elements
     */
    @Override
    protected void addPageElements(ArrayList<Element> elements) {
        // Title
        elements.add(new ElementText("§cError opening book§r", 140, 45, 1.5, true, ElementText.ENUM_TEXT_ALIGN.CENTER));
        elements.add(new ElementText("Please report to mod authors", 140, 58, 1.5, true, ElementText.ENUM_TEXT_ALIGN.CENTER));
    }
}