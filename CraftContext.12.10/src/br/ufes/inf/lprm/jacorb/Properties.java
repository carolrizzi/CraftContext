package br.ufes.inf.lprm.jacorb;

public class Properties {
	
	public static String getService(String subject){
		return "service." + subject;
	}
	
	public static String getEventChannel(String subject){
		return "eventchannel." + subject;
	}
	
	public static String getCommandChannel(String subject){
		return "commandchannel." + subject;
	}
	
}
