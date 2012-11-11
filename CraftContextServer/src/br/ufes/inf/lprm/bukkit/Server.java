package br.ufes.inf.lprm.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import br.ufes.inf.lprm.bukkit.building.BuildingDataBase;
import br.ufes.inf.lprm.bukkit.building.BuildingProperties;
import br.ufes.inf.lprm.bukkit.building.BuildingServiceImpl;
import br.ufes.inf.lprm.bukkit.building.command.BuildingCommandListener;
import br.ufes.inf.lprm.bukkit.building.event.BuildingEventListener;
import br.ufes.inf.lprm.bukkit.player.PlayerProperties;
import br.ufes.inf.lprm.bukkit.player.PlayerServiceImpl;
import br.ufes.inf.lprm.bukkit.player.command.PlayerCommandListener;
import br.ufes.inf.lprm.bukkit.player.event.PlayerEventListener;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;

public class Server extends JavaPlugin{

	public static World world;
	
	private br.ufes.inf.lprm.jacorb.Server jacorbServer;
	private PluginDescriptionFile pluginDescription;
	
	@Override
	public void onLoad() {
		pluginDescription = getDescription();
	}
	
	@Override
	public void onDisable() {
		try{
			DataBaseHelper.saveAllData();
			EventHelper.disconnectPushSupplier();
			CommandHelper.disconnectPushSupplier();
			jacorbServer.stopServer();
			System.out.println("The plugin " + pluginDescription.getName() + " was terminated.");
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR: Problem while disabling the plugin " + pluginDescription.getName() + ".");
		}
	}
	
	@Override
	public void onEnable() {
		try{
			world = getServer().getWorlds().get(0);
			
			System.out.println("Loading data...");
			
			DataBaseHelper.loadAllData();
			jacorbServer = new br.ufes.inf.lprm.jacorb.Server();
			InitHelper.startChannels();
			InitHelper.startServices();
			EventHelper.registerEventListeners(this);
			CommandHelper.registerCommandListeners(this);
			jacorbServer.start();
			
			System.out.println("The plugin " + pluginDescription.getName() + " was started. (Version " + pluginDescription.getVersion() + ")");
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("ERROR: Problem while starting the plugin " + pluginDescription.getName() + ".");
		}
	}
	
}

class DataBaseHelper {
	public static void saveAllData () {
		BuildingDataBase.save();
	}
	
	public static void loadAllData () {
		BuildingDataBase.load();
	}
}

class InitHelper {
	public static void startServices () throws NotFound, CannotProceed, InvalidName, AlreadyBound, ServantNotActive, WrongPolicy{
		Helper.initService(Properties.getService(PlayerProperties.SUBJECT), new PlayerServiceImpl());
		Helper.initService(Properties.getService(BuildingProperties.SUBJECT), new BuildingServiceImpl());
	}
	
	public static void startChannels() {
		Helper.initChannel(Properties.getEventChannel(PlayerProperties.SUBJECT));
		Helper.initChannel(Properties.getEventChannel(BuildingProperties.SUBJECT));

		Helper.initChannel(Properties.getCommandChannel(PlayerProperties.SUBJECT));
		Helper.initChannel(Properties.getCommandChannel(BuildingProperties.SUBJECT));
	}
	
}

class EventHelper {
	
	private static PlayerEventListener playerListener;
	private static BuildingEventListener buildingListener;
	
	public static void registerEventListeners(Server plugin){
		PluginManager pm = Bukkit.getServer().getPluginManager();

		if(playerListener == null){
			try{
				playerListener = new PlayerEventListener();
				pm.registerEvents(playerListener, plugin);
			}catch (Exception e) {
				printError(e, PlayerProperties.SUBJECT);
			}
		}

		if(buildingListener == null){
			try{
				buildingListener = new BuildingEventListener();
				pm.registerEvents(buildingListener, plugin);
			}catch (Exception e) {
				printError(e, BuildingProperties.SUBJECT);
			}
		}
		
	}
	
	public static void disconnectPushSupplier() {
		try{
			if(playerListener != null)
				playerListener.disconnectConsumers();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			if(buildingListener != null)
				buildingListener.disconnectConsumers();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printError(Exception e, String event){
		System.err.println("ERROR: Could not register the " + event + " event.");
		e.printStackTrace();
	}
}

class CommandHelper {
	
	private static PlayerCommandListener playerListener;
	private static BuildingCommandListener buildingListener;
	
	public static void registerCommandListeners(Server plugin) {
		try{
			if(playerListener == null){
				playerListener = new PlayerCommandListener();
				plugin.getCommand(PlayerProperties.SUBJECT).setExecutor(playerListener);
			}
		}catch (Exception e) {
			printError(e, PlayerProperties.SUBJECT);
		}
		
		try{
			if(buildingListener == null){
				buildingListener = new BuildingCommandListener();
				plugin.getCommand(BuildingProperties.SUBJECT).setExecutor(buildingListener);
			}
		}catch (Exception e) {
			printError(e, BuildingProperties.SUBJECT);
		}
	}

	public static void disconnectPushSupplier(){
		try{
			if(playerListener != null)
				playerListener.disconnectConsumers();
		}catch (Exception e) {
			e.printStackTrace();
		}

		try{
			if(buildingListener != null)
				buildingListener.disconnectConsumers();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printError(Exception e, String command){
		System.err.println("ERROR: Could not set executor to the command " + command);
		e.printStackTrace();
	}
	
}
