package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/PlayerCommandType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/

public class PlayerCommandType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static br.ufes.inf.lprm.generated.PlayerCommandType[] __array = new br.ufes.inf.lprm.generated.PlayerCommandType [__size];

  public static final int _CMD_FOOD_LEVEL = 0;
  public static final br.ufes.inf.lprm.generated.PlayerCommandType CMD_FOOD_LEVEL = new br.ufes.inf.lprm.generated.PlayerCommandType(_CMD_FOOD_LEVEL);
  public static final int _CMD_HEALTH = 1;
  public static final br.ufes.inf.lprm.generated.PlayerCommandType CMD_HEALTH = new br.ufes.inf.lprm.generated.PlayerCommandType(_CMD_HEALTH);

  public int value ()
  {
    return __value;
  }

  public static br.ufes.inf.lprm.generated.PlayerCommandType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected PlayerCommandType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class PlayerCommandType