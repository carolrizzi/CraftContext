package br.ufes.inf.lprm.bukkit.player.command.data;

import br.ufes.inf.lprm.generated.PlayerFoodLevelCommandDataPOA;

public class PlayerFoodLevelCommandDataImpl extends PlayerFoodLevelCommandDataPOA{

	private String name;
	private int foodLevel;

	public PlayerFoodLevelCommandDataImpl(String name, int foodLevel) {
		this.name = name;
		this.foodLevel = foodLevel;
	}
	
	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public int getFoodLevel() {
		return foodLevel;
	}

}
