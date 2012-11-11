package br.ufes.inf.lprm.generated;


/**
* br/ufes/inf/lprm/generated/_PlayerServiceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from player.idl
* Monday, August 13, 2012 5:46:14 PM BRT
*/


// --- SERVICES --- //
public class _PlayerServiceStub extends org.omg.CORBA.portable.ObjectImpl implements br.ufes.inf.lprm.generated.PlayerService
{

  public String[] getConnectedPlayers () throws br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getConnectedPlayers", true);
                $in = _invoke ($out);
                String $result[] = br.ufes.inf.lprm.generated.stringsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getConnectedPlayers (        );
            } finally {
                _releaseReply ($in);
            }
  } // getConnectedPlayers

  public boolean isPlayerConnected (String player) throws br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("isPlayerConnected", true);
                $out.write_string (player);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return isPlayerConnected (player        );
            } finally {
                _releaseReply ($in);
            }
  } // isPlayerConnected

  public void disconnectPlayer (String player, String message) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("disconnectPlayer", true);
                $out.write_string (player);
                $out.write_string (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                disconnectPlayer (player, message        );
            } finally {
                _releaseReply ($in);
            }
  } // disconnectPlayer

  public br.ufes.inf.lprm.generated.Position getPlayerPosition (String player) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPlayerPosition", true);
                $out.write_string (player);
                $in = _invoke ($out);
                br.ufes.inf.lprm.generated.Position $result = br.ufes.inf.lprm.generated.PositionHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPlayerPosition (player        );
            } finally {
                _releaseReply ($in);
            }
  } // getPlayerPosition

  public void sendMessageToPlayer (String player, String message) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMessageToPlayer", true);
                $out.write_string (player);
                $out.write_string (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendMessageToPlayer (player, message        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMessageToPlayer

  public void sendMessageToConnectedPlayers (String message) throws br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMessageToConnectedPlayers", true);
                $out.write_string (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendMessageToConnectedPlayers (message        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMessageToConnectedPlayers

  public int getPlayerFoodLevel (String player) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPlayerFoodLevel", true);
                $out.write_string (player);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPlayerFoodLevel (player        );
            } finally {
                _releaseReply ($in);
            }
  } // getPlayerFoodLevel

  public void setPlayerFoodLevel (String player, int level) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setPlayerFoodLevel", true);
                $out.write_string (player);
                $out.write_long (level);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                setPlayerFoodLevel (player, level        );
            } finally {
                _releaseReply ($in);
            }
  } // setPlayerFoodLevel

  public int getPlayerHealth (String player) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPlayerHealth", true);
                $out.write_string (player);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPlayerHealth (player        );
            } finally {
                _releaseReply ($in);
            }
  } // getPlayerHealth

  public void setPlayerHealth (String player, int health) throws br.ufes.inf.lprm.generated.PlayerNotFound, br.ufes.inf.lprm.generated.PlayerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setPlayerHealth", true);
                $out.write_string (player);
                $out.write_long (health);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerNotFound:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerNotFoundHelper.read ($in);
                else if (_id.equals ("IDL:br/ufes/inf/lprm/generated/PlayerException:1.0"))
                    throw br.ufes.inf.lprm.generated.PlayerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                setPlayerHealth (player, health        );
            } finally {
                _releaseReply ($in);
            }
  } // setPlayerHealth

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:br/ufes/inf/lprm/generated/PlayerService:1.0"};

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
} // class _PlayerServiceStub