package com.teambrmodding.luxetumbra.core.client.elements;

import com.teambrmodding.luxetumbra.core.client.listeners.IKeyboardEventListener;
import com.teambrmodding.luxetumbra.core.client.listeners.IMouseEventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * The element is a self contained instance of a display for the book. IE picture, text, etc...
 *
 * @author Paul Davis - pauljoda
 * @since 10/8/2016
 */
public abstract class Element extends Gui {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The x and y pos of this element
     */
    private int xPos, yPos;

    /**
     * The object that holds this tool tip. Used for static tool tips
     */
    protected ArrayList<String> toolTip = new ArrayList<>();

    /**
     * Listener for the mouse events, null by default for no extra actions
     */
    private IMouseEventListener mouseEventListener = null;

    /**
     * Listener for keyboard events, null by default for no extra actions
     */
    private IKeyboardEventListener keyboardEventListener = null;

    /*******************************************************************************************************************
     * Abstract Methods                                                                                                *
     *******************************************************************************************************************/

    /**
     * Called from constructor. Set up anything needed here
     */
    public abstract void initialize();

    /**
     * Render the base of the component here. First render call
     * @param guiLeft The location of the left edge of the parent gui
     * @param guiTop The location of the right edge of the parent gui
     * @param mouseX The mouse X Location
     * @param mouseY The mouse Y Location
     */
    public abstract void render(int guiLeft, int guiTop, int mouseX, int mouseY);

    /**
     * Render the top of the component here, text and things on top should go here. Second render call
     * @param guiLeft The location of the left edge of the parent gui
     * @param guiTop The location of the right edge of the parent gui
     * @param mouseX The mouse X Location
     * @param mouseY The mouse Y Location
     */
    public abstract void renderOverlay(int guiLeft, int guiTop, int mouseX, int mouseY);

    /**
     * Used to access the width of the object
     * @return The width of the object
     */
    public abstract int getWidth();

    /**
     * Used to access the height of the object
     * @return The height of the object
     */
    public abstract int getHeight();

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

    /**
     * Default Constructor
     */
    public Element() {
        this(0, 0);
    }

    /**
     * Main constructor
     * @param xPosition The x position of this element
     * @param yPosition The y position of this element
     */
    public Element(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
        initialize();
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Used to get the area of this element
     * @param guiLeft The parent guiLeft
     * @param guiTop The parent guiTOp
     * @return A {@link Rectangle} covering the area this element covers
     */
    public Rectangle getArea(int guiLeft, int guiTop) {
        return new Rectangle(xPos + guiLeft, yPos + guiTop, getWidth(), getHeight());
    }

    public void renderToolTip(int mouseX, int mouseY, GuiScreen parent) {
        if(toolTip != null && !toolTip.isEmpty())
            drawHoveringText(toolTip, mouseX, mouseY, parent, Minecraft.getMinecraft().fontRendererObj);
        else if(getDynamicToolTip(mouseX, mouseY) != null)
            drawHoveringText(getDynamicToolTip(mouseX, mouseY), mouseX, mouseY, parent, Minecraft.getMinecraft().fontRendererObj);

    }

    /**
     * Override this method to enable a dynamic tool tip. Otherwise pass the static one
     * @param mouseX The mouseX
     * @param mouseY The mouseY
     * @return An array of strings to display
     */
    public ArrayList<String> getDynamicToolTip(int mouseX, int mouseY) { return null; }

    /**
     * Called when the mouse is pressed, called from parent gui
     * @param x The mouse x position
     * @param y The mouse y position
     * @param button The mouse button
     */
    public void mouseDown(int x, int y, int button) {
        if(mouseEventListener != null) mouseEventListener.onMouseDown(this, x, y, button);
    }

    /**
     * Called when the mouse is released, called from parent gui
     * @param x The mouse x position
     * @param y The mouse y position
     * @param button The mouse button
     */
    public void mouseUp(int x, int y, int button) {
        if(mouseEventListener != null) mouseEventListener.onMouseUp(this, x, y, button);
    }

    /**
     * Called when the mouse is dragged, called from parent gui
     * @param x The mouse x position
     * @param y The mouse y position
     * @param button The mouse button
     * @param time How long the drag has happened
     */
    public void mouseDrag(int x, int y, int button, long time) {
        if(mouseEventListener != null) mouseEventListener.onMouseDrag(this, x, y, button, time);
    }

    /**
     * Called when the mouse is scrolled, called from parent gui
     * @param dir The direction of the scroll
     */
    public void mouseScrolled(int dir) {
        if(mouseEventListener != null) mouseEventListener.onMouseScrolled(dir);
    }

    /**
     * Used to check if the mouse is over this element
     * @param mouseX The mouse x position
     * @param mouseY The mouse y position
     * @return True if mouse is over this element
     */
    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= xPos && mouseX < xPos + getWidth() && mouseY >= yPos && mouseY < yPos + getHeight();
    }

