package sham1.manacraft.manipulation.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

public class ManaCollectorTileEntity extends TileEntity implements ITickable, IInventory{

    private ItemStack storageStack;

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public Packet<INetHandlerPlayClient> getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);

        return new S35PacketUpdateTileEntity(pos, 1, tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("storageStack")) storageStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("storageStack"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (storageStack != null) {
            NBTTagCompound storageStackTag = new NBTTagCompound();
            storageStack.writeToNBT(storageStackTag);
            tag.setTag("storageStack", storageStackTag);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return storageStack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = storageStack;
        if (stack != null) {
            if (stack.stackSize <= count) {
                storageStack = null;
            } else {
                stack = stack.splitStack(count);
                if (stack.stackSize == 0) storageStack = null;
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack copy = storageStack.copy();
        storageStack = null;
        return copy;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        storageStack = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
