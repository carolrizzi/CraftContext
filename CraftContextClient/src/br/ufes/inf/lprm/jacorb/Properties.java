package br.ufes.inf.lprm.jacorb;

public class Properties {
	
	/**
	 * Returns the service name given a subject. Example:
	 * <pre>
	 * {@code
	 * String serviceName = getService("player");
	 * or
	 * String serviceName = getService(PlayerProperties.SUBJECT);
	 * }
	 * </pre>
	 * @param subject Service's subject.
	 * @return Service's name.
	 */
	public static String getService(String subject){
		return "service." + subject;
	}
	
	/**
	 * Returns the event channel name given a subject. Example:
	 * <pre>
	 * {@code
	 * String channelName = getEventChannel("player");
	 * or
	 * String channelName = getEventChannel(PlayerProperties.SUBJECT);
	 * }
	 * </pre>
	 * @param subject Event Channel's subject.
	 * @return Event Channel's name. 
	 */
	public static String getEventChannel(String subject){
		return "eventchannel." + subject;
	}
	
	/**
	 * Returns the command channel name given a subject. Example:
	 * <pre>
	 * {@code
	 * String channelName = getCommandChannel("player");
	 * or
	 * String channelName = getCommandChannel(PlayerProperties.SUBJECT);
	 * }
	 * </pre>
	 * @param subject Command Channel's subject.
	 * @return Command Channel's name.
	 */
	public static String getCommandChannel(String subject){
		return "commandchannel." + subject;
	}
	
}