    /**
     * Called when a key is typed, called from parent gui
     * @param letter The character typed
     * @param keyCode The keycode
     */
    public void keyTyped(char letter, int keyCode) {
        if(keyboardEventListener != null) keyboardEventListener.keyTyped(this, letter, keyCode);
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    /**
     * Used to get the X Position of this element
     * @return The xPos of this element
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Used to set the X Position of this object
     * @param pos The new pos
     */
    public void setXPos(int pos) {
        xPos = pos;
    }

    /**
     * Used to get the Y Position of this element
     * @return The yPos of this element
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Used to set the Y position of this element
     * @param pos The new pos
     */
    public void setYPos(int pos) {
        yPos = pos;
    }

    /**
     * Used to get the tool tip list
     * @return The list of tool tips
     */
    public ArrayList<String> getToolTip() {
        return toolTip;
    }

    /**
     * Used to set the tool tip. Warning, do not modify passed object after calling
     * @param tip The tool tip
     */
    public void setToolTip(ArrayList<String> tip) {
        toolTip = tip;
    }

    /**
     * Get the Mouse Event Listener
     * @return The {@link IMouseEventListener} or null if none defined
     */
    public IMouseEventListener getMouseEventListener() {
        return mouseEventListener;
    }

    /**
     * Used to set the mouseEventListener to the passed variable
     * @param iMouseEventListener The {@link IMouseEventListener} to attach to this element
     */
    public void setMouseEventListener(IMouseEventListener iMouseEventListener) {
        mouseEventListener = iMouseEventListener;
    }

    /**
     * Used to get the instance of the keyboard event listener
     * @return The keyboard event listener
     */
    public IKeyboardEventListener getKeyboardEventListener() {
        return keyboardEventListener;
    }

    /**
     * Used to set the keyboardEventListener
     * @param iKeyboardEventListener The {@link IKeyboardEventListener} to attach to this element
     */
    public void setKeyboardEventListener(IKeyboardEventListener iKeyboardEventListener) {
        keyboardEventListener = iKeyboardEventListener;
    }

    /*******************************************************************************************************************
     * Helper Methods                                                                                                  *
     *******************************************************************************************************************/

    /**
     * Local copy of drawHoveringText using parent as base
     * @param toolTip The toolTip
     * @param mouseX The mouse X
     * @param mouseY The mouse Y
     * @param parent The parent GUI
     * @param font The fontRenderObj
     */
    protected void drawHoveringText(ArrayList<String> toolTip, int mouseX, int mouseY, GuiScreen parent, FontRenderer font) {
        if (!toolTip.isEmpty()) {
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, 0.0, 5);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            for(String s : toolTip) {
                int l = font.getStringWidth(s);
                if (l > k) {
                    k = l;
                }
            }
            int j2 = mouseX + 12;
            int k2 = mouseY - 12;
            int i1 = 8;
            if (toolTip.size() > 1) {
                i1 += 2 + (toolTip.size() - 1) * 10;
            }
            if (j2 + k > parent.width) {
                j2 -= 28 + k;
            }
            if (k2 + i1 + 6 > parent.height) {
                k2 = this.getHeight() - i1 - 6;
            }
            this.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for(int i2 = 0; i2 < toolTip.size(); i2++) {
                String s1 = toolTip.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);
                if(i2 == 0)
                    k2 += 2;
                k2 += 10;
            }

            this.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }
}
