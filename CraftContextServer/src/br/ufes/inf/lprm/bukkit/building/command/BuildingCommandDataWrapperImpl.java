package br.ufes.inf.lprm.bukkit.building.command;

import org.omg.CORBA.Object;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.ufes.inf.lprm.generated.BuildingAddCommandDataPOA;
import br.ufes.inf.lprm.generated.BuildingCommandDataWrapperPOA;
import br.ufes.inf.lprm.generated.BuildingCommandType;
import br.ufes.inf.lprm.generated.BuildingListCommandDataPOA;
import br.ufes.inf.lprm.generated.BuildingRemoveCommandDataPOA;
import br.ufes.inf.lprm.jacorb.Server;

public class BuildingCommandDataWrapperImpl extends BuildingCommandDataWrapperPOA{
	private BuildingCommandType command;
	private Object data;
	
	public BuildingCommandDataWrapperImpl(BuildingAddCommandDataPOA data) throws ServantNotActive, WrongPolicy {
		this.command = BuildingCommandType.CMD_ADD;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public BuildingCommandDataWrapperImpl(BuildingRemoveCommandDataPOA data) throws ServantNotActive, WrongPolicy {
		this.command = BuildingCommandType.CMD_REMOVE;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	public BuildingCommandDataWrapperImpl(BuildingListCommandDataPOA data) throws ServantNotActive, WrongPolicy {
		this.command = BuildingCommandType.CMD_LIST;
		this.data = Server.poa.servant_to_reference(data);
	}
	
	@Override
	public BuildingCommandType getCommandType() {
		return command;
	}

	@Override
	public Object getCommandData() {
		return data;
	}

}
