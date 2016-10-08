package com.teambrmodding.luxetumbra.documentation;

import com.teambrmodding.luxetumbra.api.font.FontHelper;
import com.teambrmodding.luxetumbra.api.font.FontLoader;
import com.teambrmodding.luxetumbra.api.font.TrueTypeFont;
import com.teambrmodding.luxetumbra.core.container.ContainerGeneric;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis <pauljoda>
 * @since 10/5/2016
 */
public class GuiBook extends GuiContainer {

    /**
     * The location of the texture with all main book related objects
     */
    private static final ResourceLocation textureLocation = new ResourceLocation(Constants.MOD_ID, "textures/gui/book.png");

    /**
     * The public instance of the book, always load from here. Allows for saving info between book opens
     */
    public static GuiBook INSTANCE = new GuiBook();

    /**
     * Constructor, uses fake container for ease of use
     */
    public GuiBook() {
        super(new ContainerGeneric());
        xSize = 280;
        ySize = 180;
    }


    /*******************************************************************************************************************
     * Render Methods                                                                                                  *
     *******************************************************************************************************************/

    /**
     * Draws the back of the gui. Render things here that need to be behind text, itemstack, and things like that
     * @param partialTicks The partial ticks of the render
     * @param mouseX Mouse X
     * @param mouseY Mouse Y
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // Draw The Book
        this.mc.getTextureManager().bindTexture(textureLocation);

        GlStateManager.translate(guiLeft, guiTop, 0);

        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        // Right Page
        drawTexturedModalRect(140, 0, 0, 0, 140, 180);

        // Left Page
        GlStateManager.disableCull();
        drawReverseTexturedModalRect(140, 0 , 0, 0, 140, 180);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    /**
     * Draw the top layer, holds text, itemstacks, and other persist top obejcts
     * @param mouseX The mouse x
     * @param mouseY The mouse y
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        //Test Of Font Renderer
        TrueTypeFont arial = FontLoader.loadSystemFont("Arial", 16f, false);
        float[] white = {1f,1f,1f,1f};
        float[] black = {0F, 0F, 0F, 0F};
        FontHelper.drawString("Hello World", 1, 1, arial, 1f, 1f, black);

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    /**
     * Helper method for us to draw a textured rectangle but backwards
     */
    public void drawReverseTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(x), (double)(y), (double)this.zLevel).tex((double)((float)(textureX) * 0.00390625F), (double)((float)(textureY) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x - width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(x - width), (double)(y), (double)this.zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
}
