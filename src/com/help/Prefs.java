/**
 * @AppName		: Call Help
 * @Developer	: Amanjeev "AJ"
 * @Created		: December 15, 2009
 * @Updated		: June 4, 2011
 * @license		: MIT
 */

package com.help;

import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Prefs extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceStatePrefs) {
		super.onCreate(savedInstanceStatePrefs);
		addPreferencesFromResource(R.xml.preferences);
	}
	
	/* Menu section */
	private static final int MENU_HOME = Menu.FIRST;
	private static final int MENU_EXIT = Menu.FIRST + 1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_HOME, MENU_HOME, "Home");
		menu.add(0, MENU_EXIT, MENU_EXIT, "Exit");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_HOME:
			final Intent intent = new Intent();
			intent.setClass(this, Call.class);
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