package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

public class Server extends Thread{

	public static ORB orb;
	public static POA poa;
	public static NamingContextExt nc;
	
	/**
	 * Initiates necessary services in order to use Jacorb.
	 * @throws InvalidName
	 * @throws AdapterInactive
	 */
	public Server() throws InvalidName, AdapterInactive {
		orb = ORB.init(new String[0], null);
		poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		poa.the_POAManager().activate();
		nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
	}
	
	/**
	 * Stops the Jacorb server.
	 */
	public void stopServer() {
		System.out.println("Stopping the Jacorb Server...");
		orb.shutdown(false);
	}
	
	/**
	 * Starts the Jacorb server.
	 */
	public void run(){
		System.out.println("Jacorb Server started.");
		orb.run();
	}
	
}
