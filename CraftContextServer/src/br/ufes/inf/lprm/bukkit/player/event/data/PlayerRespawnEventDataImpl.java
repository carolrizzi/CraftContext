package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerRespawnEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerRespawnEventDataImpl extends PlayerRespawnEventDataPOA{

	private String name;
	private Position position;

	public PlayerRespawnEventDataImpl(String name, Position position) {
		this.name = name;
		this.position = position;
	}
	
	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return position;
	}

}
