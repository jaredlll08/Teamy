package com.blamejared.teamy;

import com.blamejared.teamy.proxies.CommonProxy;
import com.blamejared.teamy.reference.Reference;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Teamy {
    
    @Mod.Instance(Reference.MODID)
    public static Teamy INSTANCE;
    
    @SidedProxy(clientSide = "com.blamejared.teamy.proxies.ClientProxy", serverSide = "com.blamejared.teamy.proxies.CommonProxy")
    public static CommonProxy PROXY;
    
    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        PROXY.registerEvents();
    }
}
