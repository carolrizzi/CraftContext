package br.ufes.inf.lprm.bukkit.player.command;

import org.omg.CORBA.Object;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.ufes.inf.lprm.generated.PlayerCommandDataWrapperPOA;
import br.ufes.inf.lprm.generated.PlayerCommandType;
import br.ufes.inf.lprm.generated.PlayerDamageCommandDataPOA;
import br.ufes.inf.lprm.generated.PlayerFoodLevelCommandDataPOA;
import br.ufes.inf.lprm.jacorb.Server;

public class PlayerCommandDataWrapperImpl extends PlayerCommandDataWrapperPOA{

	private PlayerCommandType command;
	private Object data;

	public PlayerCommandDataWrapperImpl(PlayerDamageCommandDataPOA data) throws ServantNotActive, WrongPolicy {
		this.command = PlayerCommandType.CMD_HEALTH;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public PlayerCommandDataWrapperImpl(PlayerFoodLevelCommandDataPOA data) throws ServantNotActive, WrongPolicy {
		this.command = PlayerCommandType.CMD_FOOD_LEVEL;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	@Override
	public PlayerCommandType getCommandType() {
		return command;
	}

	@Override
	public Object getCommandData() {
		return data;
	}
}
