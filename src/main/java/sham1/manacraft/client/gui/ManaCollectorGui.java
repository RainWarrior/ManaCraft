package sham1.manacraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sham1.manacraft.common.container.ManaCollectorContainer;
import sham1.manacraft.manipulation.tileentity.ManaCollectorTileEntity;

public class ManaCollectorGui extends GuiContainer {

    ManaCollectorTileEntity te;

    public ManaCollectorGui(InventoryPlayer inv, ManaCollectorTileEntity te) {
        super(new ManaCollectorContainer(inv, te));
        this.te = te;
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

        fontRendererObj.drawString(I18n.format("mana.amount", te.getManaStored(null), te.getMaxManaStorage(null)), 10, 20, 0xFFFFFFFF, true);

        float manaPropotion = te.getManaStored(null) / te.getMaxManaStorage(null);

        drawRect(48, 41, Math.round(manaPropotion), 46, 0xFF00B7FB);
    }
}
