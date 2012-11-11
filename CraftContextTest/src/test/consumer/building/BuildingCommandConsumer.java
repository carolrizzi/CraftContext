package test.consumer.building;

import org.omg.CORBA.Any;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerOperations;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.building.BuildingCommandDataManager;
import br.ufes.inf.lprm.building.BuildingServiceManager;
import br.ufes.inf.lprm.building.exception.BuildingCommandTypeError;
import br.ufes.inf.lprm.generated.Area;
import br.ufes.inf.lprm.generated.BuildingAddCommandData;
import br.ufes.inf.lprm.generated.BuildingAlreadyExists;
import br.ufes.inf.lprm.generated.BuildingCommandType;
import br.ufes.inf.lprm.generated.BuildingException;
import br.ufes.inf.lprm.generated.BuildingListCommandData;
import br.ufes.inf.lprm.generated.BuildingNotFound;
import br.ufes.inf.lprm.generated.BuildingRemoveCommandData;
import br.ufes.inf.lprm.generated.BuildingService;
import br.ufes.inf.lprm.generated.PlayerException;
import br.ufes.inf.lprm.generated.PlayerNotFound;
import br.ufes.inf.lprm.generated.PlayerService;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.player.PlayerServiceManager;

public class BuildingCommandConsumer implements PushConsumerOperations{
	
	@Override
	public void disconnect_push_consumer() {
		System.out.println("BuildingCommandConsumer: disconnected.");
	}

	@Override
	public void push(Any any) throws Disconnected {
		try{
			BuildingService bService = BuildingServiceManager.getService();
			
			PlayerService pService = PlayerServiceManager.getService();
			BuildingCommandDataManager manager = new BuildingCommandDataManager(any);
			
			if(manager.getCommandType().equals(BuildingCommandType.CMD_ADD)){
				BuildingAddCommandData data = manager.getBuildingAddCommandData();
				Area area = data.getArea();
				Position center = area.getCenter();
				try {
					bService.addBuilding(data.getBuildingName(), 
									 data.getBuildingType(),
									 data.getBuildingAddress(),
									 data.getHeight(),
									 area.getXWidth(),
									 area.getZWidth(),
									 center.getX(),
									 center.getY(),
									 center.getZ());
					pService.sendMessageToPlayer(data.getPlayerName(), "The building " + data.getBuildingName() + " was added.");
				} catch (BuildingAlreadyExists e) {
					pService.sendMessageToPlayer(data.getPlayerName(), "The building " + data.getBuildingName() + " already exists.");
				}
				
			}else if(manager.getCommandType().equals(BuildingCommandType.CMD_REMOVE)){
				BuildingRemoveCommandData data = manager.getBuildingRemoveCommandData();
				try {
					bService.removeBuilding(data.getBuildingName(), data.physicalDestruction());
					pService.sendMessageToPlayer(data.getPlayerName(), "The building " + data.getBuildingName() + " was removed.");
				} catch (BuildingNotFound e) {
					pService.sendMessageToPlayer(data.getPlayerName(), "The building " + data.getBuildingName() + " does not exist.");
				}
				
				
			}else if(manager.getCommandType().equals(BuildingCommandType.CMD_LIST)){
				BuildingListCommandData data = manager.getBuildingListCommandData();
				bService.listBuildings(data.getPlayerName());
			
			}else{
				System.err.println("Error: Building Command not found.");
			}
		} catch (NotFound e) {
			System.err.println("Service not found.\nExiting the system.");
		} catch (CannotProceed e) {
			System.err.println("Cannot Proceed (Service).\nExiting the system.");
		} catch (InvalidName e) {
			System.err.println("Invalid service name.\nExiting the system.");
		} catch (BuildingCommandTypeError e) {
			System.err.println("Wrong BuildingCommandData Exception: incorrect received data or incorrect data type conversion during data extraction.");
		} catch (BuildingException e) {
			System.err.println("Unknown Building Exception: " + e.msg);
		} catch (PlayerNotFound e) {
			System.err.println("Player not found: " + e.name);
		} catch (PlayerException e) {
			System.err.println("Unknown Player Exception: " + e.msg);
		}
	}

}
