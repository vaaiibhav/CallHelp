/**
 * @AppName		: Call Help
 * @Developer	: Amanjeev "AJ"
 * @Created		: December 15, 2009
 * @Updated		: June 4, 2011
 * @license		: MIT
 */

package com.call;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


public class Call extends Activity {
	
	private Button callButton = null;
	private Button prefButton = null;
	private String numberTextPref = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.callButton = (Button) this.findViewById(R.id.callbutton);
		this.callButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dial();
			}
		});
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		numberTextPref = prefs.getString("numberfield", "911");
		
		Toast.makeText(
				getBaseContext(),
				numberTextPref,
				Toast.LENGTH_LONG).show();
		
		/*
		this.prefButton = (Button) this.findViewById(R.id.prefbutton);
		this.prefButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {				
				//setContentView(R.layout.prefs);
				Intent prefIntent = new Intent(Call.this, Prefs.class);
				Call.this.startActivity(prefIntent);
			}
		});
		*/
		
	}
	

/*	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			dial();
			return true;
		}
		return false;
	}
	
*/	
	public void dial() {
		try {
			
			/*ACTION_DIAL only pulls up the screen, ACTION_CALL will dial the number*/
			//startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7075022626")));
			
			//android.telephony.SmsManager sm = android.telephony.SmsManager.getDefault();
			//sm.sendTextMessage("7075022626", null, "test this biatch", null, null);

			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:7188085987")));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    private static final int MENU_SETTINGS = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_SETTINGS, MENU_SETTINGS, "Settings");
        return true;        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
        	case MENU_SETTINGS:
        		final Intent intent = new Intent();
        		intent.setClass(this, Prefs.class);
        		startActivity(intent);
        		break;
        }
        return true;
    }

	

}
