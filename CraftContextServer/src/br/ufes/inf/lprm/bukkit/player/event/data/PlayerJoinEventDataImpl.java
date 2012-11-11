package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerJoinEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerJoinEventDataImpl extends PlayerJoinEventDataPOA{

	private String name;
	private Position position;

	public PlayerJoinEventDataImpl(String name, Position position) {
		this.name = name;
		this.position = position;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getPlayerName() {
		return name;
	}

}
