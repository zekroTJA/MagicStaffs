package de.zekro.magicstaffs.gui;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.assembler.ContainerAssembler;
import de.zekro.magicstaffs.blocks.assembler.TileEntityAssembler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAssembler extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MagicStaffs.MOD_ID, "textures/gui/assembler.png");
    private final InventoryPlayer player;
    private final TileEntityAssembler entity;

    public GuiAssembler(InventoryPlayer player, TileEntityAssembler entity) {
        super(new ContainerAssembler(player, entity));
        this.player = player;
        this.entity = entity;

        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tileName = entity.getDisplayName().getUnformattedText();
        fontRenderer.drawString(tileName, (xSize / 2 - fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
        fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 8, 81 - 10, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
