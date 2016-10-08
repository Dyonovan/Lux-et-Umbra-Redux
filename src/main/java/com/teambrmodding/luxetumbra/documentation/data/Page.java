package com.teambrmodding.luxetumbra.documentation.data;

import com.teambrmodding.luxetumbra.core.client.elements.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * The lowest level of the book. The page is responsible for holding the page number, search objects, and display
 *
 * @author Paul Davis - pauljoda
 * @since 10/6/2016
 */
public abstract class Page {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * Used as a counter to manipulate the available page. The page constructor will take this page and move it up one
     */
    private static int NEXT_AVAILABLE_PAGE = 0;

    /**
     * The elements for the left page
     */
    private ArrayList<Element> leftPageElements  = new ArrayList<>();

    /**
     * The elements for the right page
     */
    private ArrayList<Element> rightPageElements = new ArrayList<>();

    /**
     * An array to hold objects used in searching for this page. Use static objects for best results
     */
    private ArrayList<Objects> searchObjects = new ArrayList<>();

    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Called by constructor to add left page elements
     * @param elements The object to hold the elements
     */
    protected abstract void addLeftPageElements(final ArrayList<Element> elements);

    /**
     * Called by constructor to add right page elements
     * @param elements The object to hold the elements
     */
    protected abstract void addRightPageElements(final ArrayList<Element> elements);
}
