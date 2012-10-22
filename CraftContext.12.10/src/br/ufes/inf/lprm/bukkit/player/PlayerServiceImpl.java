package br.ufes.inf.lprm.bukkit.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import br.ufes.inf.lprm.bukkit.common.PositionImpl;
import br.ufes.inf.lprm.generated.PlayerException;
import br.ufes.inf.lprm.generated.PlayerNotFound;
import br.ufes.inf.lprm.generated.PlayerServicePOA;
import br.ufes.inf.lprm.generated.Position;

public class PlayerServiceImpl extends PlayerServicePOA{

	private Server server;

	public PlayerServiceImpl() {
		server = Bukkit.getServer();
	}
	
	@Override
	public String[] getConnectedPlayers() throws PlayerException{
		try{
			Player [] players = server.getOnlinePlayers();
			String [] names = new String [players.length];
			for (int i = 0; i < players.length; i++){
				names[i] = players[i].getName();
			}
			return names;
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_01);
		}
	}

	@Override
	public boolean isPlayerConnected(String playerName) throws PlayerException {
		try{
			if(server.getPlayer(playerName) == null) return false;
			return true;
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_02);
		}
	}

	@Override
	public void disconnectPlayer(String playerName, String message) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_03);
			
		try{
			player.kickPlayer(message);
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_03);
		}
	}

	@Override
	public Position getPlayerPosition(String playerName) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_04);
		
		try{
			Location location = player.getLocation();
			return new PositionImpl(location.getX(), location.getY(), location.getZ());
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_04);
		}
	}

	@Override
	public void sendMessageToPlayer(String playerName, String message) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_05);
		
		try{
			player.sendMessage(message);
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_05);
		}
	}

	@Override
	public void sendMessageToConnectedPlayers(String message) throws PlayerException {
		try{
			server.broadcastMessage(message);
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_06);
		}
	}

	@Override
	public int getPlayerFoodLevel(String playerName) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_07);
		
		try{
			return player.getFoodLevel();
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_07);
		}
	}

	@Override
	public void setPlayerFoodLevel(String playerName, int level) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_08);
		
		try{
			int currentFoodLevel = player.getFoodLevel();
			if(currentFoodLevel != level){
				player.setFoodLevel(level);
				FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, level);
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_08);
		}
	}

	@Override
	public int getPlayerHealth(String playerName) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_09);
		
		try{
			return player.getHealth();
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_09);
		}
	}

	@Override
	public void setPlayerHealth(String playerName, int health) throws PlayerNotFound, PlayerException {
		Player player = getPlayer(playerName, PlayerProperties.Errors.SERVICE_10);
		
		try{
			int currentHealth = player.getHealth();
			player.setHealth(health);
			
			Event event = null;
			if(currentHealth > health){
				event = new EntityDamageEvent(player, DamageCause.CUSTOM, health);
			}else if (currentHealth < health){
				event = new EntityRegainHealthEvent(player, health, RegainReason.CUSTOM);
			}
			Bukkit.getServer().getPluginManager().callEvent(event);
		}catch (Exception e) {
			throw new PlayerException(PlayerProperties.Errors.SERVICE_10);
		}
	}

	private Player getPlayer(String playerName, String error) throws PlayerException, PlayerNotFound{
		Player player = null;
		try{
			player = server.getPlayer(playerName);
		}catch (Exception e) {
			throw new PlayerException(error);
		}
		if(player == null) throw new PlayerNotFound(playerName);
		return player;
	}
	
}
