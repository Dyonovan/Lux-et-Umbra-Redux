package com.teambrmodding.luxetumbra.core.client.listeners;

import com.teambrmodding.luxetumbra.core.client.elements.Element;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * The interface that allows the object to listen and react to keyboard events
 *
 * @author Paul Davis - pauljoda
 * @since 10/8/2016
 */
public interface IKeyboardEventListener {

    /**
     * Called when the keyboard is pressed
     * @param element The element getting the input
     * @param character The character pressed
     * @param keyCode The key code
     */
    void keyTyped(Element element, char character, int keyCode);
}
