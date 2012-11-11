package br.ufes.inf.lprm.building;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;

import br.ufes.inf.lprm.building.exception.BuildingEventTypeError;
import br.ufes.inf.lprm.generated.BuildingEventDataWrapper;
import br.ufes.inf.lprm.generated.BuildingEventDataWrapperHelper;
import br.ufes.inf.lprm.generated.BuildingEventType;
import br.ufes.inf.lprm.generated.BuildingPresenceEventData;
import br.ufes.inf.lprm.generated.BuildingPresenceEventDataHelper;

public class BuildingEventDataManager {

	private BuildingEventType event;
	private Object data;
	
	/**
	 * Given an Any object, creates a manager for manipulating event data of Building type.
	 * @param any The Any object from where the event data will be extracted.
	 * @throws BuildingEventTypeError The object extracted from the given Any object is not an event data of Building type.
	 */
	public BuildingEventDataManager(Any any) throws BuildingEventTypeError {
		try{
			BuildingEventDataWrapper dataWrapper = BuildingEventDataWrapperHelper.narrow(any.extract_Object());
			this.event = dataWrapper.getEventType();
			this.data = dataWrapper.getEventData();
		}catch (Exception e) {
			throw new BuildingEventTypeError();
		}
	}
	
	/**
	 * @return The event type.
	 */
	public BuildingEventType getEventType() {
		return event;
	}

	/**
	 * @return Data containing information about a player's presence inside a building.
	 * <pre>
	 * Data Content:
	 * - The building's name
	 * - Name of the player who is inside the building
	 * </pre>
	 * @throws BuildingEventTypeError The command type is not BuildingEventType.EVT_PRESENCE.
	 */
	public BuildingPresenceEventData getBuildingPresenceEventData() throws BuildingEventTypeError{
		if(!event.equals(BuildingEventType.EVT_PRESENCE)) throw new BuildingEventTypeError();
		return BuildingPresenceEventDataHelper.narrow(data);
	}
	
}
