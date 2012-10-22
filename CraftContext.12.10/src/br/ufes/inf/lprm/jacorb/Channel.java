package br.ufes.inf.lprm.jacorb;

import org.jacorb.events.EventChannelImpl;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Channel {

	private ORB orb;
	private String name;

	public Channel(String channelName) {
		this.name = channelName;

		try{
			orb = ORB.init(new String[0], null);
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			
			Object obj = poa.servant_to_reference(new EventChannelImpl(orb,poa));
			nc.bind(nc.to_name(channelName), obj);

			System.out.println("Channel " + name + " started");
		}catch (Exception e) {
			System.err.println("Could not start the channel " + name);
			e.printStackTrace();
		}
	}
	
	public void stopChannel() {
		if(orb == null){
			System.err.println("ERROR: The channel " + name + " cannot be stopped because it was not started.");
			return;
		}
		System.out.println("Stopping the " + name + " channel server...");
		orb.shutdown(false);
	}	
}
