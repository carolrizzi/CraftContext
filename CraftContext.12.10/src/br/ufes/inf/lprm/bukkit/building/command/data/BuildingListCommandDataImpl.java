package br.ufes.inf.lprm.bukkit.building.command.data;

import br.ufes.inf.lprm.generated.BuildingListCommandDataPOA;

public class BuildingListCommandDataImpl extends BuildingListCommandDataPOA{

	private String playerName;
	
	public BuildingListCommandDataImpl(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public String getPlayerName() {
		return playerName;
	}

}
