package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/PlayerRespawnEventDataPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/

public abstract class PlayerRespawnEventDataPOA extends org.omg.PortableServer.Servant
 implements br.ufes.inf.lprm.generated.PlayerRespawnEventDataOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getPlayerName", new java.lang.Integer (0));
    _methods.put ("getPosition", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // br/ufes/inf/lprm/generated/PlayerRespawnEventData/getPlayerName
       {
         String $result = null;
         $result = this.getPlayerName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // br/ufes/inf/lprm/generated/PlayerRespawnEventData/getPosition
       {
         br.ufes.inf.lprm.generated.Position $result = null;
         $result = this.getPosition ();
         out = $rh.createReply();
         br.ufes.inf.lprm.generated.PositionHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/PlayerRespawnEventData:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public PlayerRespawnEventData _this() 
  {
    return PlayerRespawnEventDataHelper.narrow(
    super._this_object());
  }

  public PlayerRespawnEventData _this(org.omg.CORBA.ORB orb) 
  {
    return PlayerRespawnEventDataHelper.narrow(
    super._this_object(orb));
  }


} // class PlayerRespawnEventDataPOA
