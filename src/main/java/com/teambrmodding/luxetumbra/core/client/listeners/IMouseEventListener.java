package com.teambrmodding.luxetumbra.core.client.listeners;

import com.teambrmodding.luxetumbra.core.client.elements.Element;

/**
 * This file was created for Lux-et-Umbra-Redux
 * <p>
 * Lux-et-Umbra-Redux is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * The interface to allow an object to react to mouse events
 *
 * @author Paul Davis <pauljoda>
 * @since 10/8/2016
 */
public interface IMouseEventListener {

    /**
     * Called when the mouse clicks on the component
     * @param element The element to be clicked
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param button Which button was clicked
     */
    void onMouseDown(Element element, int mouseX, int mouseY, int button);

    /**
     * Called when the mouse releases the component
     * @param element The element to be clicked
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param button Which button was clicked
     */
    void onMouseUp(Element element, int mouseX, int mouseY, int button);

    /**
     * Called when the mouse drags an item
     * @param element The element to be clicked
     * @param mouseX X position of the mouse
     * @param mouseY Y position of the mouse
     * @param button Which button was clicked
     * @param time How long its been clicked
     */
    void onMouseDrag(Element element, int mouseX, int mouseY, int button, long time);

    /**
     * Called when the mouse is scrolled
     * @param dir The direction of the scroll. 1 for a positive (down), -1 for negative (up)
     */
    void onMouseScrolled(int dir);
}
