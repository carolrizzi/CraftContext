package br.ufes.inf.lprm.jacorb;

import org.omg.CORBA.Any;
import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushSupplierPOA;

public class PushSupplier extends PushSupplierPOA{

	private ProxyPushConsumer ppc;
	private String event;
	
	public PushSupplier(ProxyPushConsumer ppc, String eventName) {
		this.ppc = ppc;
		this.event = eventName;
		
		try {
			this.ppc.connect_push_supplier(_this(Server.orb));
		} catch (AlreadyConnected e) {
			System.err.println("ERROR: Push Supplier is already connected.");
		}
	}
	
	public void notifyEvent (Any any){
		new Push(ppc, any, event);
	}
	
	public void notifyEvent (){
		new Push(ppc, Server.orb.create_any(), event);
	}
	
	@Override
	public void disconnect_push_supplier() {
		System.out.println("Event Push Supplier " + event + " is disconnected.");
	}

}

class Push extends Thread {
	private ProxyPushConsumer ppc;
	private Any any;
	private String event;
	
	public Push(ProxyPushConsumer ppc, Any any, String eventName) {
		this.ppc = ppc;
		this.any = any;
		this.event = eventName;
		start();
	}
	
	public void run(){
		try {
			ppc.push(any);
		} catch (Disconnected e) {
			ppc.disconnect_push_consumer();
			e.printStackTrace();
			System.err.println("ERROR: Could not nofitfy " + event + " event.");
		}
	}
}
