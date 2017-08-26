package com.blamejared.teamy.api;

public enum TeamRole {
    
    OWNER(1), ADMIN(2), MEMBER(3), NONE(Integer.MAX_VALUE);
    
    private final int priority;
    
    TeamRole(int priority) {
        this.priority = priority;
    }
    
    public boolean getInvitePlayers() {
        return priority < MEMBER.getPriority();
    }
    
    public int getPriority() {
        return priority;
    }
    
}
