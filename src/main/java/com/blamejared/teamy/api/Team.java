package com.blamejared.teamy.api;

import java.util.*;

public class Team {
    
    private String name;
    private int colour;
    private Map<UUID, TeamRole> players;
    
    public Team(String name, int colour) {
        this.name = name;
        this.colour = colour;
        players = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getColour() {
        return colour;
    }
    
    public void setColour(int colour) {
        this.colour = colour;
    }
    
    public Map<UUID, TeamRole> getPlayers() {
        return players;
    }
    
    public void setPlayers(Map<UUID, TeamRole> players) {
        this.players = players;
    }
    
    public void addPlayer(UUID uuid, TeamRole role) {
        if(role == TeamRole.NONE){
            return;
        }
        this.players.put(uuid, role);
    }
    
    public void removeFromTeam(UUID uuid){
        players.remove(uuid);
    }
    public void changeRole(UUID uuid, TeamRole role){
        players.replace(uuid, role);
    }
    
    public boolean isPartOfTeam(UUID uuid) {
        return players.containsKey(uuid);
    }
    
    public TeamRole getRole(UUID uuid) {
        return players.getOrDefault(uuid, TeamRole.NONE);
    }
    
    
}
