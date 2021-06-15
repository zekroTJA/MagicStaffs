package de.zekro.magicstaffs.blocks.infuser;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStaff extends ModelBase {
    public ModelRenderer baseStaff;

    public ModelStaff() {
        textureWidth = 16;
        textureHeight = 16;

        baseStaff = new ModelRenderer(this);

        setRotationAngle(baseStaff, -1.75F, 0.0F, 0.0F);

        baseStaff.setRotationPoint(0.0F, -5.0F, 0.0F);

        baseStaff.cubeList.add(new ModelBox(baseStaff, 0, 0, -0.5F, -5.0F, -0.5F, 1, 10, 1, 0.0F, false));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(Entity entity, float f0, float f1, float f2, float f3, float f4, float f5) {
        baseStaff.render(f5);
    }
}