package sham1.manacraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import sham1.manacraft.common.CommonProxy;
import sham1.manacraft.common.ManaBlocks;
import sham1.manacraft.common.ManaItems;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import sham1.manacraft.handles.GuiHandle;
import sham1.manacraft.packets.ManaCraftPackets;

@Mod(modid = "manacraft", name = "ManaCraft", version = "1.8.8-0.0.0.0")
public class ManaCraft {

    public static CreativeTabs creativeTab = new CreativeTabs("manacraft_tab") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ManaBlocks.manaCollector);
        }
    };

    @Mod.Instance
    public static ManaCraft INSTANCE;

    @SidedProxy(clientSide = "sham1.manacraft.client.ClientProxy", serverSide = "sham1.manacraft.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ManaCraftPackets.registerNetwork();
        ManaCraftPackets.registerPackets(proxy);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandle());

        ManaItems.registerItems();
        ManaBlocks.registerBlocks();
        ManaBlocks.registerTileEntities();

        proxy.registerItemRenderer();
        proxy.registerBlockItemRenderer();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
