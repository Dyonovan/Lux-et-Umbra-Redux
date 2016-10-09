package com.teambrmodding.luxetumbra.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
public class ClientTickHandler {

    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (KeybindHandler.getRadialMenu().isKeyDown())
                KeybindHandler.keyPressed(KeybindHandler.getRadialMenu());
        }
    }
}
