package com.teambrmodding.luxetumbra.core.client.elements;

import com.teambrmodding.luxetumbra.utils.ClientUtils;
import com.teambrmodding.luxetumbra.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
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
public class ElementText extends Element {

    /**
     * The enum for text alignment
     */
    public enum ENUM_TEXT_ALIGN {
        LEFT,
        RIGHT,
        CENTER
    }

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The text to render
     */
    private String text;

    /**
     * The scale to render the text
     */
    private double scale;

    /**
     * Flag to use unicode
     */
    private boolean useUnicode;

    /**
     * The text alignment
     */
    private ENUM_TEXT_ALIGN alignment = ENUM_TEXT_ALIGN.LEFT;

    /**
     * A local instance of the font render obj
     */
    private FontRenderer fontRenderObj;

    /*******************************************************************************************************************
     * Constructors                                                                                                    *
     *******************************************************************************************************************/

    /**
     * Default constructor
     */
    public ElementText() {
        this("", 0, 0);
    }

    /**
     * Constructor to use default values, just sends text
     * @param text
     */
    public ElementText(String text, int xPos, int yPos) {
        this(text, xPos, yPos, 1.0, false, ENUM_TEXT_ALIGN.LEFT);
    }

    /**
     * Main constructor
     * @param displayText The text to display
     * @param displayScale The display scale
     * @param displayInUnicode The unicode flag
     * @param displayAlignment Alignment, if set to CENTER, x is now used as center point, if right x is end point
     */
    public ElementText(String displayText, int xPos, int yPos, double displayScale,
                       boolean displayInUnicode, ENUM_TEXT_ALIGN displayAlignment) {
        super(xPos, yPos);
        text = ClientUtils.translate(displayText);
        scale = displayScale;
        useUnicode = displayInUnicode;
        alignment = displayAlignment;
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Called by constructor to start up element
     */
    @Override
    public void initialize() {
        fontRenderObj = Minecraft.getMinecraft().fontRendererObj;
    }

    /**
     * Render the background
     * @param guiLeft The location of the left edge of the parent gui
     * @param guiTop The location of the right edge of the parent gui
     * @param mouseX The mouse X Location
     * @param mouseY The mouse Y Location
     */
    @Override
    public void render(int guiLeft, int guiTop, int mouseX, int mouseY) {
        // NO - OP
    }

    /**
     * Render the Overlay
     * @param guiLeft The location of the left edge of the parent gui
     * @param guiTop The location of the right edge of the parent gui
     * @param mouseX The mouse X Location
     * @param mouseY The mouse Y Location
     */
    @Override
    public void renderOverlay(int guiLeft, int guiTop, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        RenderUtils.prepareRenderState();

        // Scale GL Matrix
        GlStateManager.scale(scale, scale, 0);

        // Manage Unicode
        boolean prevUnicodeStatus = fontRenderObj.getUnicodeFlag();
        fontRenderObj.setUnicodeFlag(useUnicode);

        int x;
        int y = (int) (getYPos() / scale);

        switch (alignment) {
            case RIGHT :
                x = (int) ((getXPos() / scale) - fontRenderObj.getStringWidth(text));
                break;
            case CENTER :
                x = (int) ((getXPos() / scale) - (fontRenderObj.getStringWidth(text) / 2));
                break;
            case LEFT :
            default :
                x = (int) (getXPos() / scale);
        }

        fontRenderObj.drawString(text, x, y, 0x000000);

        fontRenderObj.setUnicodeFlag(prevUnicodeStatus);
        RenderUtils.restoreColor();
        RenderUtils.restoreRenderState();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }

    /*******************************************************************************************************************
     * Accessors / Mutators                                                                                            *
     *******************************************************************************************************************/

    /**
     * Get the width
     * @return width
     */
    @Override
    public int getWidth() {
        return (int) (fontRenderObj.getStringWidth(text) * scale);
    }

    /**
     * Get the height
     * @return height
     */
    @Override
    public int getHeight() {
        return (int) (fontRenderObj.FONT_HEIGHT * scale);
    }

    /**
     * Get the text
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text
     * @param text Text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the scale
     * @return The scale
     */
    public double getScale() {
        return scale;
    }

    /**
     * Set the scale
     * @param scale The scale to set
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Checks if using unicode
     * @return True if using unicode
     */
    public boolean isUseUnicode() {
        return useUnicode;
    }

    /**
     * Set flag for unicode
     * @param useUnicode Flag for unicode
     */
    public void setUseUnicode(boolean useUnicode) {
        this.useUnicode = useUnicode;
    }

    /**
     * Get the alignment
     * @return The alignment
     */
    public ENUM_TEXT_ALIGN getAlignment() {
        return alignment;
    }

    /**
     * Set the alignment
     * @param alignment The new alignment
     */
    public void setAlignment(ENUM_TEXT_ALIGN alignment) {
        this.alignment = alignment;
    }
}
