package com.blamejared.teamy.toasts;

import net.minecraft.client.gui.toasts.*;
import net.minecraft.client.renderer.GlStateManager;

public class TeamyToast implements IToast {
    
    private long firstDrawTime;
    private boolean newDisplay;
    
    private String team;
    
    public TeamyToast(String team) {
        this.team = team;
    }
    
    public IToast.Visibility draw(GuiToast toastGui, long delta) {
        if(this.newDisplay) {
            this.firstDrawTime = delta;
            this.newDisplay = false;
        }
        
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 64, 160, 32);
        
        toastGui.getMinecraft().fontRenderer.drawString("You can join the team:", 18, 7, -1);
        toastGui.getMinecraft().fontRenderer.drawString(team, 18, 18, -1);
        
        return delta - this.firstDrawTime < 5000L ? IToast.Visibility.SHOW : IToast.Visibility.HIDE;
    }
    
}
