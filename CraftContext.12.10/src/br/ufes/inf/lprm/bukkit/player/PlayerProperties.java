package br.ufes.inf.lprm.bukkit.player;

public class PlayerProperties {
	
	public static final String SUBJECT = "player";
	
	public static class Commands {
		public static final String SET_HEALTH = "set_health";
		public static final String SET_FOOD_LEVEL = "set_food";
	}
	
	public static class Errors {
		public static final String SERVICE_01 = "Error while retrieving the connected players.";
		public static final String SERVICE_02 = "Error while verifying the player's connection.";
		public static final String SERVICE_03 = "Error while disconnecting player.";
		public static final String SERVICE_04 = "Error while retrieving the player's position.";
		public static final String SERVICE_05 = "Error while sending message to player.";
		public static final String SERVICE_06 = "Error while sending message to connected players.";
		public static final String SERVICE_07 = "Error while retrieving the starvation level of the player.";
		public static final String SERVICE_08 = "Error while setting the starvation level of the player";
		public static final String SERVICE_09 = "Error while retrieving the player's health.";
		public static final String SERVICE_10 = "Error while setting the player's health.";
	}
  
}
