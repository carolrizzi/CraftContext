package br.ufes.inf.lprm.bukkit.building.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import br.ufes.inf.lprm.generated.Building;

public class BuildingPresenceEvent extends Event{

	private static final long serialVersionUID = 1L;

	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private Building building;
	
	public BuildingPresenceEvent(Player player, Building building) {
		this.player = player;
		this.building = building;
	}
	
	public HandlerList getHandlers() {
        return handlers;
    }
	
	public static HandlerList getHandlerList() {
        return handlers;
    }

	public Player getPlayer() {
		return player;
	}

	public Building getBuilding() {
		return building;
	}
	
}
