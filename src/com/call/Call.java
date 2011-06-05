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
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class Call extends Activity {

	private Button callButton = null;
	private String numberTextPref = null;
	private boolean enableTextPref = false;
	private String textMessagePref = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		/* The big fat call button */
		this.callButton = (Button) this.findViewById(R.id.callbutton);

		callButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					Toast.makeText(getBaseContext(),
							"Release to call " + numberTextPref, 2).show();
				}
				return false;
			}
		});

		this.callButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dial();
			}
		});

	}

	@Override
	public void onResume() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		numberTextPref = prefs.getString("numberfield", "911");
		enableTextPref = prefs.getBoolean("enabletextmessage", false);
		textMessagePref = prefs.getString("textmessagefield", "Please help me. I may be in trouble.");
		super.onResume();
	}

	public void dial() {
		try {
			/* Send a text message if the user wants and the number is not 911*/
			if (enableTextPref == true && numberTextPref.toString() != "911") {
				android.telephony.SmsManager sm = android.telephony.SmsManager.getDefault();
				sm.sendTextMessage(numberTextPref, null, textMessagePref, null, null);
			}

			/* Call, finally! */
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ numberTextPref)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Menu section */
	private static final int MENU_SETTINGS = Menu.FIRST;
	private static final int MENU_EXIT = Menu.FIRST + 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_SETTINGS, MENU_SETTINGS, "Settings");
		menu.add(0, MENU_EXIT, MENU_EXIT, "Exit");
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
		case MENU_EXIT:
			Intent exit_intent = new Intent(Intent.ACTION_MAIN);
			exit_intent.addCategory(Intent.CATEGORY_HOME);
			exit_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(exit_intent);
		}
		return true;
	}

	/* End: Menu section */

}
