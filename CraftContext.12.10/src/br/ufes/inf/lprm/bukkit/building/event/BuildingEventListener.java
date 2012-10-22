package br.ufes.inf.lprm.bukkit.building.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.bukkit.building.BuildingProperties;
import br.ufes.inf.lprm.bukkit.building.event.data.BuildingPresenceEventDataImpl;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.jacorb.PushSupplier;

public class BuildingEventListener implements Listener{

	private ProxyPushConsumer ppc;
	private PushSupplier ps;
	
	public BuildingEventListener() throws NotFound, CannotProceed, InvalidName {
		ppc = Helper.getProxyPushConsumer(Properties.getEventChannel(BuildingProperties.SUBJECT));
		ps = new PushSupplier(ppc, "Building");
	}
	
	public void disconnectConsumers() {
		ppc.disconnect_push_consumer();
	}
	
	@EventHandler
	public void onBuildingPresence (BuildingPresenceEvent event){
		try {
			Player player = event.getPlayer();
			BuildingPresenceEventDataImpl data = new BuildingPresenceEventDataImpl(player.getName(), event.getBuilding());
			BuildingEventDataWrapperImpl dataWarpper = new BuildingEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
