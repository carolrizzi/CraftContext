package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.DamageCause;
import br.ufes.inf.lprm.generated.PlayerDamageEventDataPOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerDamageEventDataImpl extends PlayerDamageEventDataPOA{

	private String name;
	private Position position;
	private DamageCause cause;
	private int damage;

	public PlayerDamageEventDataImpl(String name, Position position, DamageCause cause, int damage) {
		this.name = name;
		this.position = position;
		this.cause = cause;
		this.damage = damage;
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
	public DamageCause getCause() {
		return cause;
	}

	@Override
	public int getDamage() {
		return damage;
	}

}
