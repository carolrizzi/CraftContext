package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/_BuildingListCommandDataStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from building.idl
* Sunday, October 21, 2012 10:12:54 PM BRST
*/

public class _BuildingListCommandDataStub extends org.omg.CORBA.portable.ObjectImpl implements br.ufes.inf.lprm.generated.BuildingListCommandData
{

  public String getPlayerName ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPlayerName", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPlayerName (        );
            } finally {
                _releaseReply ($in);
            }
  } // getPlayerName

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/BuildingListCommandData:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _BuildingListCommandDataStub
