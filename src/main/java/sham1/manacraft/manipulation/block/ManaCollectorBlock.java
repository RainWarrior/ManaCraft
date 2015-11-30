package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import sham1.manacraft.ManaCraft;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;

public class ManaCollectorBlock extends Block {

    public ManaCollectorBlock() {
        super (Material.iron);
        setCreativeTab(ManaCraft.creativeTab);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new ManaCollectorTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && worldIn.getTileEntity(pos) != null) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof ManaCollectorTileEntity && !playerIn.isSneaking()) {
                playerIn.openGui(ManaCraft.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }
        return false;
    }
}
