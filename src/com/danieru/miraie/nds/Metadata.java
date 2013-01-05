package com.danieru.miraie.nds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class Metadata {

	private HashMap<String, String> metadata;
	private File metadataFile;

	@SuppressWarnings("unchecked")
	public Metadata(String metadataFilename) {
		metadataFile = new File(Filespace.getMetadataFolder() +'/'+ metadataFilename);
		
		try {
			FileInputStream fis = new FileInputStream(metadataFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

            metadata = (HashMap<String, String>) ois.readObject();

            ois.close();
            fis.close();
		} catch (Exception e) {
			metadata = new HashMap<String, String>();
			
		}
	}
	
	private boolean saveMetadata() {
		try {
            FileOutputStream fos = new FileOutputStream(metadataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(metadata);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            return false;
        }
		return true;
	}
	
	public String get(String key) {
		return metadata.get(key);
	}
	
	public boolean set(String key, String val) {
		metadata.put(key, val);
		return saveMetadata();
	}
}
