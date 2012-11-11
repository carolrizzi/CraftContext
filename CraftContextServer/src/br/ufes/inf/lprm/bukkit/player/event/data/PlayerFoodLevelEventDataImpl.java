package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerFoodLevelEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerFoodLevelEventDataImpl extends PlayerFoodLevelEventDataPOA{

	private String player;
	private Position position;
	private int level;

	public PlayerFoodLevelEventDataImpl() {}
	
	public PlayerFoodLevelEventDataImpl(String player, Position position, int level) {
		this.player = player;
		this.position = position;
		this.level = level;
	}
	
	@Override
	public String getPlayerName() {
		return player;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public int getFoodLevel() {
		return level;
	}

}
