package br.ufes.inf.lprm.generated;

/**
* br/ufes/inf/lprm/generated/DamageCauseHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from common.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/

public final class DamageCauseHolder implements org.omg.CORBA.portable.Streamable
{
  public br.ufes.inf.lprm.generated.DamageCause value = null;

  public DamageCauseHolder ()
  {
  }

  public DamageCauseHolder (br.ufes.inf.lprm.generated.DamageCause initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.ufes.inf.lprm.generated.DamageCauseHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.ufes.inf.lprm.generated.DamageCauseHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.ufes.inf.lprm.generated.DamageCauseHelper.type ();
  }

}
