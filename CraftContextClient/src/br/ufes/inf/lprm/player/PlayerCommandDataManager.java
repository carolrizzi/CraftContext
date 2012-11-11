package br.ufes.inf.lprm.player;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;

import br.ufes.inf.lprm.generated.PlayerCommandDataWrapper;
import br.ufes.inf.lprm.generated.PlayerCommandDataWrapperHelper;
import br.ufes.inf.lprm.generated.PlayerCommandType;
import br.ufes.inf.lprm.generated.PlayerDamageCommandData;
import br.ufes.inf.lprm.generated.PlayerDamageCommandDataHelper;
import br.ufes.inf.lprm.generated.PlayerFoodLevelCommandData;
import br.ufes.inf.lprm.generated.PlayerFoodLevelCommandDataHelper;
import br.ufes.inf.lprm.player.exception.PlayerCommandTypeError;

public class PlayerCommandDataManager {

	private PlayerCommandType command;
	private Object data;
	
	/**
	 * Given an Any object, creates a manager for manipulating command data of Player type.
	 * @param any The Any object from where the command data will be extracted.
	 * @throws PlayerCommandTypeError The object extracted from the given Any object is not an command data of Player type.
	 */
	public PlayerCommandDataManager(Any any) throws PlayerCommandTypeError {
		try{
			PlayerCommandDataWrapper dataWrapper = PlayerCommandDataWrapperHelper.narrow(any.extract_Object());
			this.command = dataWrapper.getCommandType();
			this.data = dataWrapper.getCommandData();
		}catch (Exception e) {
			throw new PlayerCommandTypeError();
		}
	}
	
	/**
	 * @return The command type;
	 */
	public PlayerCommandType getCommandType(){
		return command;
	}
	
	/**
	 * @return Command data containing information for changing a player's starvation condition.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current starvation level
	 * </pre>
	 * @throws PlayerCommandTypeError The command type is not PlayerCommandType.CMD_FOOD_LEVEL.
	 */
	public PlayerFoodLevelCommandData getPlayerFoodLevelCommandData () throws PlayerCommandTypeError {
		if(!command.equals(PlayerCommandType.CMD_FOOD_LEVEL)) throw new PlayerCommandTypeError();
		return PlayerFoodLevelCommandDataHelper.narrow(data);
	}
	
	/**
	 * @return Command data containing information for changing a player's health condition.
	 * <pre>
	 * Data Content:
	 * - Player's name
	 * - Player's current health level
	 * </pre>
	 * @throws PlayerCommandTypeError The command type is not PlayerCommandType.CMD_HEALTH.
	 */
	public PlayerDamageCommandData getPlayerDamageCommandData () throws PlayerCommandTypeError {
		if(!command.equals(PlayerCommandType.CMD_HEALTH)) throw new PlayerCommandTypeError();
		return PlayerDamageCommandDataHelper.narrow(data);
	}
	
}
