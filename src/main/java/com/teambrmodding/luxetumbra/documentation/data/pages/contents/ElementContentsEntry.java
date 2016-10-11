package com.teambrmodding.luxetumbra.documentation.data.pages.contents;

import com.teambrmodding.luxetumbra.core.client.elements.Element;
import com.teambrmodding.luxetumbra.core.client.listeners.IMouseEventListener;
import com.teambrmodding.luxetumbra.documentation.GuiBook;
import com.teambrmodding.luxetumbra.documentation.data.Page;
import com.teambrmodding.luxetumbra.documentation.data.entries.Entry;
import com.teambrmodding.luxetumbra.utils.ColorUtils;
import com.teambrmodding.luxetumbra.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

import java.awt.*;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 10/9/2016
 */
public class ElementContentsEntry extends Element {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The text to render
     */
    private Entry entry;

    /**
     * A local instance of the font render obj
     */
    private FontRenderer fontRenderObj;

    /**
     * The time this element has been hovered over
     */
    private int timeHovered;

    /**
     * Max time for hover
     */
    private final int MAX_HOVER = 20;

    /*******************************************************************************************************************
     * Constructor                                                                                                     *
     *******************************************************************************************************************/

    /**
     * The constructor for this element
     * @param displayEntry The entry to display, DO NOT CHANGE ANYTHING HERE OR IN HELD VALUE!
     */
    public ElementContentsEntry(int x, int y, final Entry displayEntry) {
        super(x, y);
        entry = displayEntry;

        // Add a mouse event listener
        setMouseEventListener(new IMouseEventListener() {
            @Override
            public void onMouseDown(Element element, int mouseX, int mouseY, int button) {
                GuiBook.INSTANCE.jumpToPage(entry.getPages().get(0).pageNumber);
            }
            @Override
            public void onMouseUp(Element element, int mouseX, int mouseY, int button) {}
            @Override
            public void onMouseDrag(Element element, int mouseX, int mouseY, int button, long time) {}
            @Override
            public void onMouseScrolled(int dir) {}
        });
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Called by super constructor, do things here before main constructor
     */
    @Override
    public void initialize() {
        fontRenderObj = Minecraft.getMinecraft().fontRendererObj;
        timeHovered = 0;
    }

    /**
     * We don't need this for this element
     */
    @Override
    public void render(int guiLeft, int guiTop, int mouseX, int mouseY) {}

    /**
     * Renders all the things we need, stack if there, text, and hover
     * @param guiLeft The location of the left edge of the parent gui
     * @param guiTop The location of the right edge of the parent gui
     * @param mouseX The mouse X Location
     * @param mouseY The mouse Y Location
     */
    @Override
    public void renderOverlay(int guiLeft, int guiTop, int mouseX, int mouseY) {
        // Manage Hover State
        if(isMouseOver(mouseX - getXPos(), mouseY - getYPos())) {
            timeHovered += 2;
            if(timeHovered > getWidth() + 2)
                timeHovered = getWidth() + 2;
        } else {
            timeHovered -= 2;
            if(timeHovered < -10)
                timeHovered = -10;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(getXPos(), getYPos(), 0);

        boolean hasItemStack = true;

        // Render Itemstack
        if(hasItemStack) {
            RenderHelper.enableGUIStandardItemLighting();
            RenderUtils.pushGl();

            double scale = 0.6;
            GlStateManager.scale(scale, scale, 0);

            int x = (int) (2 / scale);
            int y = (int) (-1.5 / scale);

            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(entry.getDisplayStack(), x, y);

            RenderUtils.popGl();
            RenderHelper.disableStandardItemLighting();
        }

        // Render Title
        boolean prevUnicode = fontRenderObj.getUnicodeFlag();
        fontRenderObj.setUnicodeFlag(true);

        // Draw Title
        int xPosBase = (hasItemStack ? 14 : 2);
        fontRenderObj.drawString(entry.getEntryTitle(), xPosBase, 0, 0x000000);

        // Pad with dots
        int titleWidth   = fontRenderObj.getStringWidth(entry.getEntryTitle());
        int pageNumWidth = fontRenderObj.getStringWidth(String.valueOf(entry.getFirstPage()));
        int dotsWidth    = getWidth() - titleWidth - pageNumWidth - xPosBase;
        int dotWidth     = fontRenderObj.getCharWidth('.');
        String dots = " ";
        for(int i = 5; i < dotsWidth; i += dotWidth)
            dots += ".";
        fontRenderObj.drawString(dots, xPosBase + titleWidth, 0, 0x000000);

        // Draw page num
        fontRenderObj.drawString(String.valueOf(entry.getFirstPage()), xPosBase + titleWidth + dotsWidth,
                0, 0x000000);

        fontRenderObj.setUnicodeFlag(prevUnicode);

        // Render Hover Info
        RenderUtils.pushGl();
        Minecraft.getMinecraft().getTextureManager().bindTexture(GuiBook.textureLocation);
        // Part one of recovering from itemstack renderer
        GlStateManager.disableBlend();
        for(int i = 0; i < timeHovered; i++) {
            RenderUtils.pushGl();
            RenderUtils.prepareRenderState();
            GlStateManager.translate((i - 1), -2, 0);
            GlStateManager.scale(1, getHeight() + 3, 1);
            RenderUtils.setColor(getColorForHover(i));
            // Need to call this after every render, itemStack render left stack dirty
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            drawTexturedModalRect(0, 0, 40, 50, 1, 1);
            // Need to call this after every render, itemStack render left stack dirty
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
            RenderUtils.restoreColor();
            RenderUtils.popGl();
        }

        RenderUtils.popGl();
        GlStateManager.popMatrix();
    }

    /**
     * Used to get what color to render in the hover
     * @param pos The position in the gradient
     * @return The color to render
     */
    private Color getColorForHover(int pos) {
        // Grab what pixel location for gradient
        float colorPosition = (float) pos / (getWidth() + 2);
        return ColorUtils.getColorBetween(new Color(151, 0, 255, 80), new Color(255, 216, 15, 80), colorPosition);
    }

    /*******************************************************************************************************************
     * Accessors and mutators                                                                                          *
     *******************************************************************************************************************/

    /**
     * Width, we are always the same so return the same width here
     */
    @Override
    public int getWidth() {
        return Page.PAGE_WIDTH - 5;
    }

    /**
     * Pretty much the height of the font
     */
    @Override
    public int getHeight() {
        return 9;
    }
}
