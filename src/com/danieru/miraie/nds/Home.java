package com.danieru.miraie.nds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Home extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.home);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.home, menu);
	    return true;
	}
	
	public void gotoSettings(View view) {
        Intent settingsIntent = new Intent(view.getContext(), Settings.class);
        startActivityForResult(settingsIntent, 0);
	}
	
	public void pickROM(View view) {
        Intent playIntent = new Intent(view.getContext(), MainActivity.class);
        startActivityForResult(playIntent, 0);
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
}