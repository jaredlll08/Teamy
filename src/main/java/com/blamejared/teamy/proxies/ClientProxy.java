package com.blamejared.teamy.proxies;

import com.blamejared.teamy.events.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.util.Map;

public class ClientProxy extends CommonProxy {
    
    
    @Override
    public void registerEvents() throws NoSuchFieldException, IllegalAccessException {
        super.registerEvents();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }
}
