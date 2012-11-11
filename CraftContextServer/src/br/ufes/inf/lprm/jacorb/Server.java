package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server extends Thread{

	public static ORB orb;
	public static POA poa;
	public static NamingContextExt nc;
	
	public Server() {
		try{
			orb = ORB.init(new String[0], null);
			poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
		System.out.println("Stopping the Jacorb Server...");
		orb.shutdown(false);
	}
	
	public void run(){
		System.out.println("Jacorb Server started.");
		orb.run();
	}
	
}
