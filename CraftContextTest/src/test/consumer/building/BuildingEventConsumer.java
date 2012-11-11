package test.consumer.building;

import org.omg.CORBA.Any;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerOperations;

import br.ufes.inf.lprm.building.BuildingEventDataManager;
import br.ufes.inf.lprm.generated.BuildingEventType;
import br.ufes.inf.lprm.generated.BuildingPresenceEventData;

public class BuildingEventConsumer implements PushConsumerOperations{

	@Override
	public void disconnect_push_consumer() {
		System.out.println("BuildingEventConsumer: disconnected.");
	}

	@Override
	public void push(Any any) throws Disconnected {
		try{
			BuildingEventDataManager manager = new BuildingEventDataManager(any);
			if(manager.getEventType().equals(BuildingEventType.EVT_PRESENCE)){
				BuildingPresenceEventData data = manager.getBuildingPresenceEventData();
				System.out.println("[EVENT] Player " + data.getPlayerName() + " has entered the building " + data.getBuilding().getName());
				
			}else{
				System.err.println("Error: Building Event not found.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
