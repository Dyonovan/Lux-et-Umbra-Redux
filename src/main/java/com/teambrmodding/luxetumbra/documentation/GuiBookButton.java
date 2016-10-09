package com.teambrmodding.luxetumbra.documentation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

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
public class GuiBookButton extends GuiButton {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/
    /**
     * The non hovered u
     */
    private int textureU;

    /**
     * The non hovered v
     */
    private int textureV;

    /**
     * The hovered u
     */
    private int hoveredU;

    /**
     * The hovered v
     */
    private int hoveredV;

    /*******************************************************************************************************************
     * Constructor                                                                                                     *
     *******************************************************************************************************************/

    /**
     * Constructor for all buttons in the book
     * @param buttonId The button id
     * @param x The x position
     * @param y The y position
     * @param w The width
     * @param h The height
     * @param u The normal texture u
     * @param v The normal texture v
     * @param hu The hovered texture u
     * @param hv The hovered texture v
     */
    public GuiBookButton(int buttonId, int x, int y,
                         int w, int h,
                         int u, int v, int hu, int hv) {
        super(buttonId, x, y, " ");
        textureU = u;
        textureV = v;
        hoveredU = hu;
        hoveredV = hv;
        this.width = w;
        this.height = h;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.enableBlend();

            mc.getTextureManager().bindTexture(GuiBook.textureLocation);

            this.hovered = mouseX >= this.xPosition &&
                    mouseY >= this.yPosition &&
                    mouseX < this.xPosition + this.width &&
                    mouseY < this.yPosition + this.height;

            drawTexturedModalRect(xPosition, yPosition,
                    hovered ? hoveredU : textureU, hovered ? hoveredV : textureV,
                    width, height);

            GlStateManager.disableBlend();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
}
