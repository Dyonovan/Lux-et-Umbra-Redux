package com.teambrmodding.luxetumbra.utils;

import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import java.text.NumberFormat;
import java.util.Locale;

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
public class ClientUtils {

    public static String MODID    = Constants.MOD_ID;             // Used to hold a reference locally
    public static String RESOURCE = MODID.toLowerCase(Locale.US); // Easier access to resource path

    /**
     * Formats a string to the language selected
     * @param text Text to translate
     * @return Translated text
     */
    public static String translate(String text) {
        return I18n.format(text);
    }

    /**
     * Used to format a number for the current language selected
     * @param number The number
     * @return The formatted number
     */
    public static String formatNumber(double number) {
        return NumberFormat.getNumberInstance(Locale.forLanguageTag(Minecraft.getMinecraft().gameSettings.language))
                .format(number);
    }
}
