package br.ufes.inf.lprm.generated;

/**
* br/ufes/inf/lprm/generated/BuildingServiceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/


// --- SERVICES --- //
public final class BuildingServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public br.ufes.inf.lprm.generated.BuildingService value = null;

  public BuildingServiceHolder ()
  {
  }

  public BuildingServiceHolder (br.ufes.inf.lprm.generated.BuildingService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.ufes.inf.lprm.generated.BuildingServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.ufes.inf.lprm.generated.BuildingServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.ufes.inf.lprm.generated.BuildingServiceHelper.type ();
  }

}
