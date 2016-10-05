package com.teambrmodding.luxetumbra.documentation;

import com.teambrmodding.luxetumbra.LuxetUmbra;
import com.teambrmodding.luxetumbra.lib.Constants;
import net.minecraft.item.Item;

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
public class ItemBook extends Item {

    public ItemBook() {
        super();
        setUnlocalizedName(Constants.MOD_ID + ":book");
        setCreativeTab(LuxetUmbra.tabLuxetUmbra);
    }
}
