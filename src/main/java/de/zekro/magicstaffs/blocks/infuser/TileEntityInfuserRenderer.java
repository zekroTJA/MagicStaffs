package de.zekro.magicstaffs.blocks.infuser;

import de.zekro.magicstaffs.MagicStaffs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class TileEntityInfuserRenderer extends TileEntitySpecialRenderer<TileEntityInfuser>
{
    public static final ResourceLocation BASESTAFF = new ResourceLocation(MagicStaffs.MOD_ID, "textures/blocks/infuser_staff.png");

    public final ModelStaff baseStaff = new ModelStaff();

    public void render(TileEntityInfuser te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.pushMatrix();

        float f0 = te.tickCount + partialTicks;
        float f1 = te.bookRotation;

        GlStateManager.translate(x + 0.5F, y + MathHelper.sin(f0 * 0.125F) * 0.0625F + 1.25F, z + 0.5F);
        GlStateManager.rotate(-f1 * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);

        this.bindTexture(BASESTAFF);
        this.baseStaff.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        GlStateManager.popMatrix();
    }
}