package br.ufes.inf.lprm.bukkit.player.event;

import org.omg.CORBA.Object;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.ufes.inf.lprm.bukkit.player.event.data.PlayerChatEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerDamageEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerDeathEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerFoodLevelEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerJoinEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerKickEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerLogoutEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerMoveEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerRegainHeathEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerRespawnEventDataImpl;
import br.ufes.inf.lprm.generated.PlayerEventDataWrapperPOA;
import br.ufes.inf.lprm.generated.PlayerEventType;
import br.ufes.inf.lprm.jacorb.Server;

public class PlayerEventDataWrapperImpl extends PlayerEventDataWrapperPOA{
	private PlayerEventType event;
	private Object data;
	
	public PlayerEventDataWrapperImpl(PlayerJoinEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_JOIN;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public PlayerEventDataWrapperImpl(PlayerLogoutEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_LOGOUT;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public PlayerEventDataWrapperImpl(PlayerChatEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_CHAT;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public PlayerEventDataWrapperImpl(PlayerRespawnEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_RESPAWN;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerKickEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_KICK;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerDeathEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_DEATH;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerMoveEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_MOVE;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerRegainHeathEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_REGAIN_HEALTH;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerDamageEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_DAMAGE;
		this.data = Server.poa.servant_to_reference(data);
	}

	public PlayerEventDataWrapperImpl(PlayerFoodLevelEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = PlayerEventType.EVT_STARVATION_LEVEL;
		this.data = Server.poa.servant_to_reference(data);
	}

	@Override
	public PlayerEventType getEventType() {
		return event;
	}

	@Override
	public Object getEventData() {
		return data;
	}
}
