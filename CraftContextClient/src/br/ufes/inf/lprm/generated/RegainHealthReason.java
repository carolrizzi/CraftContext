package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/RegainHealthReason.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from common.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/


// --- ENUMERATIONS --- //
public class RegainHealthReason implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 7;
  private static br.ufes.inf.lprm.generated.RegainHealthReason[] __array = new br.ufes.inf.lprm.generated.RegainHealthReason [__size];

  public static final int _REGEN = 0;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason REGEN = new br.ufes.inf.lprm.generated.RegainHealthReason(_REGEN);
  public static final int _SATIATED = 1;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason SATIATED = new br.ufes.inf.lprm.generated.RegainHealthReason(_SATIATED);
  public static final int _EATING = 2;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason EATING = new br.ufes.inf.lprm.generated.RegainHealthReason(_EATING);
  public static final int _ENDER_CRYSTAL = 3;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason ENDER_CRYSTAL = new br.ufes.inf.lprm.generated.RegainHealthReason(_ENDER_CRYSTAL);
  public static final int _MAGIC_REASON = 4;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason MAGIC_REASON = new br.ufes.inf.lprm.generated.RegainHealthReason(_MAGIC_REASON);
  public static final int _MAGIC_REGEN = 5;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason MAGIC_REGEN = new br.ufes.inf.lprm.generated.RegainHealthReason(_MAGIC_REGEN);
  public static final int _CUSTOM_REASON = 6;
  public static final br.ufes.inf.lprm.generated.RegainHealthReason CUSTOM_REASON = new br.ufes.inf.lprm.generated.RegainHealthReason(_CUSTOM_REASON);

  public int value ()
  {
    return __value;
  }

  public static br.ufes.inf.lprm.generated.RegainHealthReason from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected RegainHealthReason (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class RegainHealthReason