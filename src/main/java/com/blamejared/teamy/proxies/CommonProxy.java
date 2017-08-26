package com.blamejared.teamy.proxies;

import com.blamejared.teamy.events.*;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    
    public void registerEvents() throws NoSuchFieldException, IllegalAccessException {
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
    }
}
