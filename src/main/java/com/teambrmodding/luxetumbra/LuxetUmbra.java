package com.teambrmodding.luxetumbra;

import com.teambrmodding.luxetumbra.common.CommonProxy;
import com.teambrmodding.luxetumbra.lib.Constants;
import com.teambrmodding.luxetumbra.manager.GuiMananger;
import com.teambrmodding.luxetumbra.manager.ItemManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
@Mod(modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        version = Constants.VERSION,
        dependencies = Constants.DEPENDENCIES,
        updateJSON = Constants.UPDATE_JSON)
public class LuxetUmbra {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    /**
     * The Mod instance, use this when asked for MOD
     */
    @Mod.Instance(Constants.MOD_ID)
    public static LuxetUmbra INSTANCE;

    /**
     * The logger will print to the console with our mod name, use when needing to pass info
     */
    public static final Logger logger = LogManager.getLogger(Constants.MOD_NAME);

    /**
     * Proxy object, is changed based on side, class defined in annotation variables
     */
    @SidedProxy(clientSide = "com.teambrmodding.luxetumbra.client.ClientProxy",
            serverSide = "com.teambrmodding.luxetumbra.common.CommonProxy")
    public static CommonProxy proxy;

    /**
     * Easiest way to create a creative tab quickly. Things get easier all the time
     */
    public static CreativeTabs tabLuxetUmbra = new CreativeTabs("tabLuxEtUmbra") {
        @Override
        public Item getTabIconItem() {
            return ItemManager.book;
        }
    };

    /*******************************************************************************************************************
     * Loading Methods                                                                                                 *
     *******************************************************************************************************************/

    /**
     * Called in the preInit Stage, this is when to register things
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        ItemManager.preInit();

        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiMananger());
    }

    /**
     * At this stage, things should be registered, manipulate variables/mod interaction here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    /**
     * Called after all changes are to have been made, use to open managers/objects and functional bits
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    /**
     * Server load event, used mainly for us to register commands
     * @param event
     */
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) { }
}
