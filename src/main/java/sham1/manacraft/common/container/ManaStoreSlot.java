package sham1.manacraft.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ManaStoreSlot extends Slot {
    public ManaStoreSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}