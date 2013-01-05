package com.danieru.miraie.nds;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class AutosaveManager {
	private Metadata autosaves;
	
	public class Autosave implements java.io.Serializable {
		private static final long serialVersionUID = 7156478122467657180L;
		public Date time;
		public String screenshot;
		public String romPath;
	}
	
	AutosaveManager() {
		autosaves = new Metadata("autosaves.metadata");
	}
	
	private Autosave saveAutosave(String name) {
		Autosave save = null;

		try {
			InputStream bis = new ByteArrayInputStream(autosaves.get(name).getBytes("UTF-8"));
	        ObjectInputStream ois = new ObjectInputStream(bis);
	        
	        save = (Autosave)ois.readObject();
	        
	        bis.close();
	        ois.close();
		} catch (Exception e) {
			// If system cannot handle utf-8 we're in super big trouble
			// Also if we get an io exception on an in ram string...
			// In other words: we should never get here!
			e.printStackTrace();
		}
		
		return save;
	}
	
	private void loadAutosave(String name, Autosave save) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(bos);
	        
	        oos.writeObject(save);
	        autosaves.set(name, bos.toString());
	        
	        bos.close();
	        oos.close();
		} catch (Exception e) {
			// We should never get here!
			e.printStackTrace();
		}
	}
}
