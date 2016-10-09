package com.teambrmodding.luxetumbra.client;

import com.teambrmodding.luxetumbra.client.gui.GuiToggleMenu;
import com.teambrmodding.luxetumbra.common.items.RadialMenu;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
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
public class KeybindHandler {

    private static final KeybindHandler instance = new KeybindHandler();

    private final KeyBinding radialMenu = new KeyBinding(I18n.format("luxetembra.text.radialMenuKey"), Keyboard.KEY_G, Constants.MOD_NAME);

    public void registerBindings() {
        ClientRegistry.registerKeyBinding(radialMenu);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (radialMenu.isPressed()) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof RadialMenu) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiToggleMenu());
            }
        }
    }

    /*******************************************************************************************************************
     * Accessors and Mutators                                                                                          *
     *******************************************************************************************************************/

    public KeyBinding getRadialMenu() {
        return radialMenu;
    }

    public static KeybindHandler getInstance() {
        return instance;
    }
}
