package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/BuildingHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/


// --- VALUETYPES --- //
abstract public class BuildingHelper
{
  private static String  _id = "IDL:br/ufes/inf/lprm/generated/Building:1.0";


  public static void insert (org.omg.CORBA.Any a, br.ufes.inf.lprm.generated.Building that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static br.ufes.inf.lprm.generated.Building extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.ValueMember[] _members0 = new org.omg.CORBA.ValueMember[4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          // ValueMember instance for name
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.ValueMember ("name", 
              "", 
              _id, 
              "", 
              _tcOf_members0, 
              null, 
              org.omg.CORBA.PRIVATE_MEMBER.value);
          // ValueMember instance for type
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.ValueMember ("type", 
              "", 
              _id, 
              "", 
              _tcOf_members0, 
              null, 
              org.omg.CORBA.PRIVATE_MEMBER.value);
          // ValueMember instance for height
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[2] = new org.omg.CORBA.ValueMember ("height", 
              "", 
              _id, 
              "", 
              _tcOf_members0, 
              null, 
              org.omg.CORBA.PRIVATE_MEMBER.value);
          // ValueMember instance for address
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.ValueMember ("address", 
              "", 
              _id, 
              "", 
              _tcOf_members0, 
              null, 
              org.omg.CORBA.PRIVATE_MEMBER.value);
          __typeCode = org.omg.CORBA.ORB.init ().create_value_tc (_id, "Building", org.omg.CORBA.VM_NONE.value, br.ufes.inf.lprm.generated.AreaHelper.type (), _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static br.ufes.inf.lprm.generated.Building read (org.omg.CORBA.portable.InputStream istream)
  {
    return (br.ufes.inf.lprm.generated.Building)((org.omg.CORBA_2_3.portable.InputStream) istream).read_value (id ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, br.ufes.inf.lprm.generated.Building value)
  {
    ((org.omg.CORBA_2_3.portable.OutputStream) ostream).write_value (value, id ());
  }


}