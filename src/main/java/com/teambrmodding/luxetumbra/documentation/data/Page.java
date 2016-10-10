package com.teambrmodding.luxetumbra.documentation.data;

import com.teambrmodding.luxetumbra.core.client.elements.Element;
import com.teambrmodding.luxetumbra.utils.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;

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

    /**
     * The right page offset, add to x to make element on right page
     */
    public static final int RIGHT_PAGE_X_OFFSET = 140;

    /**
     * A single page width
     */
    public static final int PAGE_WIDTH = 115;

    /**
     * A single page height
     */
    public static final int PAGE_HEIGHT = 155;

    /**
     * The offset for the corner, keeps info on page and not in unreadable area
     */
    public static final int CORNER_OFFSET = 10;

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The elements for the left page
     */
    private ArrayList<Element> elements = new ArrayList<>();

    /**
     * The page number of this book
     */
    public int pageNumber;

    /**
     * An array to hold objects used in searching for this page. Use static objects for best results
     */
    private ArrayList<Object> searchObjects = new ArrayList<>();

    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Called by constructor to add page elements
     * @param elements The object to hold the elements
     */
    protected abstract void addPageElements(final ArrayList<Element> elements);

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

    /**
     * Forces page creation with next constructor
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
        if(searchObjects != null && searchObjects.length > 0) {
            for(Object obj : searchObjects)
                this.searchObjects.add(obj);
        }
        addPageElements(elements);
        pageNumber = recievePageNumber();
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Used to add the elements after child constructor. Needed when elements need access to data from constructor
     *
     * Call at the end of your constructor to add call addPageElements again
     */
    protected void addPageElementsLate() {
        addPageElements(elements);
    }

    /*******************************************************************************************************************
     * Render Methods                                                                                                  *
     *******************************************************************************************************************/

    /**
     * Render the background of the page, call all elements and render
     * @param guiLeft The guiLeft of the parent
     * @param guiTop The guiTop of the parent
     * @param mouseX The mouse X
     * @param mouseY The mouse Y
     */
    public void drawBackground(int guiLeft, int guiTop, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        RenderUtils.prepareRenderState();

        // Translate for components
        GlStateManager.translate(guiLeft, guiTop, 0);

        // Render Page elements
        for(Element element : elements) {
            element.render(0, 0, mouseX - guiLeft, mouseY - guiTop);
            RenderUtils.restoreColor();
        }

        RenderUtils.restoreRenderState();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }

    /**
     * Render the foreground of the page, call all elements and render
     * @param guiLeft The guiLeft of the parent
     * @param guiTop The guiTop of the parent
     * @param mouseX The mouse X
     * @param mouseY The mouse Y
     */
    public void drawForeground(int guiLeft, int guiTop, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();

        GlStateManager.translate(CORNER_OFFSET, CORNER_OFFSET, 0);

        // Render Left Page elements
        for(Element element : elements) {
            element.renderOverlay(0, 0, mouseX - guiLeft, mouseY - guiTop);
            RenderUtils.restoreColor();
        }

        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }

    /**
     * Called after all render calls, mainly used for tool tip
     * @param parent The parent GUI
     * @param guiLeft The gui left
     * @param guiTop The gui top
     * @param mouseX The mouseX
     * @param mouseY The mouseY
     */
    public void renderLastLayer(GuiScreen parent, int guiLeft, int guiTop, int mouseX, int mouseY) {
        elements.stream().filter(element -> element.isMouseOver(mouseX - guiLeft, mouseY - guiTop))
                .forEach(element -> element.renderToolTip(mouseX, mouseY, parent));
    }

    /*******************************************************************************************************************
     * Input Methods                                                                                                   *
     *******************************************************************************************************************/

    /**
     * Called by {@link com.teambrmodding.luxetumbra.documentation.GuiBook} when mouse is clicked
     * @param guiLeft The gui left
     * @param guiTop The gui top
     * @param mouseX The mouse X Pos
     * @param mouseY The mouse Y Pos
     * @param button The button pressed
     */
    public void mouseDown(int guiLeft, int guiTop, int mouseX, int mouseY, int button) {
        elements.stream().filter(element -> element.isMouseOver(mouseX - guiLeft, mouseY - guiTop))
                .forEach(element -> element.mouseDown(mouseX - guiLeft, mouseY - guiTop, button));
    }

    /**
     * Called by {@link com.teambrmodding.luxetumbra.documentation.GuiBook} when mouse is released
     * @param guiLeft The gui left
     * @param guiTop The gui top
     * @param mouseX The mouse X Pos
     * @param mouseY The mouse Y Pos
     * @param button The button released
     */
    public void mouseUp(int guiLeft, int guiTop, int mouseX, int mouseY, int button) {
        elements.stream().filter(element -> element.isMouseOver(mouseX - guiLeft, mouseY - guiTop))
                .forEach(element -> element.mouseUp(mouseX - guiLeft, mouseY - guiTop, button));
    }

    /**
     * Called by {@link com.teambrmodding.luxetumbra.documentation.GuiBook} when mouse is moved
     * @param guiLeft The gui left
     * @param guiTop The gui top
     * @param mouseX The mouse X Pos
     * @param mouseY The mouse Y Pos
     * @param button The button released
     * @param time How long mouse has dragged
     */
    public void mouseDrag(int guiLeft, int guiTop, int mouseX, int mouseY, int button, long time) {
        elements.stream().filter(element -> element.isMouseOver(mouseX - guiLeft, mouseY - guiTop))
                .forEach(element -> element.mouseDrag(mouseX - guiLeft, mouseY - guiTop, button, time));
    }

    /**
     * Called by {@link com.teambrmodding.luxetumbra.documentation.GuiBook} when mouse is scrolled
     * @param dir the scroll direction
     */
    public void mouseScrolled(int dir) {
        elements.forEach(element -> element.mouseScrolled(dir));
    }

    /**
     * Called by {@link com.teambrmodding.luxetumbra.documentation.GuiBook} when a key is typed
     * @param letter The char that was typed
     * @param keyCode The corresponding key code
     */
    public void keyTyped(char letter, int keyCode) {
        elements.forEach(element -> element.keyTyped(letter, keyCode));
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    /**
     * Get the search objects
     * @return valid search objects for this page
     */
    public ArrayList<Object> getSearchObjects() {
        return searchObjects;
    }
}
