package com.teambrmodding.luxetumbra.utils;

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
public class ColorUtils {

    /**
     * Used to get the color that is at the point between two colors
     * @param first The first color
     * @param second The second color
     * @param progress How far into the spectrum
     * @return A color that is at that point specified in the gradient
     */
    public static Color getColorBetween(Color first, Color second, float progress) {
        float inverseProgress = 1 - progress;

        double red   = second.getRed()   * progress + first.getRed()   * inverseProgress;
        double green = second.getGreen() * progress + first.getGreen() * inverseProgress;
        double blue  = second.getBlue()  * progress + first.getBlue()  * inverseProgress;
        double alpha = second.getAlpha() * progress + first.getAlpha() * inverseProgress;

        return new Color((int) red, (int) green, (int) blue, (int) alpha);
    }
}
