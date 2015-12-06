package sham1.manacraft.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sham1.manacraft.item.ManaRedirectionDeviceItem;

public class ManaItems {

    public static Item manaRedirectingDeviceItem;

    public static void registerItems() {
        manaRedirectingDeviceItem = new ManaRedirectionDeviceItem();

        manaRedirectingDeviceItem.setUnlocalizedName("mana_redirection_device");
        GameRegistry.registerItem(manaRedirectingDeviceItem, "mana_redirection_device");
    }
}
