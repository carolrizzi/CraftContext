package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventChannelAdmin.TypeError;
import org.omg.CosEventComm.PushConsumerOperations;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import test.consumer.building.BuildingCommandConsumer;
import test.consumer.building.BuildingEventConsumer;
import test.consumer.player.PlayerCommandConsumer;
import test.consumer.player.PlayerEventConsumer;
import br.ufes.inf.lprm.building.BuildingProperties;
import br.ufes.inf.lprm.building.BuildingServiceManager;
import br.ufes.inf.lprm.generated.Building;
import br.ufes.inf.lprm.generated.BuildingException;
import br.ufes.inf.lprm.generated.BuildingNotFound;
import br.ufes.inf.lprm.generated.BuildingService;
import br.ufes.inf.lprm.generated.PlayerException;
import br.ufes.inf.lprm.generated.PlayerNotFound;
import br.ufes.inf.lprm.generated.PlayerService;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.jacorb.Server;
import br.ufes.inf.lprm.player.PlayerProperties;
import br.ufes.inf.lprm.player.PlayerServiceManager;

public class CraftContextTest {

	class Menu extends Thread{
		private Server server;
		
		public Menu(Server server) {
			this.server = server;
		}
		
		private void showMenu(){
			System.out.println("Player Commands:");
			System.out.println("(p0) get-connected-players");
			System.out.println("(p1) is-player-connected <player-name>");
			System.out.println("(p2) disconnect-player <player-name> <message>");
			System.out.println("(p3) get-player-position <player-name>");
			System.out.println("(p4) send-message-to-player <player-name> <message>");
			System.out.println("(p5) send-message-to-connected-players <message>");
			System.out.println("(p6) get-player-food-level <player-name>");
			System.out.println("(p7) set-player-food-level <player-name> <level>(0-20)");
			System.out.println("(p8) get-player-health <player-name>");
			System.out.println("(p9) set-player-health <player-name> <health>(0-20)");
			
			System.out.println("\nBuilding Commands:");
			System.out.println("(b0) get-existing-buildings");
			System.out.println("(b1) get-building <building-name>");
			System.out.println("(b2) get-building-by-type <type>");
		}
		
		public void printParametersError(){
			System.err.println("Error: Incorrect amount of parameters.");
		}
		
