package com.teambrmodding.luxetumbra.client;

import com.teambrmodding.luxetumbra.common.CommonProxy;
import com.teambrmodding.luxetumbra.documentation.client.DocumentationClient;

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
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        DocumentationClient.preInit();
    }

    @Override
    public void init() {
        DocumentationClient.init();
    }

    @Override
    public void postInit() {
        DocumentationClient.postInit();
    }
}
