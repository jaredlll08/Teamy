package com.blamejared.teamy;

import com.blamejared.teamy.api.*;
import com.blamejared.teamy.commands.CommandTeamy;
import com.blamejared.teamy.network.PacketHandler;
import com.blamejared.teamy.proxies.*;
import com.blamejared.teamy.reference.Reference;
import com.google.gson.*;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import java.io.IOException;
import java.util.*;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Teamy {
    
    @Mod.Instance(Reference.MODID)
    public static Teamy INSTANCE;
    
    @SidedProxy(clientSide = "com.blamejared.teamy.proxies.ClientProxy", serverSide = "com.blamejared.teamy.proxies.CommonProxy")
    public static CommonProxy PROXY;
    
    public static List<Team> teams = new LinkedList<>();
    
    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) throws NoSuchFieldException, IllegalAccessException {
        PacketHandler.preInit();
        
        //TODO remove this, it is only a test to test json writing
        for(int i = 0; i < 200; i++) {
            Team team = new Team(UUID.randomUUID().toString().substring(0,10), UUID.randomUUID().hashCode());
            team.addPlayer(UUID.randomUUID(), TeamRole.ADMIN);
            team.addPlayer(UUID.randomUUID(), TeamRole.MEMBER);
            team.addPlayer(UUID.randomUUID(), TeamRole.OWNER);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(team));
            teams.add(team);
        }
    }
    
    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) throws NoSuchFieldException, IllegalAccessException, IOException {
        PROXY.registerEvents();
    }
    
    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandTeamy());
    }
}
