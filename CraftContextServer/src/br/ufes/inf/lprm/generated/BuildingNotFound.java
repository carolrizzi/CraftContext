package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/BuildingNotFound.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/

public final class BuildingNotFound extends org.omg.CORBA.UserException
{
  public String name = null;

  public BuildingNotFound ()
  {
    super(BuildingNotFoundHelper.id());
  } // ctor

  public BuildingNotFound (String _name)
  {
    super(BuildingNotFoundHelper.id());
    name = _name;
  } // ctor


  public BuildingNotFound (String $reason, String _name)
  {
    super(BuildingNotFoundHelper.id() + "  " + $reason);
    name = _name;
  } // ctor

} // class BuildingNotFound
