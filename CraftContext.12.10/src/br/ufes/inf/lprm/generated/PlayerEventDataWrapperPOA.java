package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/PlayerEventDataWrapperPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/


// *** Player Event *** //
public abstract class PlayerEventDataWrapperPOA extends org.omg.PortableServer.Servant
 implements br.ufes.inf.lprm.generated.PlayerEventDataWrapperOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getEventType", new java.lang.Integer (0));
    _methods.put ("getEventData", new java.lang.Integer (1));
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
       case 0:  // br/ufes/inf/lprm/generated/PlayerEventDataWrapper/getEventType
       {
         br.ufes.inf.lprm.generated.PlayerEventType $result = null;
         $result = this.getEventType ();
         out = $rh.createReply();
         br.ufes.inf.lprm.generated.PlayerEventTypeHelper.write (out, $result);
         break;
       }

       case 1:  // br/ufes/inf/lprm/generated/PlayerEventDataWrapper/getEventData
       {
         org.omg.CORBA.Object $result = null;
         $result = this.getEventData ();
         out = $rh.createReply();
         org.omg.CORBA.ObjectHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/PlayerEventDataWrapper:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public PlayerEventDataWrapper _this() 
  {
    return PlayerEventDataWrapperHelper.narrow(
    super._this_object());
  }

  public PlayerEventDataWrapper _this(org.omg.CORBA.ORB orb) 
  {
    return PlayerEventDataWrapperHelper.narrow(
    super._this_object(orb));
  }


} // class PlayerEventDataWrapperPOA
