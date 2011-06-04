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

import android.app.PendingIntent;
import android.telephony.gsm.SmsManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Call extends Activity {
	
	private Button callButton = null;
	private Button prefButton = null;
	
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
		
		this.prefButton = (Button) this.findViewById(R.id.prefbutton);
		this.prefButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {				
				setContentView(R.layout.prefs);
				String FILENAME = "callhelpprefs";
				
			}
		});
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			dial();
			return true;
		}
		return false;
	}
	
	
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
	
	

}
