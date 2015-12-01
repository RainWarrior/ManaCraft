package sham1.manacraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import sham1.manacraft.api.GenericPair;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;
import sham1.manacraft.packets.ManaUpdatePacket;
import sham1.manacraft.packets.ManaCraftPackets;

import java.util.List;
import java.util.stream.Collectors;

public class ManaCollectorContainer extends Container{

    private ManaCollectorTileEntity te;

    public ManaCollectorContainer(InventoryPlayer playerInv, ManaCollectorTileEntity te) {
        this.te = te;
        addSlotToContainer(new ManaStoreSlot(te, 0, 25, 35));

        bindPlayerInventory(playerInv);
    }

    public void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = null;
        Slot slotObject = inventorySlots.get(index);

        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (index < te.getSizeInventory()) {
                if (!this.mergeItemStack(stackInSlot, te.getSizeInventory(), 36+te.getSizeInventory(), true))
                    return null;
            } else if (!this.mergeItemStack(stackInSlot, 0, te.getSizeInventory(), false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(playerIn, stackInSlot);
        }

        return stack;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        ManaUpdatePacket packet = new ManaUpdatePacket(new ManaUpdatePacket.ManaUpdateJavabean(te.getPos(), null, te.getManaStored(null)));

        List<EntityPlayerMP> playerCrafters = crafters.stream()
                .filter(x -> x instanceof EntityPlayerMP)
                .map(x -> (EntityPlayerMP) x)
                .collect(Collectors.toList());

        playerCrafters.forEach(player -> ManaCraftPackets.sendPacketTo(packet, player));
    }
}
