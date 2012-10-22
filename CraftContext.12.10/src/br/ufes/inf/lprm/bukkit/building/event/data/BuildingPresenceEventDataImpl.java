package br.ufes.inf.lprm.bukkit.building.event.data;

import br.ufes.inf.lprm.generated.Building;
import br.ufes.inf.lprm.generated.BuildingPresenceEventDataPOA;

public class BuildingPresenceEventDataImpl extends BuildingPresenceEventDataPOA{

	private String playerName;
	private Building building;
	
	public BuildingPresenceEventDataImpl(String playerName, Building building) {
		this.playerName = playerName;
		this.building = building;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public Building getBuilding() {
		return building;
	}

}
