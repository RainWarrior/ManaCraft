package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import sham1.manacraft.ManaCraft;

import java.util.Optional;

public class ManaCollectorBlock extends Block {

    public ManaCollectorBlock() {
        super (Material.iron);
        setCreativeTab(ManaCraft.creativeTab);
    }

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        Optional<EntityLivingBase> optPlacer = Optional.ofNullable(placer);
        EnumFacing dir = optPlacer.flatMap(x -> Optional.ofNullable(x.getHorizontalFacing())).orElse(EnumFacing.NORTH);

        state.withProperty(FACING, dir);
    }
}
