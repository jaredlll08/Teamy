package com.blamejared.teamy.proxies;

import com.blamejared.teamy.events.ClientEvents;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    
    
    @Override
    public void registerEvents() {
        super.registerEvents();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }
}
