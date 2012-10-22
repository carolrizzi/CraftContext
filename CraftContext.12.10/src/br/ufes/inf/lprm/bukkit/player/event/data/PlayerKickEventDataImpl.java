package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerKickEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerKickEventDataImpl extends PlayerKickEventDataPOA{

	private String name;
	private Position position;

	public PlayerKickEventDataImpl(String name, Position position) {
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
