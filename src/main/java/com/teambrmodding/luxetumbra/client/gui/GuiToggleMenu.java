package com.teambrmodding.luxetumbra.client.gui;

import com.teambrmodding.luxetumbra.client.KeybindHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 10/8/2016
 */
public class GuiToggleMenu extends GuiScreen {

    private int timeIn = 0;
    private int selected = -1;

    @Override
    public void drawScreen(int mx, int my, float partialTicks) {
        super.drawScreen(mx, my, partialTicks);

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();

        final int x = width / 2;
        final int y = height / 2;
        final int maxRadius = 60;

        final boolean mouseIn = true;
        final float angle = mouseAngle(x, y, mx, my);
        final float distance = mouseDistance(x, y, mx, my);

        final int highlight = 5;

        GlStateManager.enableBlend();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        final int segments = 2; //TODO
        float totalDeg = 0F;
        final float degPer = 360F / segments;

        final List<int[]> stringPosition = new ArrayList<>();

        boolean wasSelected = false;

        for (int seg = 0; seg < segments; seg++) {
            boolean mouseOverSection = distance <= maxRadius && (mouseIn && angle > totalDeg && angle < totalDeg + degPer);
            float radius = Math.max(0F, Math.min(timeIn * 10F, maxRadius));

            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            float gs = 0.25F;
            if(seg % 2 == 0)
                gs += 0.1F;
            float r = 200; //TODO
            float g = 200; //TODO
            float b = gs;
            float a = 0.4F;
            if(mouseOverSection) {
                selected = seg;
                wasSelected = true;
                r = 255; //TODO
                g = 255; //TODO
                a = 0.5F;
            }
            GlStateManager.color(r, g, b, a);
            GL11.glVertex2i(x, y);
            float i = degPer;
            while(i >= 0) {
                double rad = ((i + totalDeg) / 180F * Math.PI);
                double xp = x + Math.cos(rad) * radius;
                double yp = y + Math.sin(rad) * radius;
                if(i == (int)(degPer / 2))
                    stringPosition.add(new int[]{seg, (int)xp, (int)yp, mouseOverSection ? 'n' : 'r'});
                GL11.glVertex2d(xp, yp);
                i -= 1;
            }
            totalDeg += degPer;

            GL11.glVertex2i(x, y);
            GL11.glEnd();

            if(mouseOverSection)
                radius -= highlight;
        }

        if (!wasSelected)
            selected = -1;

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableTexture2D();

        for(int[] pos : stringPosition) {
            int slot = pos[0];
            float xp = pos[1];
            int yp = pos[2];
            char c = (char)pos[3];

            ItemStack displayStack = new ItemStack(Items.APPLE);
            if(displayStack != null) {
                float xsp = xp - 4;
                int ysp = yp;
                String name = "\u00a7" + c + "testing";
                int width = fontRendererObj.getStringWidth(name);

                float mod = 0.6F;
                int xdp = (int)((xp - x) * mod + x);
                int ydp = (int)((yp - y) * mod + y);

                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.pushMatrix();
                GlStateManager.translate(xdp - 10, ydp - 10, 2);
                GlStateManager.scale(1.25, 1.25, 1.25);
                Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(displayStack, 0, 0);
                GlStateManager.popMatrix();
                RenderHelper.disableStandardItemLighting();

                if(xsp < x)
                    xsp -= width - 8;
                if(ysp < y)
                    ysp -= 9;

                fontRendererObj.drawStringWithShadow(name, xsp, ysp, 0xFFFFFF);
            }
        }

        GlStateManager.popMatrix();
    }

    @Override
    public void updateScreen() {
        if (!isKeyDown(KeybindHandler.getRadialMenu())) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private boolean isKeyDown(KeyBinding keyBinding) {
        int key = keyBinding.getKeyCode();
        if (key < 0) {
            int button = 100 + key;
            return Mouse.isButtonDown(button);
        }
        return Keyboard.isKeyDown(key);
    }

    private float mouseDistance(int x, int y, int mx, int my) {
        return  (int)Math.abs(Math.sqrt(((mx - x) * (mx - x)) + ((my - y) * (my - y))));
    }

    private float mouseAngle(int x, int y, int mx, int my) {
        Vector2f baseVec = new Vector2f(1F, 0F);
        Vector2f mouseVec = new Vector2f(mx - x, my - y);
        Float angle = (float)(Math.acos(Vector2f.dot(baseVec, mouseVec) / (baseVec.length() * mouseVec.length())) * (180F / Math.PI));
        return my < y ? 360F - angle : angle;
    }
}
