package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/PlayerDamageEventDataPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/

public abstract class PlayerDamageEventDataPOA extends org.omg.PortableServer.Servant
 implements br.ufes.inf.lprm.generated.PlayerDamageEventDataOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getPlayerName", new java.lang.Integer (0));
    _methods.put ("getPosition", new java.lang.Integer (1));
    _methods.put ("getCause", new java.lang.Integer (2));
    _methods.put ("getDamage", new java.lang.Integer (3));
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
       case 0:  // br/ufes/inf/lprm/generated/PlayerDamageEventData/getPlayerName
       {
         String $result = null;
         $result = this.getPlayerName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // br/ufes/inf/lprm/generated/PlayerDamageEventData/getPosition
       {
         br.ufes.inf.lprm.generated.Position $result = null;
         $result = this.getPosition ();
         out = $rh.createReply();
         br.ufes.inf.lprm.generated.PositionHelper.write (out, $result);
         break;
       }

       case 2:  // br/ufes/inf/lprm/generated/PlayerDamageEventData/getCause
       {
         br.ufes.inf.lprm.generated.DamageCause $result = null;
         $result = this.getCause ();
         out = $rh.createReply();
         br.ufes.inf.lprm.generated.DamageCauseHelper.write (out, $result);
         break;
       }

       case 3:  // br/ufes/inf/lprm/generated/PlayerDamageEventData/getDamage
       {
         int $result = (int)0;
         $result = this.getDamage ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/PlayerDamageEventData:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public PlayerDamageEventData _this() 
  {
    return PlayerDamageEventDataHelper.narrow(
    super._this_object());
  }

  public PlayerDamageEventData _this(org.omg.CORBA.ORB orb) 
  {
    return PlayerDamageEventDataHelper.narrow(
    super._this_object(orb));
  }


} // class PlayerDamageEventDataPOA
