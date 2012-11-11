package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/BuildingAddCommandDataPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/

public abstract class BuildingAddCommandDataPOA extends org.omg.PortableServer.Servant
 implements br.ufes.inf.lprm.generated.BuildingAddCommandDataOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getPlayerName", new java.lang.Integer (0));
    _methods.put ("getBuildingName", new java.lang.Integer (1));
    _methods.put ("getBuildingType", new java.lang.Integer (2));
    _methods.put ("getBuildingAddress", new java.lang.Integer (3));
    _methods.put ("getArea", new java.lang.Integer (4));
    _methods.put ("getHeight", new java.lang.Integer (5));
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
       case 0:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getPlayerName
       {
         String $result = null;
         $result = this.getPlayerName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getBuildingName
       {
         String $result = null;
         $result = this.getBuildingName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getBuildingType
       {
         String $result = null;
         $result = this.getBuildingType ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getBuildingAddress
       {
         String $result = null;
         $result = this.getBuildingAddress ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getArea
       {
         br.ufes.inf.lprm.generated.Area $result = null;
         $result = this.getArea ();
         out = $rh.createReply();
         br.ufes.inf.lprm.generated.AreaHelper.write (out, $result);
         break;
       }

       case 5:  // br/ufes/inf/lprm/generated/BuildingAddCommandData/getHeight
       {
         double $result = (double)0;
         $result = this.getHeight ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/BuildingAddCommandData:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public BuildingAddCommandData _this() 
  {
    return BuildingAddCommandDataHelper.narrow(
    super._this_object());
  }

  public BuildingAddCommandData _this(org.omg.CORBA.ORB orb) 
  {
    return BuildingAddCommandDataHelper.narrow(
    super._this_object(orb));
  }


} // class BuildingAddCommandDataPOA