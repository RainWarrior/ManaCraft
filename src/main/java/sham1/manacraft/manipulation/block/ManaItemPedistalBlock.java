package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ManaItemPedistalBlock extends Block {

    public ManaItemPedistalBlock() {
        super(Material.rock);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return super.createTileEntity(world, state);
    }
}
