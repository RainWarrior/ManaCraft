package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import sham1.manacraft.manipulation.tileentity.ManaNodeTileEntity;

public class ManaNodeBlock extends Block{

    public ManaNodeBlock() {
        super(Material.glass);
    }

    public static final PropertyDirection DIRECTION = PropertyDirection.create("direction");

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        if (worldIn.getTileEntity(pos) != null) {
            ManaNodeTileEntity te = (ManaNodeTileEntity) worldIn.getBlockState(pos);
        }
        return state;
    }
}
