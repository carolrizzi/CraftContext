package br.ufes.inf.lprm.generated;

/**
* br/ufes/inf/lprm/generated/BuildingRemoveCommandDataHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/

public final class BuildingRemoveCommandDataHolder implements org.omg.CORBA.portable.Streamable
{
  public br.ufes.inf.lprm.generated.BuildingRemoveCommandData value = null;

  public BuildingRemoveCommandDataHolder ()
  {
  }

  public BuildingRemoveCommandDataHolder (br.ufes.inf.lprm.generated.BuildingRemoveCommandData initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.ufes.inf.lprm.generated.BuildingRemoveCommandDataHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.ufes.inf.lprm.generated.BuildingRemoveCommandDataHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.ufes.inf.lprm.generated.BuildingRemoveCommandDataHelper.type ();
  }

}
