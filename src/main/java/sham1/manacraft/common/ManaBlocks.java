package sham1.manacraft.common;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sham1.manacraft.manipulation.block.ManaCollectorBlock;
import sham1.manacraft.manipulation.block.ManaItemPedistalBlock;
import sham1.manacraft.manipulation.block.ManaNodeBlock;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;
import sham1.manacraft.manipulation.tileentity.ManaItemPedistalTileEntity;
import sham1.manacraft.manipulation.tileentity.ManaNodeTileEntity;

public class ManaBlocks {

    public static final Block manaCollector = new ManaCollectorBlock();
    public static final Block manaNode = new ManaNodeBlock();
    public static final Block manaItemPedistal = new ManaItemPedistalBlock();

    public static void registerBlocks() {
        manaCollector.setUnlocalizedName("mana_collector");
        GameRegistry.registerBlock(manaCollector, "mana_collector");

        manaNode.setUnlocalizedName("mana_node");
        GameRegistry.registerBlock(manaNode, "mana_node");

        manaItemPedistal.setUnlocalizedName("mana_pedistal");
        GameRegistry.registerBlock(manaItemPedistal, "mana_pedistal");
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(ManaCollectorTileEntity.class, "mana_collector");
        GameRegistry.registerTileEntity(ManaNodeTileEntity.class, "mana_node");
        GameRegistry.registerTileEntity(ManaItemPedistalTileEntity.class, "mana_pedistal");
    }
}
