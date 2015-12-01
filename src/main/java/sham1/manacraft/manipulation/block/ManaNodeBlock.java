package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import sham1.manacraft.ManaCraft;

public class ManaNodeBlock extends Block{

    public ManaNodeBlock() {
        super(Material.glass);
        setCreativeTab(ManaCraft.creativeTab);
    }

    public static final PropertyDirection DIRECTION = PropertyDirection.create("direction");

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(DIRECTION).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DIRECTION, EnumFacing.getFront(meta));
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, DIRECTION);
    }
}
