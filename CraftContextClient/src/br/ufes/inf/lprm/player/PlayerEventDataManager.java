package br.ufes.inf.lprm.player;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;

import br.ufes.inf.lprm.generated.PlayerChatEventData;
import br.ufes.inf.lprm.generated.PlayerChatEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerDamageEventData;
import br.ufes.inf.lprm.generated.PlayerDamageEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerDeathEventData;
import br.ufes.inf.lprm.generated.PlayerDeathEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerEventDataWrapper;
import br.ufes.inf.lprm.generated.PlayerEventDataWrapperHelper;
import br.ufes.inf.lprm.generated.PlayerEventType;
import br.ufes.inf.lprm.generated.PlayerFoodLevelEventData;
import br.ufes.inf.lprm.generated.PlayerFoodLevelEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerJoinEventData;
import br.ufes.inf.lprm.generated.PlayerJoinEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerKickEventData;
import br.ufes.inf.lprm.generated.PlayerKickEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerLogoutEventData;
import br.ufes.inf.lprm.generated.PlayerLogoutEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerMoveEventData;
import br.ufes.inf.lprm.generated.PlayerMoveEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerRegainHeathEventData;
import br.ufes.inf.lprm.generated.PlayerRegainHeathEventDataHelper;
import br.ufes.inf.lprm.generated.PlayerRespawnEventData;
import br.ufes.inf.lprm.generated.PlayerRespawnEventDataHelper;
import br.ufes.inf.lprm.player.exception.PlayerEventTypeError;

public class PlayerEventDataManager {

	private PlayerEventType event;
	private Object data;
	
	/**
	 * Given an Any object, creates a manager for manipulating event data of Player type.
	 * @param any The Any object from where the event data will be extracted.
	 * @throws PlayerEventTypeError The object extracted from the given Any object is not an event data of Player type.
	 */
	public PlayerEventDataManager(Any any) throws PlayerEventTypeError {
		try{
			PlayerEventDataWrapper dataWrapper = PlayerEventDataWrapperHelper.narrow(any.extract_Object());
			this.event = dataWrapper.getEventType();
			this.data = dataWrapper.getEventData();
		}catch (Exception e) {
			throw new PlayerEventTypeError();
		}
	}
	
	/**
	 * @return The event data.
	 */
	public PlayerEventType getEventType() {
		return event;
	}

	/**
	 * @return Data containing information about a player's move event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_MOVE.
	 */
	public PlayerMoveEventData getPlayerMoveEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_MOVE)) throw new PlayerEventTypeError();
		return PlayerMoveEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's login event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_JOIN.
	 */
	public PlayerJoinEventData getPlayerJoinEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_JOIN)) throw new PlayerEventTypeError();
		return PlayerJoinEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's logout event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_LOGOUT.
	 */
	public PlayerLogoutEventData getPlayerLogoutEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_LOGOUT)) throw new PlayerEventTypeError();
		return PlayerLogoutEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's forced logout event, which means that the player was kicked.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_KICK.
	 */
	public PlayerKickEventData getPlayerKickEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_KICK)) throw new PlayerEventTypeError();
		return PlayerKickEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's respawn event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_RESPAWN.
	 */
	public PlayerRespawnEventData getPlayerRespawnEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_RESPAWN)) throw new PlayerEventTypeError();
		return PlayerRespawnEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's chat event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's chat message
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_CHAT.
	 */
	public PlayerChatEventData getPlayerChatEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_CHAT)) throw new PlayerEventTypeError();
		return PlayerChatEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's starvation level change event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * - Player's starvation level
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_STARVATION_LEVEL.
	 */
	public PlayerFoodLevelEventData getPlayerStarvationLevelChangeEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_STARVATION_LEVEL)) throw new PlayerEventTypeError();
		return PlayerFoodLevelEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's death event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's position when he/she died
	 * - Death message, explaining the death cause
	 * - Experience amount dropped by the player when he/she died
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_DEATH.
	 */
	public PlayerDeathEventData getPlayerDeathEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_DEATH)) throw new PlayerEventTypeError();
		return PlayerDeathEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's regain health event.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * - Amount of regained health
	 * - Reason of health recovery
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_REGAIN_HEALTH.
	 */
	public PlayerRegainHeathEventData getPlayerRegainHeathEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_REGAIN_HEALTH)) throw new PlayerEventTypeError();
		return PlayerRegainHeathEventDataHelper.narrow(data);
	}
	
	/**
	 * @return Data containing information about a player's damage event (health loss).
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current position
	 * - Amount of lost health
	 * - Reason of health loss
	 * </pre>
	 * @throws PlayerEventTypeError The event type is not PlayerEventType.EVT_DAMAGE.
	 */
	public PlayerDamageEventData getPlayerDamageEventData () throws PlayerEventTypeError {
		if(!event.equals(PlayerEventType.EVT_DAMAGE)) throw new PlayerEventTypeError();
		return PlayerDamageEventDataHelper.narrow(data);
	}
	
}
