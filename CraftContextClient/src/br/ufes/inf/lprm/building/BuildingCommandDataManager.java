package br.ufes.inf.lprm.building;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;

import br.ufes.inf.lprm.building.exception.BuildingCommandTypeError;
import br.ufes.inf.lprm.generated.BuildingAddCommandData;
import br.ufes.inf.lprm.generated.BuildingAddCommandDataHelper;
import br.ufes.inf.lprm.generated.BuildingCommandDataWrapper;
import br.ufes.inf.lprm.generated.BuildingCommandDataWrapperHelper;
import br.ufes.inf.lprm.generated.BuildingCommandType;
import br.ufes.inf.lprm.generated.BuildingListCommandData;
import br.ufes.inf.lprm.generated.BuildingListCommandDataHelper;
import br.ufes.inf.lprm.generated.BuildingRemoveCommandData;
import br.ufes.inf.lprm.generated.BuildingRemoveCommandDataHelper;

public class BuildingCommandDataManager {

	private BuildingCommandType command;
	private Object data;
	
	/**
	 * Given an Any object, creates a manager for manipulating command data of Building type.
	 * @param any The Any object from where the command data will be extracted.
	 * @throws BuildingCommandTypeError The object extracted from the given Any object is not an command data of Building type.
	 */
	public BuildingCommandDataManager(Any any) throws BuildingCommandTypeError {
		try{
			BuildingCommandDataWrapper dataWrapper = BuildingCommandDataWrapperHelper.narrow(any.extract_Object());
			this.command = dataWrapper.getCommandType();
			this.data = dataWrapper.getCommandData();
		}catch (Exception e) {
			throw new BuildingCommandTypeError();
		}
	}
	
	/**
	 * @return The command type;
	 */
	public BuildingCommandType getCommandType(){
		return command;
	}
	
	/**
	 * @return Command data containing information for adding a new building in the game.
	 * <pre>
	 * Data Content:
	 * - The building's name
	 * - The building's type (such as a label)
	 * - The building's address
	 * - The building's area
	 * - The building's height
	 * - Name of the player who is requesting the building's creation
	 * </pre>
	 * @throws BuildingCommandTypeError The command type is not BuildingCommandType.CMD_ADD.
	 */
	public BuildingAddCommandData getBuildingAddCommandData () throws BuildingCommandTypeError {
		if(!command.equals(BuildingCommandType.CMD_ADD)) throw new BuildingCommandTypeError();
		return BuildingAddCommandDataHelper.narrow(data);
	}
	
	/**
	 * @return Command data containing information for removing from the game an existing building.
	 * <pre>
	 * Data Content:
	 * - The building's name
	 * - Name of the player who is requesting the building's removal
	 * </pre>
	 * @throws BuildingCommandTypeError The command type is not BuildingCommandType.CMD_REMOVE.
	 */
	public BuildingRemoveCommandData getBuildingRemoveCommandData () throws BuildingCommandTypeError {
		if(!command.equals(BuildingCommandType.CMD_REMOVE)) throw new BuildingCommandTypeError();
		return BuildingRemoveCommandDataHelper.narrow(data);
	}
	
	/**
	 * @return Command data containing information for listing to the player the existing buildings in the game.
	 * <pre>
	 * Data Content:
	 * - Name of the player who is requesting the existing buildings list
	 * </pre>
	 * @throws BuildingCommandTypeError The command type is not BuildingCommandType.CMD_LIST.
	 */
	public BuildingListCommandData getBuildingListCommandData () throws BuildingCommandTypeError {
		if(!command.equals(BuildingCommandType.CMD_LIST)) throw new BuildingCommandTypeError();
		return BuildingListCommandDataHelper.narrow(data);
	}
	
}
