package com.teambrmodding.luxetumbra.documentation;

import com.teambrmodding.luxetumbra.core.container.ContainerGeneric;
import com.teambrmodding.luxetumbra.documentation.data.Page;
import com.teambrmodding.luxetumbra.documentation.data.pages.intro.PageIntroduction;
import com.teambrmodding.luxetumbra.documentation.data.pages.misc.ErrorPage;
import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.utils.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.Stack;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 10/5/2016
 */
public class GuiBook extends GuiContainer {

    /*******************************************************************************************************************
     * Static Objects                                                                                                  *
     *******************************************************************************************************************/

    /**
     * The public instance of the book, always load from here. Allows for saving info between book opens
     */
    public static GuiBook INSTANCE = new GuiBook();
    /**
     * The location of the texture with all main book related objects
     */
    public static final ResourceLocation textureLocation = new ResourceLocation(Constants.MOD_ID, "textures/gui/book.png");

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The currently displayed page
     */
    private Page currentPage;

    /**
     * The previous pages viewed, only add to stack when jumping, not flipping pages
     */
    private Stack<Page> previousPages;

    /**
     * Button to go to previous page
     */
    private GuiBookButton previousArrow;

    /**
     * Button to go to next page
     */
    private GuiBookButton nextArrow;

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

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

        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        GlStateManager.translate(guiLeft, guiTop, 0);

        // Right Page
        drawTexturedModalRect(140, 0, 0, 0, 140, 180);

        // Left Page
        GlStateManager.disableCull();
        drawReverseTexturedModalRect(140, 0 , 0, 0, 140, 180);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();

        // Draw the Page
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        RenderUtils.prepareRenderState();

        getCurrentPage().drawBackground(guiLeft + Page.CORNER_OFFSET, guiTop + Page.CORNER_OFFSET, mouseX, mouseY);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
        RenderUtils.restoreColor();
        RenderUtils.restoreRenderState();
    }

    /**
     * Draw the top layer, holds text, itemstacks, and other persist top obejcts
     * @param mouseX The mouse x
     * @param mouseY The mouse y
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // Draw the Page
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        RenderUtils.prepareRenderState();

        getCurrentPage().drawForeground(guiLeft, guiTop, mouseX, mouseY);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
        RenderUtils.restoreColor();
        RenderUtils.restoreRenderState();
    }

    /**
     * Main render call, we are breaking out to send the final layer to the page
     * @param mouseX The mouse X position
     * @param mouseY The mouse Y position
     * @param partialTicks The partial ticks
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        getCurrentPage().renderLastLayer(this, guiLeft + Page.CORNER_OFFSET, guiTop + Page.CORNER_OFFSET, mouseX, mouseY);
    }

    /**
     * Add buttons to GUI
     */
    @Override
    public void initGui() {
        super.initGui();

        previousArrow = new GuiBookButton(0, guiLeft - 2,          guiTop + ySize - 10, 21, 10, 3, 207, 26, 207);
        nextArrow     = new GuiBookButton(1, guiLeft + xSize - 20, guiTop + ySize - 10, 21, 10, 0, 194, 23, 194);

        previousArrow.visible = getCurrentPage().pageNumber != 0;
        nextArrow.visible     = getCurrentPage().pageNumber != Documentation.pages.size() - 1;

        buttonList.clear();
        buttonList.add(previousArrow);
        buttonList.add(nextArrow);
    }

    /**
     * Update Buttons
     */
    @Override
    public void updateScreen() {
        super.updateScreen();

        previousArrow.visible = getCurrentPage().pageNumber != 0;
        nextArrow.visible     = getCurrentPage().pageNumber != Documentation.pages.size() - 1;
    }

    /**
     * Respond to buttons
     * @param button The button
     */
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        int currentPageNumber = getCurrentPage().pageNumber;

        switch (button.id) {
            case 0 : // Go back
                currentPageNumber--;
                if(currentPageNumber < 0)
                    currentPageNumber = 0;
                currentPage = Documentation.pages.get(currentPageNumber);
                break;
            case 1 : // Go forward
                currentPageNumber++;
                if(currentPageNumber >= Documentation.pages.size())
                    currentPageNumber = Documentation.pages.size() - 1;
                currentPage = Documentation.pages.get(currentPageNumber);
                break;
            default :
        }
    }

    /*******************************************************************************************************************
     * Input Methods                                                                                                   *
     *******************************************************************************************************************/

    /**
     * Called when the mouse is clicked
     * @param mouseX The mouse x pos
     * @param mouseY The mouse y pos
     * @param mouseButton The mouse button
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        getCurrentPage().mouseDown(guiLeft + Page.CORNER_OFFSET, guiTop + Page.CORNER_OFFSET,
                mouseX, mouseY, mouseButton);
    }

    /**
     * Called when the mouse is released
     * @param mouseX The mouse x pos
     * @param mouseY The mouse y pos
     * @param state The button
     */
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        getCurrentPage().mouseUp(guiLeft + Page.CORNER_OFFSET, guiTop + Page.CORNER_OFFSET,
                    mouseX, mouseY, state);
    }

    /**
     * Called when the mouse is clicked and held to drag
     * @param mouseX The mouse x pos
     * @param mouseY the mouse y pos
     * @param clickedMouseButton The mouse button
     * @param timeSinceLastClick The time held
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        getCurrentPage().mouseDrag(guiLeft + Page.CORNER_OFFSET, guiTop + Page.CORNER_OFFSET,
                mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    /**
     * Used to handle scrolling
     */
    @Override
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
        int scrollDirection = Mouse.getEventDWheel();
        if(scrollDirection != 0)
            getCurrentPage().mouseScrolled(scrollDirection > 0 ? 1 : -1);
    }

    /**
     * Called when a key is typed
     * @param typedChar The typed char
     * @param keyCode The corresponding key code
     */
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        getCurrentPage().keyTyped(typedChar, keyCode);
    }

    /*******************************************************************************************************************
     * Helper Methods                                                                                                  *
     *******************************************************************************************************************/

    /**
     * Method to ensure a valid page is returned. If current page is null for some reason,
     *  the title page will be set to the current page
     * @return A valid page
     */
    private Page getCurrentPage() {
        if(currentPage == null && Documentation.pages.size() == 0)
            currentPage = ErrorPage.INSTANCE;
        else if(currentPage == null)
            currentPage = Documentation.pages.get(0);
        return currentPage;
    }

    /**
     * Changes the GUI to a new page, stores the old page
     * @param page The new page to display
     */
    public void jumpToPage(Page page) {
        previousPages.add(currentPage);
        currentPage = page;
    }

    /**
     * Helper method for us to draw a textured rectangle but backwards
     */
    public void drawReverseTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
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
