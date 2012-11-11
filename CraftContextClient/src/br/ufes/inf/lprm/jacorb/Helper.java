package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;
import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.EventChannelHelper;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventChannelAdmin.TypeError;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosEventComm.PushConsumerOperations;
import org.omg.CosEventComm.PushConsumerPOATie;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Helper {
	
	/**
	 * Registers a push consumer in the name server and retrieves a Push Supplier's reference.
	 * @param channelName Name of the event channel where the consumer will be registered.
	 * @param pco The consumer. 
	 * @return Reference to the Push Supplier.
	 * @throws NotFound There is no existing event/command channel with the provided channel name.
	 * @throws CannotProceed The implementation has given up for some reason.
	 * @throws InvalidName The provided channel name is invalid.
	 * @throws WrongPolicy The required policies for converting the servant into reference are not present.
	 * @throws ServantNotActive The above specified policies and rules are not met. 
	 * @throws AlreadyConnected This connection operation is called a second time. 
	 * @throws TypeError The consumer does not match the expected type.
	 */
	public static ProxyPushSupplier registerPushConsumer (String channelName, PushConsumerOperations pco) throws NotFound, CannotProceed, InvalidName, ServantNotActive, WrongPolicy, AlreadyConnected, TypeError{
		EventChannel channel = EventChannelHelper.narrow(Server.nc.resolve(Server.nc.to_name(channelName)));
		ProxyPushSupplier pps = channel.for_consumers().obtain_push_supplier();
		
		PushConsumerPOATie  pt = new PushConsumerPOATie(pco);
		pt._this_object(Server.orb);
		PushConsumer pushConsumer = PushConsumerHelper.narrow(Server.poa.servant_to_reference(pt));
		pps.connect_push_consumer(pushConsumer);
		return pps;
	}
	
	/**
	 * Retrieves an Any object given a String.
	 * @param str String to be inserted into a Any object.
	 * @return The any object containing the specified string.
	 */
	public static Any getAny(String str) {
		Any any = Server.orb.create_any();
		any.insert_string(str);
		return any;
	}
	
	/**
	 * Retrieves an Any object given a servant.
	 * @param servant Servant to be inserted into a Any object.
	 * @return The any object containing the specified servant.
	 * @throws WrongPolicy The required policies for converting the servant into reference are not present.
	 * @throws ServantNotActive The above specified policies and rules are not met. 
	 */
	public static Any getAny(Servant servant) throws ServantNotActive, WrongPolicy {
		Any any = Server.orb.create_any();
		Object obj = Server.poa.servant_to_reference(servant);
		any.insert_Object(obj);
		return any;
	}
}
