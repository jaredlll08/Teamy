package com.blamejared.teamy.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {
    
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        doRender(new BlockPos(0, 80, 0));
        doRender(new BlockPos(100, 80, 0));
        doRender(new BlockPos(0, 80, 100));
        doRender(new BlockPos(100, 80, 100));
    }
    
    
    public static void doRender(BlockPos pos) {
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        Minecraft mc = Minecraft.getMinecraft();
        if(renderManager.renderViewEntity != null) {
            {
                StringBuilder label = new StringBuilder(Math.floor(renderManager.renderViewEntity.getDistance(pos.getX(), pos.getY(), pos.getZ())) + " M");
                double sq = renderManager.renderViewEntity.getLookVec().distanceTo(new Vec3d(pos));
                if(sq > mc.gameSettings.renderDistanceChunks * 16 || Math.floor(renderManager.renderViewEntity.getDistance(pos.getX(), pos.getY(), pos.getZ())) > 150 || Math.floor(renderManager.renderViewEntity.getDistance(pos.getX(), pos.getY(), pos.getZ())) < 20) {
                    return;
                }
                GlStateManager.pushMatrix();
                GlStateManager.translate(pos.getX() - renderManager.renderViewEntity.getPosition().getX(), pos.getY() - renderManager.renderViewEntity.getPosition().getY(), pos.getZ() - renderManager.renderViewEntity.getPosition().getZ());
                GlStateManager.glNormal3f(pos.getX() - renderManager.renderViewEntity.getPosition().getX(), pos.getY() - renderManager.renderViewEntity.getPosition().getY(), pos.getZ() - renderManager.renderViewEntity.getPosition().getZ());
                GlStateManager.rotate(-renderManager.renderViewEntity.rotationYaw, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(1 * renderManager.renderViewEntity.rotationPitch, 1.0F, 0.0F, 0.0F);
                //                GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
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
                GlStateManager.translate(entity.getPosition().getX() - renderManager.renderViewEntity.getPosition().getX(), entity.getPosition().getY() - renderManager.renderViewEntity.getPosition().getY(), entity.getPosition().getZ() - renderManager.renderViewEntity.getPosition().getZ());
                GlStateManager.glNormal3f(entity.getPosition().getX() - renderManager.renderViewEntity.getPosition().getX(), entity.getPosition().getY() - renderManager.renderViewEntity.getPosition().getY(), entity.getPosition().getZ() - renderManager.renderViewEntity.getPosition().getZ());
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
