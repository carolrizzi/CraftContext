package br.ufes.inf.lprm.building;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.generated.BuildingService;
import br.ufes.inf.lprm.generated.BuildingServiceHelper;
import br.ufes.inf.lprm.jacorb.ServiceManager;

public class BuildingServiceManager extends ServiceManager{

	private static BuildingService service = null;
	
	/**
	 * @return An object that provides services for handling Building matters.
	 * @throws NotFound
	 * @throws CannotProceed
	 * @throws InvalidName
	 */
	public static BuildingService getService () throws NotFound, CannotProceed, InvalidName {
		if(service == null){
			service = BuildingServiceHelper.narrow(getObject(BuildingProperties.SUBJECT));
		}
		return service;
	}
	
}
