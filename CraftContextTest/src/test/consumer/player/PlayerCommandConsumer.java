package test.consumer.player;

import org.omg.CORBA.Any;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerOperations;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.generated.PlayerCommandType;
import br.ufes.inf.lprm.generated.PlayerDamageCommandData;
import br.ufes.inf.lprm.generated.PlayerException;
import br.ufes.inf.lprm.generated.PlayerFoodLevelCommandData;
import br.ufes.inf.lprm.generated.PlayerNotFound;
import br.ufes.inf.lprm.generated.PlayerService;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.player.PlayerCommandDataManager;
import br.ufes.inf.lprm.player.PlayerProperties;
import br.ufes.inf.lprm.player.PlayerServiceManager;
import br.ufes.inf.lprm.player.exception.PlayerCommandTypeError;

public class PlayerCommandConsumer implements PushConsumerOperations {

	@Override
	public void disconnect_push_consumer() {
		System.out.println("PlayerCommandConsumer: disconnected.");
	}

	@Override
	public void push(Any any) throws Disconnected {
		try{
			PlayerService service;
				service = PlayerServiceManager.getService();
			
			
			PlayerCommandDataManager manager = new PlayerCommandDataManager(any);
			
			if(manager.getCommandType().equals(PlayerCommandType.CMD_FOOD_LEVEL)){
				PlayerFoodLevelCommandData data = manager.getPlayerFoodLevelCommandData();
				service.setPlayerFoodLevel(data.getPlayerName(), data.getFoodLevel());
				service.sendMessageToPlayer(data.getPlayerName(), "Your food level was changed to " + data.getFoodLevel());
			
			}else if(manager.getCommandType().equals(PlayerCommandType.CMD_HEALTH)){
				PlayerDamageCommandData data = manager.getPlayerDamageCommandData();
				service.setPlayerHealth(data.getPlayerName(), data.getDamage());
				service.sendMessageToPlayer(data.getPlayerName(), "Your health level was changed to " + data.getDamage());
			
			}else{
				System.err.println("Error: Player Command not found.");
			}
		} catch (NotFound e) {
			System.err.println("Service " + Properties.getService(PlayerProperties.SUBJECT) + " not found.\nExiting the system.");
		} catch (CannotProceed e) {
			System.err.println("Cannot Proceed. Service name: " + Properties.getService(PlayerProperties.SUBJECT) + ".\nExiting the system.");
		} catch (InvalidName e) {
			System.err.println("Invalid service name: " + Properties.getService(PlayerProperties.SUBJECT) + ".\nExiting the system.");
		} catch (PlayerCommandTypeError e) {
			System.err.println("Wrong PlayerCommandData Exception: incorrect received data or incorrect data type conversion during data extraction.");
		} catch (PlayerNotFound e) {
			System.err.println("Player not found: " + e.name);
		} catch (PlayerException e) {
			System.err.println("Unkown Player Exception: " + e.msg);
		}
	}

}
