package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerLogoutEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerLogoutEventDataImpl extends PlayerLogoutEventDataPOA {
	
	private String name;
	private Position position;

	public PlayerLogoutEventDataImpl(String name, Position position) {
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
