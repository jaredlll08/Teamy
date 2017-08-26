package com.blamejared.teamy.network;

import com.blamejared.teamy.client.GUITeamy;
import com.blamejared.teamy.toasts.TeamyToast;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.*;

public class MessageTeamy implements IMessage, IMessageHandler<MessageTeamy, IMessage> {
    
    
    public MessageTeamy() {
    }
    
    
    @Override
    public void fromBytes(ByteBuf buf) {
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MessageTeamy message, MessageContext ctx) {
//        Minecraft.getMinecraft().getToastGui().add(new TeamyToast(message.teamName));
        
        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().displayGuiScreen(new GUITeamy()));
        return null;
    }
}
