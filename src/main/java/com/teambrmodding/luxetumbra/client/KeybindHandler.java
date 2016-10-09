package com.teambrmodding.luxetumbra.client;

import com.teambrmodding.luxetumbra.client.gui.GuiToggleMenu;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

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
class KeybindHandler {

    private static KeyBinding radialMenu = new KeyBinding(I18n.format("luxetembra.text.radialMenuKey"), Keyboard.KEY_G, Constants.MOD_NAME);

    static void registerBindings() {
        ClientRegistry.registerKeyBinding(radialMenu);
    }

    static void keyPressed(KeyBinding binding) {
        int radialMenuKey = radialMenu.getKeyCode();

        if (binding.getKeyCode() == radialMenuKey) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiToggleMenu());
        }
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    static KeyBinding getRadialMenu() {
        return radialMenu;
    }
}
