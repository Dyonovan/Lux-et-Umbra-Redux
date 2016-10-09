package com.teambrmodding.luxetumbra.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

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
            /*var gs = 0.25F
            if(seg % 2 == 0)
                gs += 0.1F
            var r = if(upgrades(seg)._2) gs else 200
            var g = if(!upgrades(seg)._2) gs else 200
            val b = gs
            var a = 0.4F
            if(mouseOverSection) {
                selectedUpgrade = seg
                wasSelected = true
                r = if(upgrades(seg)._2) r else 255
                g = if(!upgrades(seg)._2) g else 255
                a = 0.5F
            }
            GlStateManager.color(r, g, b, a)
            GL11.glVertex2i(x, y)
            var i = degPer
            while(i >= 0) {
                val rad = ((i + totalDeg) / 180F * Math.PI).toFloat
                val xp = x + Math.cos(rad) * radius
                val yp = y + math.sin(rad) * radius
                if(i == (degPer / 2).toInt)
                    stringPosition += Array(seg, xp.toInt, yp.toInt, if(mouseOverSection) 'n' else 'r')
                GL11.glVertex2d(xp, yp)
                i -= 1
            }
            totalDeg += degPer

            GL11.glVertex2i(x, y)
            GL11.glEnd()

            if(mouseOverSection)
                radius -= highlight*/
        }

    }

    private float mouseDistance(int x, int y, int mx, int my) {
        return 0;
    }

    private float mouseAngle(int x, int y, int mx, int my) {
        return 0;
    }
}
