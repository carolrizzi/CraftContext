package br.ufes.inf.lprm.bukkit.player.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.bukkit.Server;
import br.ufes.inf.lprm.bukkit.building.BuildingDataBase;
import br.ufes.inf.lprm.bukkit.building.event.BuildingPresenceEvent;
import br.ufes.inf.lprm.bukkit.common.PositionImpl;
import br.ufes.inf.lprm.bukkit.player.PlayerProperties;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerChatEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerDamageEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerDeathEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerFoodLevelEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerJoinEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerKickEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerLogoutEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerMoveEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerRegainHeathEventDataImpl;
import br.ufes.inf.lprm.bukkit.player.event.data.PlayerRespawnEventDataImpl;
import br.ufes.inf.lprm.generated.Building;
import br.ufes.inf.lprm.generated.DamageCause;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.generated.RegainHealthReason;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.jacorb.PushSupplier;

public class PlayerEventListener implements Listener{

	private ProxyPushConsumer ppc;
	private PushSupplier ps;
	
	public PlayerEventListener() throws NotFound, CannotProceed, InvalidName {
		ppc = Helper.getProxyPushConsumer(Properties.getEventChannel(PlayerProperties.SUBJECT));
		ps = new PushSupplier(ppc, "Player");
	}
	
	public void disconnectConsumers() {
		ppc.disconnect_push_consumer();
	}

	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent event){
		try {
			Player player = event.getPlayer();
			PlayerJoinEventDataImpl data = new PlayerJoinEventDataImpl(player.getName(), getPlayerPosition(player));
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerRespawn (PlayerRespawnEvent event){
		try {
			Player player = event.getPlayer();
			PlayerRespawnEventDataImpl data = new PlayerRespawnEventDataImpl(player.getName(), getPlayerPosition(player));
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent event){
		try {
			Player player = event.getPlayer();
			PlayerLogoutEventDataImpl data = new PlayerLogoutEventDataImpl(player.getName(), getPlayerPosition(player));
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event){
		try {
			Player player = event.getPlayer();
			PlayerKickEventDataImpl data = new PlayerKickEventDataImpl(player.getName(), getPlayerPosition(player));
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		try {
			if(event.getEntity() instanceof Player){
				Player player = (Player) event.getEntity();
				PlayerDeathEventDataImpl data = new PlayerDeathEventDataImpl(player.getName(), getPlayerPosition(player), event.getDeathMessage(), event.getDroppedExp());
				PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
				ps.notifyEvent(Helper.getAny(dataWarpper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerMove (PlayerMoveEvent event){
		try {
			Player player = event.getPlayer();
			PlayerMoveEventDataImpl data = new PlayerMoveEventDataImpl(player.getName(), getPlayerPosition(player));
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			verifyPlayerPresence(event.getPlayer());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerChat (PlayerChatEvent event){
		try {
			PlayerChatEventDataImpl data = new PlayerChatEventDataImpl(event.getPlayer().getName(), event.getMessage());
			PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
			ps.notifyEvent(Helper.getAny(dataWarpper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerRegainHeath (EntityRegainHealthEvent event){
		try{
			if(event.getEntity() instanceof Player){
				Player player = (Player) event.getEntity();
				PlayerRegainHeathEventDataImpl data = new PlayerRegainHeathEventDataImpl(player.getName(), getPlayerPosition(player), RegainHealthReason.from_int(event.getRegainReason().ordinal()), event.getAmount());
				PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
				ps.notifyEvent(Helper.getAny(dataWarpper));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerDamage (EntityDamageEvent event){
		try{
			if(event.getEntity() instanceof Player){
				Player player = (Player) event.getEntity();
				PlayerDamageEventDataImpl data = new PlayerDamageEventDataImpl(player.getName(), getPlayerPosition(player), DamageCause.from_int(event.getCause().ordinal()), event.getDamage());
				PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
				ps.notifyEvent(Helper.getAny(dataWarpper));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerStarvationLevelChange (FoodLevelChangeEvent event){
		try{
			if(event.getEntity() instanceof Player){
				Player player = (Player) event.getEntity();
				PlayerFoodLevelEventDataImpl data = new PlayerFoodLevelEventDataImpl(player.getName(), getPlayerPosition(player), event.getFoodLevel());
				PlayerEventDataWrapperImpl dataWarpper = new PlayerEventDataWrapperImpl(data);
				ps.notifyEvent(Helper.getAny(dataWarpper));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Position getPlayerPosition(Player player){
		Location location = player.getLocation();
		return new PositionImpl(location.getX(), location.getY(), location.getZ());
	}
	
	private void verifyPlayerPresence(Player player){
		for(Building building : BuildingDataBase.buildings.values()){
			if(isPlayerAtBuilding(player, building)){
				Bukkit.getServer().getPluginManager().callEvent(new BuildingPresenceEvent(player, building));
				break;
			}
		}
	}
	
	private boolean isPlayerAtBuilding(Player player, Building building){
		if(player == null || building == null) return false;
		
		Position position = building.getCenter();
		Location location = new Location(Server.world, position.getX(), position.getY(), position.getZ());
		Block center = location.getBlock();
		int xWidth = (int) building.getXWidth();
		int zWidth = (int) building.getZWidth();
		
		Block playerBlock = player.getLocation().getBlock();
		
		if(playerBlock.getX() > center.getX() - xWidth && playerBlock.getX() < center.getX() + xWidth && playerBlock.getZ() > center.getZ() - zWidth && playerBlock.getZ() < center.getZ() + zWidth && playerBlock.getY() >= center.getY() && playerBlock.getY() < center.getY() + building.getHeight()){
			return true;
		}
		return false;
	}
	
}
