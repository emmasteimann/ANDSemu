package com.danieru.miraie.nds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;
import com.danieru.miraie.nds.MainActivity.IntentActions;

public class Home extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Filespace.prepFolders();
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.home);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.home, menu);
	    return true;
	}
	
	public void gotoSettings(View view) {
        Intent settingsIntent = new Intent(view.getContext(), Settings.class);
        startActivityForResult(settingsIntent, 0);
	}
	
	public void pickRom(View view) {
		Intent i = new Intent(this, FileDialog.class);
		i.setAction(Intent.ACTION_PICK);
		i.putExtra(FileDialog.START_PATH, Filespace.getGameFolder() + '/');
		i.putExtra(FileDialog.FORMAT_FILTER, new String[] {".nds", ".zip", ".7z"});
		startActivityForResult(i, MainActivity.PICK_ROM);
	}
	
	public void playRom(Uri romPath) {
        Intent playIntent = new Intent(this, MainActivity.class);
        playIntent.setAction(MainActivity.IntentActions.LOAD);
        playIntent.setData(romPath);
        startActivity(playIntent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_settings:
	            Intent settingsIntent = new Intent(this, Settings.class);
	            startActivity(settingsIntent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MainActivity.PICK_ROM) {
		     if(resultCode == RESULT_OK) {
		    	 playRom(Uri.parse(data.getStringExtra(FileDialog.RESULT_PATH)));
		     }
		}
	}
}
