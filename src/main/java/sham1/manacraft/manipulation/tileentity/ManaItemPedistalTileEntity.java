package sham1.manacraft.manipulation.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Optional;

public class ManaItemPedistalTileEntity extends TileEntity{
    private ItemStack heldStack;

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(pos, 0, tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("heldStack")) heldStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("heldStack"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (heldStack != null)  {
            NBTTagCompound itemTag = new NBTTagCompound();
            heldStack.writeToNBT(itemTag);
            tag.setTag("heldStack", itemTag);
        }
    }

    public void setHeldItem(ItemStack stack) {
        heldStack = stack;
        worldObj.markBlockForUpdate(pos);
        markDirty();
    }

    public Optional<ItemStack> getHeldItem() {
        return Optional.ofNullable(heldStack);
    }
}
