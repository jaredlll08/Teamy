package com.blamejared.teamy.commands;

import com.blamejared.teamy.Teamy;
import com.blamejared.teamy.api.*;
import com.blamejared.teamy.network.*;
import com.google.gson.*;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class CommandTeamy extends CommandBase {
    
    @Override
    public String getName() {
        return "teamy";
    }
    
    @Override
    public String getUsage(ICommandSender sender) {
        return "teamy";
    }
    
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int size = Integer.parseInt(args[0]);
        Teamy.teams.clear();
        for(int i = 0; i < size; i++) {
            Team team = new Team(UUID.randomUUID().toString().substring(0,10), UUID.randomUUID().hashCode());
            team.addPlayer(UUID.randomUUID(), TeamRole.ADMIN);
            team.addPlayer(UUID.randomUUID(), TeamRole.MEMBER);
            team.addPlayer(UUID.randomUUID(), TeamRole.OWNER);
            Teamy.teams.add(team);
        }
        if(sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            PacketHandler.INSTANCE.sendToAll(new MessageTeamy());
//            player.openGui(Teamy.INSTANCE, 0, sender.getEntityWorld(), 0, 0, 0);
        }
    }
}
