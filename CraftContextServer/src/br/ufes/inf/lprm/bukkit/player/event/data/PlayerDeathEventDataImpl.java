package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerDeathEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerDeathEventDataImpl extends PlayerDeathEventDataPOA{

	private String name;
	private Position position;
	private String deathMessage;
	private int droppedExp;

	public PlayerDeathEventDataImpl(String name, Position position, String deathMessage, int droppedExp) {
		this.name = name;
		this.position = position;
		this.deathMessage = deathMessage;
		this.droppedExp = droppedExp;
	}
	
	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getDeathMessage() {
		return deathMessage;
	}

	@Override
	public int getDroppedExp() {
		return droppedExp;
	}

}
