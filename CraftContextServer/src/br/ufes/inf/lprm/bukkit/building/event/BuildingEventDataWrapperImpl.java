package br.ufes.inf.lprm.bukkit.building.event;

import org.omg.CORBA.Object;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.ufes.inf.lprm.bukkit.building.event.data.BuildingPresenceEventDataImpl;
import br.ufes.inf.lprm.generated.BuildingEventDataWrapperPOA;
import br.ufes.inf.lprm.generated.BuildingEventType;
import br.ufes.inf.lprm.jacorb.Server;

public class BuildingEventDataWrapperImpl extends BuildingEventDataWrapperPOA{

	private BuildingEventType event;
	private Object data;
	
	public BuildingEventDataWrapperImpl(BuildingPresenceEventDataImpl data) throws ServantNotActive, WrongPolicy {
		this.event = BuildingEventType.EVT_PRESENCE;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	@Override
	public BuildingEventType getEventType() {
		return event;
	}

	@Override
	public Object getEventData() {
		return data;
	}

}
