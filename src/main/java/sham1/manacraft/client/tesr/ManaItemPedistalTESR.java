package sham1.manacraft.client.tesr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import sham1.manacraft.manipulation.tileentity.ManaItemPedistalTileEntity;

public class ManaItemPedistalTESR extends TileEntitySpecialRenderer <ManaItemPedistalTileEntity>{
    @Override
    public void renderTileEntityAt(ManaItemPedistalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        if (te.getHeldItem().isPresent()) {
            System.out.println("Rendering pedistal item");
            ItemStack stack = te.getHeldItem().get();

            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();

            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);

            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
}
