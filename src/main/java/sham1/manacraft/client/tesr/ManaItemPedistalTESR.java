package sham1.manacraft.client.tesr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.OpenGLException;
import sham1.manacraft.manipulation.tileentity.ManaItemPedistalTileEntity;

public class ManaItemPedistalTESR extends TileEntitySpecialRenderer <ManaItemPedistalTileEntity>{

    int time = 0;

    @Override
    public void renderTileEntityAt(ManaItemPedistalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        if (te.getHeldItem().isPresent()) {
            System.out.println("Rendering pedistal item");
            ItemStack stack = te.getHeldItem().get();

            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();

            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);

            int lightMap = te.getWorld().getBlockState(te.getPos()).getBlock().getMixedBrightnessForBlock(te.getWorld(), te.getPos());
            int light1 = lightMap % 0x10000;
            int light2 = lightMap / 0x10000;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, light1, light2);

            bindTexture(TextureMap.locationBlocksTexture);
            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

            time++;

            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
}
