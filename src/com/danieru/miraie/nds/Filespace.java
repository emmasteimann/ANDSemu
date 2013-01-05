package com.danieru.miraie.nds;

import java.io.File;
import java.util.Locale;

import android.os.Environment;

public class Filespace {

	/* Define directory structure ------------------------------------------ */
	
	private static String mainFolder = "/ANDSemu";
	private static String subFolders[] =
			{"/Games",
			 "/States",
			 "/Cheats",
			 "/Battery",
			 "/.temp",
			 "/.metadata"};

	static public String getMainFolder() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
																+ mainFolder; 
	}
	
	static public String getGameFolder() {
		return getMainFolder() + subFolders[0];
	}
	
	static public String getStateFolder() {
		return getMainFolder() + subFolders[1];
	}
	
	static public String getCheatFolder() {
		return getMainFolder() + subFolders[2];
	}
	
	static public String getBatteryFolder() {
		return getMainFolder() + subFolders[3];
	}
	
	static public String getTempFolder() {
		return getMainFolder() + subFolders[4];
	}
	
	static public String getMetadataFolder() {
		return getMainFolder() + subFolders[5];
	}
	
	
	/* Helpers for working with our filespace ------------------------------ */
	
	static public boolean isGame(File gameCandidate) {
		return gameCandidate.getAbsolutePath().toLowerCase(Locale.ENGLISH)
															.endsWith(".nds");
	}
	
	static public void prepFolders() {
		String parentDir = getMainFolder();
		
		//init directory hierarchy
		for (String folder : subFolders) {
			new File(parentDir + folder).mkdirs();
		}
		
		//clear any previously extracted ROMs
		final File[] cacheFiles = new File(getTempFolder()).listFiles();
		if (cacheFiles != null)
			for (File cacheFile : cacheFiles)
				if (isGame(cacheFile))
					cacheFile.delete();
		
		return;
	}
	
	static public boolean isGameFolderUsed() {
		final File[] contents = new File(getGameFolder()).listFiles();

		if (contents != null)
			for (File gameFile : contents)
				if (isGame(gameFile))  // ignore non-games
					return true;
		
		return false;
	}
}
