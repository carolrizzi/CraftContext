package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerRegainHeathEventDataPOA;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.generated.RegainHealthReason;

public class PlayerRegainHeathEventDataImpl extends PlayerRegainHeathEventDataPOA{

	private String name;
	private Position position;
	private RegainHealthReason regainReason;
	private int amount;

	public PlayerRegainHeathEventDataImpl(String name, Position position, RegainHealthReason regainReason, int amount) {
		this.name = name;
		this.position = position;
		this.regainReason = regainReason;
		this.amount = amount;
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
	public RegainHealthReason getRegainReason() {
		return regainReason;
	}

	@Override
	public int getAmount() {
		return amount;
	}

}
