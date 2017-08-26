package com.blamejared.teamy.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {
    
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        doRender(0, 80, 0);
        doRender(100, 80, 0);
        doRender(0, 80, 100);
        doRender(100, 80, 100);
    }
    
    
    public static void doRender(double x, double y, double z) {
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        Minecraft mc = Minecraft.getMinecraft();
        if(renderManager.renderViewEntity != null) {
            {
                StringBuilder label = new StringBuilder(Math.floor(renderManager.renderViewEntity.getDistance(x, y, z) * 100) / 100 + " M");
                double sq = renderManager.renderViewEntity.getDistance(x, y, z);
                if(sq > mc.gameSettings.renderDistanceChunks * 16 || renderManager.renderViewEntity.getDistance(x, y, z) < 5) {
                    return;
                }
                GlStateManager.pushAttrib();
                GlStateManager.pushMatrix();
                GlStateManager.translate(x - renderManager.renderViewEntity.posX, y - renderManager.renderViewEntity.posY, z - renderManager.renderViewEntity.posZ);
                GlStateManager.glNormal3f(0, 0, 0);
                GlStateManager.rotate(-renderManager.renderViewEntity.rotationYaw, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(renderManager.renderViewEntity.rotationPitch, 1.0F, 0.0F, 0.0F);
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.depthMask(false);
                
                int i = mc.fontRenderer.getStringWidth(label.toString()) / 2;
                GlStateManager.disableTexture2D();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                double scale = 0.00625 * ((sq + 4) / 2.0D);
                GlStateManager.scale(-scale, -scale, -scale);
                bufferbuilder.pos((double) (-i - 1), (double) (-1), 0.0D).color(.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (-i - 1), (double) (8), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (i + 1), (double) (8), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (i + 1), (double) (-1), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
                
                mc.fontRenderer.drawString(label.toString(), -mc.fontRenderer.getStringWidth(label.toString()) / 2, 0, 0x55ff55);
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
                
                GlStateManager.enableLighting();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
                GlStateManager.popAttrib();
            }
        }
    }
    
    
    public static void doRender(EntityLivingBase entity) {
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        Minecraft mc = Minecraft.getMinecraft();
        if(renderManager.renderViewEntity != null) {
            {
                StringBuilder label = new StringBuilder(Math.floor(renderManager.renderViewEntity.getDistance(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ())) + " M");
                double sq = renderManager.renderViewEntity.getLookVec().distanceTo(new Vec3d(entity.getPosition()));
                if(sq > mc.gameSettings.renderDistanceChunks * 16 || Math.floor(renderManager.renderViewEntity.getDistance(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ())) > 150 || Math.floor(renderManager.renderViewEntity.getDistance(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ())) < 20) {
                    return;
                }
                GlStateManager.pushMatrix();
                GlStateManager.translate(entity.getPosition().getX() - renderManager.renderViewEntity.posX, entity.getPosition().getY() - renderManager.renderViewEntity.posY, entity.getPosition().getZ() - renderManager.renderViewEntity.posZ);
                GlStateManager.glNormal3f((float) (entity.getPosition().getX() - renderManager.renderViewEntity.posX), (float) (entity.getPosition().getY() - renderManager.renderViewEntity.posY), (float) (entity.getPosition().getZ() - renderManager.renderViewEntity.posZ));
                GlStateManager.rotate(-renderManager.renderViewEntity.rotationYaw, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(1 * renderManager.renderViewEntity.rotationPitch, 1.0F, 0.0F, 0.0F);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                int i = mc.fontRenderer.getStringWidth(label.toString()) / 2;
                GlStateManager.disableTexture2D();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                double scale = 0.00390625D * ((sq + 4.0D) / 2.0D);
                GlStateManager.scale(-scale, -scale, scale);
                bufferbuilder.pos((double) (-i - 1), (double) (-1), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (-i - 1), (double) (8), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (i + 1), (double) (8), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                bufferbuilder.pos((double) (i + 1), (double) (-1), 0.0D).color(0.0F, 0.0F, 0.0F, 1).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
                
                
                GlStateManager.depthMask(false);
                GlStateManager.disableDepth();
                mc.fontRenderer.drawString(label.toString(), -mc.fontRenderer.getStringWidth(label.toString()) / 2, 0, 0x55ff55);
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
            }
        }
    }
}
