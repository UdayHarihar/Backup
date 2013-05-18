/**
 * Copyright CMW Mobile.com, 2010. 
 */
package com.hrh.shaketorch;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.text.Html;
import android.util.Log;

/**
 * The SampleSeekBarDialogPreferenceSettings is responsible for the handling of
 * this sample settings.
 * @author Casper Wakkers
 */

public class SampleSeekBarDialogPreferenceSettings extends
		PreferenceActivity implements OnPreferenceClickListener,OnPreferenceChangeListener,
		SharedPreferences.OnSharedPreferenceChangeListener {
	
	private Preference aboutDeveloper;
	
	private Preference aboutApp;
	private SharedPreferences settings;
	private Builder builder;
	private  Context context = this;
	private boolean flag;
	
	

	
	/**
	 * {@inheritDoc}
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getPreferenceManager().setSharedPreferencesName(
			"com.hrh.shaketorch.seekbar");
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		addPreferencesFromResource(R.xml.torch_preference);
		getPreferenceManager().getSharedPreferences().
			registerOnSharedPreferenceChangeListener(this);
		
		aboutDeveloper = (Preference) getPreferenceScreen().findPreference(Constants.PREF_KEY_ABOUT_DEVELOPER);

		// about app
aboutApp = (Preference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_ABOUT_APP);
	}
	// about developer
	
	
	/**
	 * {@inheritDoc}
	 */
	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences().
			unregisterOnSharedPreferenceChangeListener(this);

		super.onDestroy();
	}
	/**
	 * {@inheritDoc}
	 */
	protected void onResume() {
		super.onResume();
		aboutApp.setOnPreferenceClickListener(this);
		aboutDeveloper.setOnPreferenceClickListener(this);
	}
	

	protected void onPause() {
		aboutApp.setOnPreferenceClickListener(null);
		aboutDeveloper.setOnPreferenceClickListener(null);
		super.onPause();
	}

	/**
	 * {@inheritDoc}
	 */
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		
	}
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		if (preference.getKey().equals(Constants.PREF_KEY_ABOUT_DEVELOPER)) {
			
			aboutDeveloperDialog();
		}

		if (preference.getKey().equals(Constants.PREF_KEY_ABOUT_APP)) {
			//mlog("about app");
			aboutAppDialog();
		}

		return true;
	}
	
	/**
	 * Dialog that handles deleting of a database table
	 * 
	 */
	private void aboutDeveloperDialog() {

		int dialogIcon = R.drawable.ic_menu_info_details;
		builder = new AlertDialog.Builder(this);
		builder.setTitle("About developer")
				.setIcon(dialogIcon)
				.setMessage(
						Html.fromHtml(getString(R.string.en_about_developer)));
		builder.create().show();
	}

	/**
	 * Dialog that handles deleting of a database table
	 * 
	 */
	private void aboutAppDialog() {
		// TODO
		int dialogIcon = R.drawable.ic_menu_info_details;

		builder = new AlertDialog.Builder(this);
		builder.setTitle("About application")
				.setIcon(dialogIcon)
				.setMessage(
						Html.fromHtml(getString(R.string.en_about_application)));
		builder.create().show();
	}


	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference.getKey().equals(Constants.PREF_KEY_CHANGE_SERVICE)) {
			Log.i("service","preference changed to "+ newValue);
			if (((String) newValue).equals(Constants.ENABLE)) {
				
				Log.i("service","preference changed to enable");
				
				
			/*	if (applicationService.equals(Constants.DISABLE)) {
					// Preferences are changed automatically if the return type
					// is true
					// save changes only if the this differs previously saved
					// preference
					// editor = settings.edit();
					// editor.putString(Constants.PREF_KEY_CHANGE_ENCODING,
					// Constants.ASCII);
					// editor.commit();

					// loadAscii database
					//loadAsciiDatabase();
					Log.i("service","load from enable");
				}*/

			} else if (((String) newValue).equals(Constants.DISABLE)) {			
				Log.i("service","preference changed to disabled");
			//	Intent i=new Intent(this, ShakeService.class);
			//	this.stopService(i);
				//stop the service from here 
		      //  stopService(new Intent(this, ShakeService.class));

			/*	if (applicationService.equals(Constants.ENABLE)) {
					// save changes only if the this differs previously saved
					// preference
					// editor = settings.edit();
					// editor.putString(Constants.PREF_KEY_CHANGE_ENCODING,
					// Constants.UNICODE);
					// editor.commit();

					// load unicode database
					//loadUnicodeDatabase();
					Log.i("service","load from disable");
				}*/
			}
		}
		return true;
	}
	
		

}
