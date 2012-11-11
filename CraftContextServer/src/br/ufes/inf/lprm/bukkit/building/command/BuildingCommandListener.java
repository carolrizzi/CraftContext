package br.ufes.inf.lprm.bukkit.building.command;

import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.bukkit.building.BuildingProperties;
import br.ufes.inf.lprm.bukkit.building.command.data.BuildingAddCommandDataImpl;
import br.ufes.inf.lprm.bukkit.building.command.data.BuildingListCommandDataImpl;
import br.ufes.inf.lprm.bukkit.building.command.data.BuildingRemoveCommandDataImpl;
import br.ufes.inf.lprm.bukkit.common.AreaImpl;
import br.ufes.inf.lprm.bukkit.common.PositionImpl;
import br.ufes.inf.lprm.generated.Area;
import br.ufes.inf.lprm.generated.Position;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.jacorb.PushSupplier;

public class BuildingCommandListener implements CommandExecutor{

	private ProxyPushConsumer ppc;
	private PushSupplier ps;

	public BuildingCommandListener() throws NotFound, CannotProceed, InvalidName {
		ppc = Helper.getProxyPushConsumer(Properties.getCommandChannel(BuildingProperties.SUBJECT));
		ps = new PushSupplier(ppc, "Building");
	}

	public void disconnectConsumers(){
		ppc.disconnect_push_consumer();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player){
			player = (Player) sender;
		}else{
			sender.sendMessage("Only players can use this command.");
			return true;
		}
		
		if (command.getName().equals(BuildingProperties.SUBJECT)){
			if (args.length < 1){
				sender.sendMessage("Please, specify the command type: /building [ add | remove | list ] [ parameters ]");
				return true;
			}
			
			try {
				BuildingCommandDataWrapperImpl dataWrapper = null;
				
				if(args[0].equals(BuildingProperties.Commands.ADD)){
					if(args.length < 7){
						sender.sendMessage("Please, especify the parameters: /building add < \"name\" ( string ) > < \"type\" ( string ) > < \"address\" ( string ) > < xWidth ( double ) > < zWidth ( double ) > < height ( double ) >");
						return true;
					}
					
					Location location = player.getLocation();
					Position position = new PositionImpl(location.getX(), location.getY()-1, location.getZ());

					AtomicInteger index = new AtomicInteger(1);
					String name = getParameter(args, index, "name", sender);
					if(name == null) return true;
					
					String type = getParameter(args, index, "type", sender);
					if(type == null) return true;
					
					String address = getParameter(args, index, "address", sender);
					if(address == null) return true;
					
					Area area = new AreaImpl(position, Double.parseDouble(args[index.getAndAdd(1)]), Double.parseDouble(args[index.getAndAdd(1)]));
					BuildingAddCommandDataImpl data = new BuildingAddCommandDataImpl(player.getName(), name, type, address, area, Double.parseDouble(args[index.getAndAdd(1)]));
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
				
				}else if(args[0].equals(BuildingProperties.Commands.REMOVE)){
					if(args.length < 3){
						sender.sendMessage("Please, especify the parameters: /building remove < \"name\" ( string ) > < physicalDestruction ( boolean ) >");
						return true;
					}
					
					AtomicInteger index = new AtomicInteger(1);
					String name = getParameter(args, index, "name", sender);
					if(name == null) return true;
					
					BuildingRemoveCommandDataImpl data = new BuildingRemoveCommandDataImpl(player.getName(), name, Boolean.parseBoolean(args[index.get()]));
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
					
				}else if(args[0].equals(BuildingProperties.Commands.LIST)){
					BuildingListCommandDataImpl data = new BuildingListCommandDataImpl(player.getName());
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
					
				}else{
					sender.sendMessage("Invalid command type. Try: /building [ add | remove | list ] [ parameters ]");
					return true;
				}
				
				ps.notifyEvent(Helper.getAny(dataWrapper));
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	private String getParameter (String[] args, AtomicInteger index, String subject, CommandSender sender) {
		if(!args[index.get()].startsWith("\"")){
			sender.sendMessage("The building's " + subject + " must be between quotes. Example: \"My building's " + subject + "\"");
			return null;
		}
		
		String str = args[index.getAndAdd(1)].substring(1);
		if(str.endsWith("\"")) return str.substring(0, str.length() - 1);
		for(; !args[index.get()].endsWith("\""); index.addAndGet(1)){
			str += " " + args[index.get()];
		}
		str += " " + args[index.get()].substring(0, args[index.getAndAdd(1)].length() - 1);
		return str;
	}
	
}
