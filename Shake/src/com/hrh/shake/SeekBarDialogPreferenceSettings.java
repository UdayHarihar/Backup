package com.hrh.shake;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


public class SeekBarDialogPreferenceSettings extends
		PreferenceActivity implements OnPreferenceClickListener,OnPreferenceChangeListener,
		SharedPreferences.OnSharedPreferenceChangeListener {
	
	private Preference aboutDeveloper;
	private Preference aboutApp;
	private ListPreference appService;
	private SharedPreferences settings;
	private String applicationService;
	private Builder builder;
	private  Context context = this;
	private boolean flag;
	
	 private static final String PREFERENCE_KEY = "seekBar";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getPreferenceManager().setSharedPreferencesName(
		//	"com.hrh.shaketorch.seekbar");
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		addPreferencesFromResource(R.xml.torch_preference);
		getPreferenceManager().getSharedPreferences().
			registerOnSharedPreferenceChangeListener(this);
		// about developer
		aboutDeveloper = (Preference) getPreferenceScreen().findPreference(Constants.PREF_KEY_ABOUT_DEVELOPER);

		// about app
		aboutApp = (Preference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_ABOUT_APP);
		
		// declare the view objects
		appService = (ListPreference) getPreferenceScreen().findPreference(
				Constants.PREF_KEY_CHANGE_SERVICE);
		
	

		// Set the state of the app version listing items based on the saved
		// preference - they are not handled by default
		if (settings.contains(Constants.PREF_KEY_CHANGE_SERVICE)) {
			applicationService = settings.getString(
					Constants.PREF_KEY_CHANGE_SERVICE, "");
			// App version preference is already set
		//	mlog("app service preference is present");
			if (applicationService.equals(Constants.DISABLE)) {
				appService.setValue(Constants.DISABLE);
			} 
			else {
				appService.setValue(Constants.ENABLE);
			}
			
		
		}
	}
	

	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences().
			unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
	}

	protected void onResume() {
		appService.setOnPreferenceChangeListener(this);
		aboutApp.setOnPreferenceClickListener(this);
		aboutDeveloper.setOnPreferenceClickListener(this);
		super.onResume();
	}

	protected void onPause() {
		appService.setOnPreferenceChangeListener(null);
		aboutApp.setOnPreferenceClickListener(null);
		aboutDeveloper.setOnPreferenceClickListener(null);
		super.onPause();
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

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
	/*	if (key.equals(PREFERENCE_KEY)) {
		    // Notify that value was really changed
		    int value = sharedPreferences.getInt(PREFERENCE_KEY, 0);
		    Toast.makeText(this, getString(R.string.summary, value), Toast.LENGTH_LONG).show();
		}*/
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
				flag=isMyServiceRunning();
				if (flag)
				{
					Toast.makeText(context,"Service is running..!!",Toast.LENGTH_LONG).show();	
				}
				else 
				{
					Intent i=new Intent(this,ShakeService.class);
					this.startService(i);
				}
				
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
		        stopService(new Intent(this, ShakeService.class));

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
	
	  private boolean isMyServiceRunning() {
		    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
		        if (ShakeService.class.getName().equals(service.service.getClassName())) {
		            return true;
		        }
		    }
		    return false;
		}	

}
