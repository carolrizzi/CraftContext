package br.ufes.inf.lprm.player;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.generated.PlayerService;
import br.ufes.inf.lprm.generated.PlayerServiceHelper;
import br.ufes.inf.lprm.jacorb.ServiceManager;

public class PlayerServiceManager extends ServiceManager{

	private static PlayerService service = null;
	
	/**
	 * @return An object that provides services for handling Player matters.
	 * @throws NotFound
	 * @throws CannotProceed
	 * @throws InvalidName
	 */
	public static PlayerService getService () throws NotFound, CannotProceed, InvalidName {
		if(service == null){
			service = PlayerServiceHelper.narrow(getObject(PlayerProperties.SUBJECT));
		}
		return service;
	}
	
}
