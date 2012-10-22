package br.ufes.inf.lprm.bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataBase {

	private static final String PARTIAL_PATH = "CraftContext/";
	
	public static void save(String fileName, Object obj) {
		String path = PARTIAL_PATH + fileName;
		
		try {
			File saveFolder = new File(PARTIAL_PATH); 
			if (!saveFolder.exists() || !saveFolder.isDirectory()) saveFolder.mkdirs();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("ERROR: Could not save the object at path " + path + ".");		
	}
	
	public static Object load(String fileName) {
		String path = PARTIAL_PATH + fileName;
		try {
			if((new File(path)).exists()){
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
				return ois.readObject();
			}else return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("ERROR: Could not load the object at path " + path + ". (br.ufes.inf.lprm.bukkit.Info.load)");
		return null;
	}
	
}
