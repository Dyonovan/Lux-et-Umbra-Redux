package com.teambrmodding.luxetumbra;

import com.teambrmodding.luxetumbra.common.CommonProxy;
import com.teambrmodding.luxetumbra.lib.Constants;
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

    public static final Logger logger = LogManager.getLogger(Constants.MOD_NAME);

    @SidedProxy(clientSide = "com.teambrmodding.luxetumbra.client.ClientProxy",
            serverSide = "com.teambrmodding.luxetumbra.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabLuxetUmbra = new CreativeTabs("tabLuxEtUmbra") {
        @Override
        public Item getTabIconItem() {
            return ItemManager.book;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        ItemManager.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }
}
