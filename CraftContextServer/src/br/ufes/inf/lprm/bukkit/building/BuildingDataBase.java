package br.ufes.inf.lprm.bukkit.building;

import java.util.HashMap;

import br.ufes.inf.lprm.bukkit.DataBase;
import br.ufes.inf.lprm.generated.Building;

public class BuildingDataBase {

	public static HashMap<String, Building> buildings = null;
	private static final String FILE_NAME = "building.crc";
	
	public static void save(){
		DataBase.save(FILE_NAME, buildings);
	}
	
	@SuppressWarnings("unchecked")
	public static void load() {
		try{
			Object obj = DataBase.load(FILE_NAME);
			if(obj == null)
				buildings = new HashMap<String, Building>();
			else{
				if (obj instanceof HashMap<?, ?>)
					buildings = (HashMap<String, Building>) obj;
				else {
					System.err.println("ERROR: The provided object does not have the correct type. Creating a new one. (br.ufes.inf.lprm.bukkit.Info.getObject)");
					throw new Exception();
				}
			}
		}catch (Exception e) {
			buildings = new HashMap<String, Building>();
		}
	}
	
}
