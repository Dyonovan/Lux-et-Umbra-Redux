package com.teambrmodding.luxetumbra.documentation.client;

import com.teambrmodding.luxetumbra.client.ItemRenderManager;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis <pauljoda>
 * @since 10/4/2016
 */
public class DocumentationClient {

    public static void preInit() {}

    public static void init() {
        ItemRenderManager.registerItemRenderers();
    }

    public static void postInit() {}
}
