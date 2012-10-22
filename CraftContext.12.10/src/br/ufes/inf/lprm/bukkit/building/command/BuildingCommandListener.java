package br.ufes.inf.lprm.bukkit.building.command;

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
				sender.sendMessage("Please, specify the command type.");
				return false;
			}
			
			try {
				BuildingCommandDataWrapperImpl dataWrapper = null;
				
				if(args[0].equals(BuildingProperties.Commands.ADD)){
					if(args.length != 7){
						sender.sendMessage("Please, especify the parameters.\n/building add <name> <type> <address> <xWidth> <zWidth> <height>");
						return false;
					}
					
					Location location = player.getLocation();
					Position position = new PositionImpl(location.getX(), location.getY()-1, location.getZ());
					Area area = new AreaImpl(position, Double.parseDouble(args[4]), Double.parseDouble(args[5]));
					BuildingAddCommandDataImpl data = new BuildingAddCommandDataImpl(player.getName(), args[1], args[2], args[3], area, Double.parseDouble(args[6]));
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
				
				}else if(args[0].equals(BuildingProperties.Commands.REMOVE)){
					if(args.length != 3){
						sender.sendMessage("Please, especify the parameters.");
						return false;
					}
					
					BuildingRemoveCommandDataImpl data = new BuildingRemoveCommandDataImpl(player.getName(), args[1], Boolean.parseBoolean(args[2]));
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
					
				}else if(args[0].equals(BuildingProperties.Commands.LIST)){
					BuildingListCommandDataImpl data = new BuildingListCommandDataImpl(player.getName());
					dataWrapper = new BuildingCommandDataWrapperImpl(data);
					
				}else{
					return false;
				}
				
				ps.notifyEvent(Helper.getAny(dataWrapper));
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
