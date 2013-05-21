package com.hrh.shaketorch;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


/**
 * <p>
 *Shake Torch App
 * </p>
 * Preference Activity class <br>
 * 
 * purpose: The application preferences activity, <br>
 * <br>
 * 
 * 
 * @author Uday V.H
 * @version 1.0 30-05-2012
 * 
 *          Copyright HRH
 */
public class ActivityPreferences extends PreferenceActivity implements
		OnPreferenceChangeListener, OnPreferenceClickListener ,SharedPreferences.OnSharedPreferenceChangeListener{

	 

	public static int maximum    = 100;
	public static int interval   = 5;
	private float oldValue = 25;
	private ListPreference appService;
	private SharedPreferences settings;
	private String applicationService;
	private Preference aboutDeveloper;
	private Preference aboutApp;
	
	private Builder builder;
	private  Context context = this;
	private boolean flag;
	



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// preference resource is added
	
	addPreferencesFromResource(R.xml.torch_preference);

		// get the preference
		settings = PreferenceManager.getDefaultSharedPreferences(this);

		// declare the view objects
		appService = (ListPreference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_CHANGE_SERVICE);
		
	

		// Set the state of the app version listing items based on the saved
		// preference - they are not handled by default
		if (settings.contains(Constants.PREF_KEY_CHANGE_SERVICE)) {
			applicationService = settings.getString(
					Constants.PREF_KEY_CHANGE_SERVICE, "");
			// App version preference is already set
			mlog("app service preference is present");
			if (applicationService.equals(Constants.DISABLE)) {
				appService.setValue(Constants.DISABLE);
			} 
			else {
				appService.setValue(Constants.ENABLE);
			}
			
		
		}

	
		// about developer
		aboutDeveloper = (Preference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_ABOUT_DEVELOPER);

		// about app
		aboutApp = (Preference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_ABOUT_APP);
		
	
	

	}

	@Override
	protected void onResume() {
		super.onResume();
		appService.setOnPreferenceChangeListener(this);
		aboutApp.setOnPreferenceClickListener(this);
		aboutDeveloper.setOnPreferenceClickListener(this);
	}

	@Override
	protected void onPause() {
		mlog("in onpause");
		appService.setOnPreferenceChangeListener(null);
		aboutApp.setOnPreferenceClickListener(null);
		aboutDeveloper.setOnPreferenceClickListener(null);
		super.onPause();
	}

	@Override
	protected void onStop() {
		mlog("on stop called");
		super.onStop();
	}

	/**
	 * Overridden this method because the changes made to the preferences should
	 * be applied when this activity is closed and an application activity is
	 * opened. In this case the calling activity launched this activity as a sub
	 * activity so the result is sent back. In onActivity returned method of
	 * calling class, reload application is called.
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_OK);
			finish();
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void mlog(String msg) {
		// Log.d(DEBUG_TAG, msg);
	}

	/**
	 * Called when the preference in the preference list is changed. Since the
	 * app service preference is not dependent on any other preference elements
	 * save the changes manually
	 * 
	 */
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


	@Override
	public boolean onPreferenceClick(Preference preference) 
	{
		//mlog("clicked");

		if (preference.getKey().equals(Constants.PREF_KEY_ABOUT_DEVELOPER)) {
			mlog("about dev");
			aboutDeveloperDialog();
		}

		if (preference.getKey().equals(Constants.PREF_KEY_ABOUT_APP)) {
			mlog("about app");
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
	public void onBackPressed() {
		// TODO Auto-generated method stub

		super.onBackPressed();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}


}
