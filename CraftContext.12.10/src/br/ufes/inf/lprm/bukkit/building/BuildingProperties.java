package br.ufes.inf.lprm.bukkit.building;

public class BuildingProperties {
	
	public static final String SUBJECT = "building";
	
	public static class Commands {
		public static final String ADD = "add";
		public static final String REMOVE = "remove";
		public static final String LIST = "list";
	}
	
	public static class Errors {
		public static final String SERVICE_01 = "Error while retrieving the existent buildings.";
		public static final String SERVICE_02 = "Error while retrieving buildings by type.";
		public static final String SERVICE_03 = "Error while listing existent buildings to player.";
		public static final String SERVICE_04 = "Error while adding a new building.";
		public static final String SERVICE_05 = "Error while physically destroying an existent building.";
	}
  
}
