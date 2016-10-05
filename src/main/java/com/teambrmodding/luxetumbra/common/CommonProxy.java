package com.teambrmodding.luxetumbra.common;

import com.teambrmodding.luxetumbra.documentation.common.DocumentationCommon;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 10/3/2016
 */
public class CommonProxy {

    public void preInit() {
        DocumentationCommon.preInit();
    }

    public void init() {
        DocumentationCommon.init();
    }

    public void postInit() {
        DocumentationCommon.postInit();
    }
}
