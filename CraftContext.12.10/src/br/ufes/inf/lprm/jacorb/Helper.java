package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.EventChannelHelper;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Helper {
	public static void initService (String serviceName, Servant service) throws NotFound, CannotProceed, InvalidName, AlreadyBound, ServantNotActive, WrongPolicy {
		try {
			Server.nc.bind(Server.nc.to_name(serviceName), Server.poa.servant_to_reference(service));
			System.out.println("Service " + serviceName + " started.");
		} catch (Exception e) {
			System.err.println("ERROR: Could not start the service " + serviceName + ". (br.ufes.inf.lprm.jacorb.Helper.initService)");
			e.printStackTrace();
		}
	}
	
	public static ProxyPushConsumer getProxyPushConsumer (String channelName) throws NotFound, CannotProceed, InvalidName{
		EventChannel channel = EventChannelHelper.narrow(Server.nc.resolve(Server.nc.to_name(channelName)));
		return channel.for_suppliers().obtain_push_consumer();
	}
	
	public static Any getAny(String str) {
		try{
			Any any = Server.orb.create_any();
			any.insert_string(str);
			return any;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Any getAny(Servant servant) {
		try{
			Any any = Server.orb.create_any();
			Object obj = Server.poa.servant_to_reference(servant);
			any.insert_Object(obj);
			return any;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
