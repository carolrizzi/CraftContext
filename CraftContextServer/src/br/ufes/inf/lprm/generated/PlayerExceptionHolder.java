package br.ufes.inf.lprm.generated;

/**
* br/ufes/inf/lprm/generated/PlayerExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/

public final class PlayerExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public br.ufes.inf.lprm.generated.PlayerException value = null;

  public PlayerExceptionHolder ()
  {
  }

  public PlayerExceptionHolder (br.ufes.inf.lprm.generated.PlayerException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.ufes.inf.lprm.generated.PlayerExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.ufes.inf.lprm.generated.PlayerExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.ufes.inf.lprm.generated.PlayerExceptionHelper.type ();
  }

}
