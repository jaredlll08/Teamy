package com.blamejared.teamy.client;

import com.blamejared.teamy.Teamy;
import com.blamejared.teamy.api.Team;
import com.blamejared.teamy.reference.Reference;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GUITeamy extends GuiScreen {
    
    private ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/gui_teamy.png");
    public int guiLeft;
    public int guiTop;
    public int xSize;
    public int ySize;
    
    
    public int scrollBarX;
    public int scrollBarY;
    
    
    public GUITeamy() {
    }
    
    @Override
    public void initGui() {
        super.initGui();
        this.xSize = 248;
        this.ySize = 166;
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        this.guiLeft = scaledresolution.getScaledWidth() / 2 - xSize / 2;
        this.guiTop = scaledresolution.getScaledHeight() / 2 - ySize / 2;
        System.out.println(guiLeft + ":" + guiTop);
        setGuiSize(xSize, ySize);
        this.scrollBarX = guiLeft + 82;
        this.scrollBarY = guiTop + 4;
    
    
    }
    
    @Override
    public void drawBackground(int tint) {
        super.drawBackground(tint);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        
        GlStateManager.pushAttrib();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawTexturedModalRect(scrollBarX, scrollBarY, 0, ySize, 4, 13);
//        drawTexturedModalRect(scrollBarX, scrollBarY, 0, ySize, 4, 2);
//        for(int i = 0;i<100-Teamy.teams.size()/10;i++){
//            drawTexturedModalRect(scrollBarX, 100-scrollBarY+2+i, 0, ySize+2, 4, 2);
//        }
//        drawTexturedModalRect(scrollBarX, scrollBarY+3+Teamy.teams.size()/10, 0, ySize+11, 4, 2);
        GlStateManager.popAttrib();
        final int[] y = {0};
        int teamOffset = Math.abs(guiTop+4-scrollBarY);
        for(int i = teamOffset; i < Teamy.teams.size(); i++) {
            if(y[0] < 10) {
                GlStateManager.pushAttrib();
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                drawTexturedModalRect(guiLeft + 7, guiTop + 7 + (y[0]++ * 15), 4, ySize, 70, 13);
                GlStateManager.popAttrib();
            }
        }
        y[0] = 0;
        for(int i = teamOffset; i < Teamy.teams.size(); i++) {
            Team team = Teamy.teams.get(i);
            if(y[0] < 10) {
                fontRenderer.drawString(i+"", guiLeft + 10, guiTop + 10 + (y[0]++ * 15), 0);
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
    
    @Override
    public void handleInput() throws IOException {
        super.handleInput();
        int dWheel = Mouse.getDWheel();
        if(dWheel < 0) {
            if(scrollBarY < guiTop+ySize-16) {
                scrollBarY = Math.min(scrollBarY+Teamy.teams.size()/10, guiTop+ySize-17);
            }
        } else if(dWheel > 0) {
            if(scrollBarY > guiTop+4){
                scrollBarY = Math.max(scrollBarY-Teamy.teams.size()/10, guiTop+4);
                
            }
        }
        
    }
}
