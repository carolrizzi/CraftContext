package br.ufes.inf.lprm.bukkit.building.command.data;

import br.ufes.inf.lprm.generated.BuildingRemoveCommandDataPOA;

public class BuildingRemoveCommandDataImpl extends BuildingRemoveCommandDataPOA{

	private String playerName;
	private String buildingName;
	private boolean physicalDestruction;
	
	public BuildingRemoveCommandDataImpl(String playerName, String buildingName, boolean physicalDestruction) {
		this.playerName = playerName;
		this.buildingName = buildingName;
		this.physicalDestruction = physicalDestruction;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String getBuildingName() {
		return buildingName;
	}

	@Override
	public boolean physicalDestruction() {
		return physicalDestruction;
	}

}