		public void run (){
			System.out.println("Client Server has started. Type \"help\" for help.");
			
			PlayerService pService = null;
			try {
				pService = PlayerServiceManager.getService();
			} catch (NotFound e) {
				System.err.println("Service " + Properties.getService(PlayerProperties.SUBJECT) + " not found.\nExiting the system.");
				System.exit(1);
			} catch (CannotProceed e) {
				System.err.println("Cannot Proceed. Service name: " + Properties.getService(PlayerProperties.SUBJECT) + ".\nExiting the system.");
				System.exit(1);
			} catch (InvalidName e) {
				System.err.println("Invalid service name: " + Properties.getService(PlayerProperties.SUBJECT) + ".\nExiting the system.");
				System.exit(1);
			}
			
			BuildingService bService = null;
			try {
				bService = BuildingServiceManager.getService();
			} catch (NotFound e) {
				System.err.println("Service " + Properties.getService(BuildingProperties.SUBJECT) + " not found.\nExiting the system.");
				System.exit(1);
			} catch (CannotProceed e) {
				System.err.println("Cannot Proceed. Service name: " + Properties.getService(BuildingProperties.SUBJECT) + ".\nExiting the system.");
				System.exit(1);
			} catch (InvalidName e) {
				System.err.println("Invalid service name: " + Properties.getService(BuildingProperties.SUBJECT) + ".\nExiting the system.");
				System.exit(1);
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				try{
					String in[] = br.readLine().split(" ");
					String cmd = in[0];
					if (in[0].toLowerCase().equals("exit") || cmd.toLowerCase().equals("stop")){
						Channels.disconnectChannels();
						server.stopServer();
						System.out.println("Client was terminated.");
						break;
					}else if(cmd.toLowerCase().equals("help")){
						showMenu();
					}else if(cmd.toLowerCase().equals("p0") || cmd.toLowerCase().equals("get-connected-players")){
						String [] players = pService.getConnectedPlayers();
						if(players.length <= 0)
							System.out.println("There are no logged players.");
						else{
							System.out.println("Connected players:");
							for(String player : players)
								System.out.println("* " + player);
						}
					
					}else if(cmd.toLowerCase().equals("p1") || cmd.toLowerCase().equals("is-player-connected")){
						if(in.length < 2) printParametersError();
						else System.out.println("Is player " + in[1] + " connected: " + (pService.isPlayerConnected(in[1]) ? "yes" : "no"));
						
					}else if(cmd.toLowerCase().equals("p2") || cmd.toLowerCase().equals("disconnect-player")){
						if(in.length < 3) printParametersError();
						else {
							String msg = "";
							for(int i = 2; i < in.length; i++)
								msg += in[i] + " ";
							pService.disconnectPlayer(in[1], msg);
						}
						System.out.println("Player " + in[1] + " was disconnected.");
						
					}else if(cmd.toLowerCase().equals("p3") || cmd.toLowerCase().equals("get-player-position")){
						if(in.length < 2) printParametersError();
						else {
							Position position = pService.getPlayerPosition(in[1]);
							System.out.println(in[1] + "'s Position: X(" + position.getX() + "), Y(" + position.getY() + "), Z(" + position.getZ() + ")");
						}
						
					}else if(cmd.toLowerCase().equals("p4") || cmd.toLowerCase().equals("send-message-to-player")){
						if(in.length < 3) printParametersError();
						else {
							String msg = "";
							for(int i = 2; i < in.length; i++)
								msg += in[i] + " ";
							try{
								pService.sendMessageToPlayer(in[1], msg);
								System.out.println("Message successfully sent to player " + in[1] + ".");
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}else if(cmd.toLowerCase().equals("p5") || cmd.toLowerCase().equals("send-message-to-connected-players")){
						if(in.length < 2) printParametersError();
						else {
							String msg = "";
							for(int i = 1; i < in.length; i++)
								msg += in[i] + " ";
							try{
								pService.sendMessageToConnectedPlayers(msg);
								System.out.println("Message successfully sent to all connected players.");
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
						
					}else if(cmd.toLowerCase().equals("p6") || cmd.toLowerCase().equals("get-player-food-level")){
						if(in.length < 2) printParametersError();
						else System.out.println(in[1] + "'s Food Level: " + pService.getPlayerFoodLevel(in[1]));
						
					}else if(cmd.toLowerCase().equals("p7") || cmd.toLowerCase().equals("set-player-food-level")){
						if(in.length < 3) printParametersError();
						else {
							pService.setPlayerFoodLevel(in[1], Integer.parseInt(in[2]));
							System.out.println(in[1] + "'s food level was changed to " + in[2]);
						}
						
					}else if(cmd.toLowerCase().equals("p8") || cmd.toLowerCase().equals("get-player-health")){
						if(in.length < 2) printParametersError();
						else System.out.println(in[1] + "'s Health Level: " + pService.getPlayerHealth(in[1]));
						
					}else if(cmd.toLowerCase().equals("p9") || cmd.toLowerCase().equals("set-player-health")){
						if(in.length < 3) printParametersError();
						else {
							pService.setPlayerHealth(in[1], Integer.parseInt(in[2]));
							System.out.println(in[1] + "'s health level was changed to " + in[2]);
						}
						
					}else if(cmd.toLowerCase().equals("b0") || cmd.toLowerCase().equals("get-existing-buildings")){
						Building [] buildings = bService.getExistingBuildings();
						if(buildings.length <= 0)
							System.out.println("There are no buildings.");
						else{
							System.out.println("Existent Buildings:");
							for(Building building : buildings)
								System.out.println("* " + building.getName());
						}
						
					}else if(cmd.toLowerCase().equals("b1") || cmd.toLowerCase().equals("get-building")){
						if(in.length < 2) printParametersError();
						else{
							Building building = null;
							int i = 1;
							String name = in[i++];
							for(; i < in.length; i++)
								name += " " + in[i];
							building = bService.getBuilding(name);
							System.out.println("Building Information:");
							System.out.println("* Name: " + building.getName());
							System.out.println("* Type: " + building.getType());
							System.out.println("* Address: " + building.getAddress());
							System.out.println("* x Width: " + building.getXWidth());
							System.out.println("* z Width: " + building.getZWidth());
							System.out.println("* Height: " + building.getHeight());
						}
						
					}else if(cmd.toLowerCase().equals("b2") || cmd.toLowerCase().equals("get-building-by-type")){
						if(in.length < 2) printParametersError();
						else{
							int i = 1;
							String type = in[i++];
							for(; i < in.length; i++)
								type += " " + in[i];
							Building [] buildings = bService.getBuildingByType(type);
							if(buildings.length <= 0)
								System.out.println("There are no buildings with the specified type: " + type);
							else{
								System.out.println("Buildings with type " + type + ":");
								for(Building building : buildings)
									System.out.println("* " + building.getName());
							}
						}
						
					}else{
						System.err.println("Error: Invalid command or parameters.");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (PlayerNotFound e) {
					System.out.println("Player not found: " + e.name);
				} catch (PlayerException e) {
					System.err.println("Unkown Player Exception: " + e.msg);
				} catch (BuildingNotFound e) {
					System.out.println("Building not found: " + e.name);
				} catch (BuildingException e) {
					System.err.println("Unkown Building Exception: " + e.msg);
				} catch (IOException e) {
					System.out.println("IOException: Could not read user's command.");
				}
				try {
					sleep(500);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public CraftContextTest() {
		try{
			Server server = new Server();
			Channels.connectChannels();
			
			Menu menu = new Menu(server);
			menu.start();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String []args){
		new CraftContextTest();
	}
	
}

class Channels {

	private static ProxyPushSupplier playerEvent;
	private static ProxyPushSupplier buildingEvent;
	
	private static ProxyPushSupplier playerCommand;
	private static ProxyPushSupplier buildingCommand;
	
	private static ProxyPushSupplier registerPushConsumer(String channelName, PushConsumerOperations pco){
		try {
			return Helper.registerPushConsumer(channelName, pco);
		} catch (NotFound e) {
			System.err.println("Event Channel " + channelName + " not found.");
		} catch (CannotProceed e) {
			System.err.println("Cannot Proceed.");
		} catch (InvalidName e) {
			System.err.println("Invalid Channel Name: " + channelName + ".");
		} catch (ServantNotActive e) {
			System.err.println("Servant for channel " + channelName + " is not active.");
		} catch (WrongPolicy e) {
			System.err.println("Wrong Policy.");
		} catch (AlreadyConnected e) {
			System.err.println("Event channel " + channelName + " is already connected.");
		} catch (TypeError e) {
			System.err.println("Type Error.");
		} catch (Exception e) {
			System.err.println("Unknown error during channel registration.");
		}
		System.err.println("Could not register the " + Properties.getEventChannel(PlayerProperties.SUBJECT) + " channel.");
		return null;
	}
	
	public static void connectChannels(){
		playerEvent = registerPushConsumer(Properties.getEventChannel(PlayerProperties.SUBJECT), new PlayerEventConsumer());
		buildingEvent = registerPushConsumer(Properties.getEventChannel(BuildingProperties.SUBJECT), new BuildingEventConsumer());
		
		playerCommand = registerPushConsumer(Properties.getCommandChannel(PlayerProperties.SUBJECT), new PlayerCommandConsumer());
		buildingCommand = registerPushConsumer(Properties.getCommandChannel(BuildingProperties.SUBJECT), new BuildingCommandConsumer());
	}
	
	public static void disconnectChannels(){
		try{
			playerEvent.disconnect_push_supplier();
		}catch (Exception e) {}
		
		try{
			buildingEvent.disconnect_push_supplier();
		}catch (Exception e) {}

		try{
			playerCommand.disconnect_push_supplier();
		}catch (Exception e) {}
		
		try{
			buildingCommand.disconnect_push_supplier();
		}catch (Exception e) {}
	}

}