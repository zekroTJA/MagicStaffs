package de.zekro.magicstaffs.gui;

import de.zekro.magicstaffs.MagicStaffs;
import de.zekro.magicstaffs.blocks.infuser.ContainerInfuser;
import de.zekro.magicstaffs.blocks.infuser.TileEntityInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * The GUI of the Infusion Table.
 */
public class GuiInfuser extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MagicStaffs.MOD_ID, "textures/gui/infuser.png");
    private final InventoryPlayer player;
    private final TileEntityInfuser entity;

    /**
     * Create new instance of GuiInfuser;
     * @param player inventory player instance
     * @param entity block tile entity instance
     */
    public GuiInfuser(InventoryPlayer player, TileEntityInfuser entity) {
        super(new ContainerInfuser(player, entity));
        this.player = player;
        this.entity = entity;

        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tileName = entity.getDisplayName().getUnformattedText();
        fontRenderer.drawString(tileName, 8, 8, 4210752);
        fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 8, 81 - 10, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen (int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

}
