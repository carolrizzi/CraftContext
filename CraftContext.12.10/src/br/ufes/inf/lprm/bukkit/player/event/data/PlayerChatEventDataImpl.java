package br.ufes.inf.lprm.bukkit.player.event.data;

import br.ufes.inf.lprm.generated.PlayerChatEventDataPOA;

public class PlayerChatEventDataImpl extends PlayerChatEventDataPOA{

	private String name;
	private String message;

	public PlayerChatEventDataImpl(String name, String message) {
		this.name = name;
		this.message = message;
	}

	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
