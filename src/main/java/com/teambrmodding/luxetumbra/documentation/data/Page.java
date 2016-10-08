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
     * Static Objects                                                                                                  *
     *******************************************************************************************************************/

    /**
     * Used as a counter to manipulate the available page. The page constructor will take this page and move it up one
     */
    private static int NEXT_AVAILABLE_PAGE = 0;

    /**
     * Used to get the next page number and modify static value
     * @return The page number to assign to a page
     */
    public int recievePageNumber() {
        int returnValue = NEXT_AVAILABLE_PAGE;
        NEXT_AVAILABLE_PAGE++;
        return returnValue;
    }

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

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
    private ArrayList<Object> searchObjects = new ArrayList<>();

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

    /**
     * Override to render extra info besides the elements
     * @param guiLeft The left edge of the render space
     * @param guiTop The top edge of the render space
     * @param mouseX The mouse X
     * @param mouseY The mouse Y
     */
    public void renderExtras(int guiLeft, int guiTop, int mouseX, int mouseY) {}

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

    /**
     * Default constructor for page object, passes null as object array
     */
    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    public Page() {
        this(null);
    }

    /**
     * Creates a page object
     * @param searchObjects The objects that can be used to find this page, pass null for none
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public Page(Object ... searchObjects) {
        if(searchObjects.length > 0 && searchObjects[0] != null) {
            for(Object obj : searchObjects)
                this.searchObjects.add(obj);
        }
        addLeftPageElements(leftPageElements);
        addRightPageElements(rightPageElements);
    }

    /*******************************************************************************************************************
     * Render Methods                                                                                                  *
     *******************************************************************************************************************/


}
