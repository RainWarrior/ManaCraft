package sham1.manacraft.client;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import sham1.manacraft.client.tesr.ManaItemPedistalTESR;
import sham1.manacraft.common.CommonProxy;
import sham1.manacraft.common.ManaItems;
import sham1.manacraft.manipulation.tileentity.ManaItemPedistalTileEntity;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer() {
        ModelLoader.setCustomModelResourceLocation(ManaItems.manaRedirectingDeviceItem, 0, new ModelResourceLocation("manacraft:mana_redirection_device", "inventory"));
    }

    @Override
    public void registerBlockItemRenderer() {

    }

    @Override
    public void registerTESRs() {
        ClientRegistry.bindTileEntitySpecialRenderer(ManaItemPedistalTileEntity.class, new ManaItemPedistalTESR());
    }
}
