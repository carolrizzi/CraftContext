package br.ufes.inf.lprm.generated;

/**
* br/ufes/inf/lprm/generated/BuildingHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/


// --- VALUETYPES --- //
public final class BuildingHolder implements org.omg.CORBA.portable.Streamable
{
  public br.ufes.inf.lprm.generated.Building value = null;

  public BuildingHolder ()
  {
  }

  public BuildingHolder (br.ufes.inf.lprm.generated.Building initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.ufes.inf.lprm.generated.BuildingHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.ufes.inf.lprm.generated.BuildingHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.ufes.inf.lprm.generated.BuildingHelper.type ();
  }

}
