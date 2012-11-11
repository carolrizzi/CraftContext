package test.consumer.player;

import org.omg.CORBA.Any;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerOperations;

import br.ufes.inf.lprm.generated.DamageCause;
import br.ufes.inf.lprm.generated.PlayerChatEventData;
import br.ufes.inf.lprm.generated.PlayerDamageEventData;
import br.ufes.inf.lprm.generated.PlayerDeathEventData;
import br.ufes.inf.lprm.generated.PlayerEventType;
import br.ufes.inf.lprm.generated.PlayerFoodLevelEventData;
import br.ufes.inf.lprm.generated.PlayerJoinEventData;
import br.ufes.inf.lprm.generated.PlayerKickEventData;
import br.ufes.inf.lprm.generated.PlayerLogoutEventData;
import br.ufes.inf.lprm.generated.PlayerRegainHeathEventData;
import br.ufes.inf.lprm.generated.PlayerRespawnEventData;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.generated.RegainHealthReason;
import br.ufes.inf.lprm.player.PlayerEventDataManager;

public class PlayerEventConsumer implements PushConsumerOperations {

	@Override
	public void disconnect_push_consumer() {
		System.out.println("PlayerEventConsumer: disconnected.");
	}
	
	@Override
	public void push(Any any) throws Disconnected {
		try{
			PlayerEventDataManager manager = new PlayerEventDataManager(any);
			
			if(manager.getEventType().equals(PlayerEventType.EVT_JOIN)){
				PlayerJoinEventData data = manager.getPlayerJoinEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " joined the game. Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
			
			}else if(manager.getEventType().equals(PlayerEventType.EVT_CHAT)){
				PlayerChatEventData data = manager.getPlayerChatEventData();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " sent a message. Message: " + data.getMessage());				
			
			}else if(manager.getEventType().equals(PlayerEventType.EVT_DAMAGE)){
				PlayerDamageEventData data = manager.getPlayerDamageEventData();
				Position position = data.getPosition();
				
				String cause;
				switch (data.getCause().value()) {
				case DamageCause._BLOCK_EXPLOSION:
					cause = "Block Explosion";
					break;

				case DamageCause._CONTACT:
					cause = "Contact";
					break;

				case DamageCause._CUSTOM_CAUSE:
					cause = "Custom Cause";
					break;

				case DamageCause._DROWNING:
					cause = "Drowning";
					break;

				case DamageCause._ENTITY_ATTACK:
					cause = "Entity Attack";
					break;

				case DamageCause._ENTITY_EXPLOSION:
					cause = "Entity Explosion";
					break;

				case DamageCause._FALL:
					cause = "Fall";
					break;

				case DamageCause._FIRE_CAUSE:
					cause = "Fire Cause";
					break;

				case DamageCause._FIRE_TICK:
					cause = "Fire Tick";
					break;

				case DamageCause._LAVA_CAUSE:
					cause = "Lava Cause";
					break;

				case DamageCause._LIGHTNING:
					cause = "Lightning";
					break;

				case DamageCause._MAGIC_CAUSE:
					cause = "Magic Cause";
					break;

				case DamageCause._MELTING:
					cause = "Melting";
					break;

				case DamageCause._POISON:
					cause = "Poison";
					break;

				case DamageCause._PROJECTILE:
					cause = "Projectile";
					break;

				case DamageCause._STARVATION:
					cause = "Starvation";
					break;

				case DamageCause._SUFFOCATION:
					cause = "Suffocation";
					break;

				case DamageCause._SUICIDE:
					cause = "Suicide";
					break;

				case DamageCause._VOID_CAUSE:
					cause = "Void Cause";
					break;

				default:
					cause = "Not Identified";
					break;
				}
				
				System.out.println("[EVENT] Player " + data.getPlayerName() + " suffered a damage. Cause: " + cause + ". Damage: " + data.getDamage() +". Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());				
			
			}else if(manager.getEventType().equals(PlayerEventType.EVT_DEATH)){
				PlayerDeathEventData data = manager.getPlayerDeathEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " died. Message: " + data.getDeathMessage() + ". Dropped Experience: " + data.getDroppedExp() + ". Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
			
			}else if(manager.getEventType().equals(PlayerEventType.EVT_KICK)){
				PlayerKickEventData data = manager.getPlayerKickEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " was kicked. Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());	
			
			}else if(manager.getEventType().equals(PlayerEventType.EVT_LOGOUT)){
				PlayerLogoutEventData data = manager.getPlayerLogoutEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " left the game. Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
				
//			}else if(manager.getEventType().equals(PlayerEventType.EVT_MOVE)){
//				PlayerMoveEventData data = manager.getPlayerMoveEventData();
//				Position position = data.getPosition();
//				System.out.println("[EVENT] Player " + data.getPlayerName() + " moved. Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
//						
			}else if(manager.getEventType().equals(PlayerEventType.EVT_REGAIN_HEALTH)){
				PlayerRegainHeathEventData data = manager.getPlayerRegainHeathEventData();
				Position position = data.getPosition();
				
				String reason;
				switch (data.getRegainReason().value()) {
				case RegainHealthReason._CUSTOM_REASON:
					reason = "Custom reason";
					break;

				case RegainHealthReason._EATING:
					reason = "Eating";
					break;

				case RegainHealthReason._ENDER_CRYSTAL:
					reason = "Ender crystal";
					break;

				case RegainHealthReason._MAGIC_REASON:
					reason = "Magic reason";
					break;

				case RegainHealthReason._MAGIC_REGEN:
					reason = "Magic regen";
					break;

				case RegainHealthReason._REGEN:
					reason = "Regen";
					break;

				case RegainHealthReason._SATIATED:
					reason = "Satiated";
					break;

				default:
					reason = "Not Identified";
					break;
				}
				
				System.out.println("[EVENT] Player " + data.getPlayerName() + " regained health. Regain Reason: " + reason + ". Amount: " + data.getAmount() +". Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
				
			}else if(manager.getEventType().equals(PlayerEventType.EVT_RESPAWN)){
				PlayerRespawnEventData data = manager.getPlayerRespawnEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " reappeared. Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
				
			}else if(manager.getEventType().equals(PlayerEventType.EVT_STARVATION_LEVEL)){
				PlayerFoodLevelEventData data = manager.getPlayerStarvationLevelChangeEventData();
				Position position = data.getPosition();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " changed his starvation level. Food level: " + data.getFoodLevel() + ". Position: " + position.getX() + "/" + position.getY() + "/" + position.getZ());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
