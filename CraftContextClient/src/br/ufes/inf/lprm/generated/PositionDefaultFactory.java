package br.ufes.inf.lprm.generated;



/**
* br/ufes/inf/lprm/generated/PositionDefaultFactory.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from common.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/


// --- VALUETYPES --- //
public class PositionDefaultFactory implements org.omg.CORBA.portable.ValueFactory {

  public java.io.Serializable read_value (org.omg.CORBA_2_3.portable.InputStream is)
  {
    return is.read_value(new PositionImpl ());
  }
}
