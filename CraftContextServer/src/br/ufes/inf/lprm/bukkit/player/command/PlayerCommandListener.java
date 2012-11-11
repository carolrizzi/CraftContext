package br.ufes.inf.lprm.bukkit.player.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.ufes.inf.lprm.bukkit.player.PlayerProperties;
import br.ufes.inf.lprm.bukkit.player.command.data.PlayerDamageCommandDataImpl;
import br.ufes.inf.lprm.bukkit.player.command.data.PlayerFoodLevelCommandDataImpl;
import br.ufes.inf.lprm.jacorb.Helper;
import br.ufes.inf.lprm.jacorb.Properties;
import br.ufes.inf.lprm.jacorb.PushSupplier;

public class PlayerCommandListener implements CommandExecutor{

	private ProxyPushConsumer ppc;
	private PushSupplier ps;

	public PlayerCommandListener() throws NotFound, CannotProceed, InvalidName {
		ppc = Helper.getProxyPushConsumer(Properties.getCommandChannel(PlayerProperties.SUBJECT));
		ps = new PushSupplier(ppc, "Player");
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
		
		if (command.getName().equals(PlayerProperties.SUBJECT)){
			if (args.length < 1){
				sender.sendMessage("Please, specify the command type: /player [ set_food | set_health ] [ parameters ]");
				return true;
			}
			
			try {
				PlayerCommandDataWrapperImpl dataWrapper = null;
				
				if(args[0].equals(PlayerProperties.Commands.SET_FOOD_LEVEL)){
					if(args.length != 2){
						sender.sendMessage("Please, especify the parameters: /player set_food < level ( int: 0-20 ) >");
						return true;
					}
					
					PlayerFoodLevelCommandDataImpl dataFood = new PlayerFoodLevelCommandDataImpl(player.getName(), Integer.parseInt(args[1]));
					dataWrapper = new PlayerCommandDataWrapperImpl(dataFood);
				
				}else if(args[0].equals(PlayerProperties.Commands.SET_HEALTH)){
					if(args.length != 2){
						sender.sendMessage("Please, especify the parameters.\n/player set_health < level ( int: 0-20 ) >");
						return true;
					}
					
					PlayerDamageCommandDataImpl dataDamage = new PlayerDamageCommandDataImpl(player.getName(), Integer.parseInt(args[1]));
					dataWrapper = new PlayerCommandDataWrapperImpl(dataDamage);
					
				}else{
					sender.sendMessage("Invalid command type. Try: /player [ set_food | set_health ] [ parameters ]");
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

}
