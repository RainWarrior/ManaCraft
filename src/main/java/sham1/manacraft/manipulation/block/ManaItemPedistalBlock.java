package sham1.manacraft.manipulation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import sham1.manacraft.ManaCraft;
import sham1.manacraft.manipulation.tileentity.ManaItemPedistalTileEntity;

public class ManaItemPedistalBlock extends Block {

    public ManaItemPedistalBlock() {
        super(Material.rock);
        setCreativeTab(ManaCraft.creativeTab);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new ManaItemPedistalTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem() == null) {
                ManaItemPedistalTileEntity te = (ManaItemPedistalTileEntity) worldIn.getTileEntity(pos);

                if (te.getHeldItem().isPresent()) {
                    ItemStack stack = te.getHeldItem().get();

                    EntityItem itemEntity = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, stack);
                    worldIn.spawnEntityInWorld(itemEntity);

                    te.setHeldItem(null);
                }

            } else {
                ManaItemPedistalTileEntity te = (ManaItemPedistalTileEntity) worldIn.getTileEntity(pos);

                if (!te.getHeldItem().isPresent()) {
                    ItemStack stack = playerIn.getHeldItem().copy();
                    stack.stackSize = 1;

                    playerIn.getHeldItem().stackSize = playerIn.getHeldItem().stackSize - 1;
                    if (playerIn.getHeldItem().stackSize == 0) playerIn.inventory.setItemStack(null);

                    te.setHeldItem(stack);
                }
            }
        }

        return true;
    }
}
