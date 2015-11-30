package sham1.manacraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import sham1.manacraft.common.container.ManaCollectorContainer;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;

public class ManaCollectorGui extends GuiContainer {

    public ManaCollectorGui(InventoryPlayer inv, ManaCollectorTileEntity te) {
        super(new ManaCollectorContainer(inv, te));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        ResourceLocation background = new ResourceLocation("manacraft", "texture/gui/manaCollector.png");
        mc.getTextureManager().bindTexture(background);

        int x = (width - xSize)/2;
        int y = (height - ySize)/2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
