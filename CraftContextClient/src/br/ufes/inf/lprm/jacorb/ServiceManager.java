package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public abstract class ServiceManager {

	/**
	 * Returns a CORBA Object given a service subject.
	 * @param subject Service subject.
	 * @return A CORBA object.
	 * @throws NotFound
	 * @throws CannotProceed
	 * @throws InvalidName
	 */
	protected static Object getObject (String subject) throws NotFound, CannotProceed, InvalidName{
		return Server.nc.resolve(Server.nc.to_name(Properties.getService(subject)));
	}
	
}
