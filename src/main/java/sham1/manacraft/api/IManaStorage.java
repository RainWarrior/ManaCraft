package sham1.manacraft.api;

import net.minecraft.util.EnumFacing;

public interface IManaStorage {
    /**
     * Gets the amount of mana stored in a given side. Pass null if you do not care about the side.
     * @param facing The side you query.
     * @return The amount of mana stored.
     */
    int getManaStored(EnumFacing facing);

    /**
     * Gets the max amount of mana that you can store on a given side. Pass null if you do not care which side you get.
     * @param facing The side you are querying.
     * @return The max amount of mana you can have.
     */
    int getMaxManaStorage(EnumFacing facing);

    /**
     * Sets the amount of mana stored in a given side. Pass null for side if you do not care what side you are setting.<br/><br />Most useful for server -&gt client synchronicity.
     * @param facing The face you want to set.
     * @param amount The amount you want to set.
     */
    void setManaStored(EnumFacing facing, int amount);
}
