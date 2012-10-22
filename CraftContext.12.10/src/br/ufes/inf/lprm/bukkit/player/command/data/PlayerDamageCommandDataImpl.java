package br.ufes.inf.lprm.bukkit.player.command.data;

import br.ufes.inf.lprm.generated.PlayerDamageCommandDataPOA;

public class PlayerDamageCommandDataImpl extends PlayerDamageCommandDataPOA{

	private String name;
	private int damage;

	public PlayerDamageCommandDataImpl(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public int getDamage() {
		return damage;
	}

}
