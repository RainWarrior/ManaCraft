package sham1.manacraft.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sham1.manacraft.manipulation.block.ManaCollectorBlock;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;

public class ManaBlocks {

    public static final Block manaCollector = new ManaCollectorBlock();

    public static void registerBlocks() {
        manaCollector.setUnlocalizedName("mana_collector");
        GameRegistry.registerBlock(manaCollector, "mana_collector");
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(ManaCollectorTileEntity.class, "mana_collector");
    }
}
