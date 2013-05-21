package com.hrh.shake;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;



public class ShakeActivity extends Activity {

	private  Context context = this;
	private boolean flag;
    private boolean isFlashOn = false;
	private Camera camera;
	private ShakeListener mShaker;
	private KeyguardLock lock;
	private Builder builder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake);
		
		flag=isMyServiceRunning();
		if (flag)
		{
			Toast.makeText(context,"Service is running..!!",Toast.LENGTH_LONG).show();	
		}
		else 
		{
			Intent i=new Intent(this,ShakeService.class);
			this.startService(i);
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
			Editor editor = settings.edit();
			editor.putString("change_app_service", "ENABLE");
			editor.commit();
		}
		
	}

	  @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	   /*     Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);    */
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_preference:
        	handlePreferenceClick();
        return true;
        case R.id.help2:
        	aboutHelperDialog();
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }
    private void aboutHelperDialog() {
		// TODO Auto-generated method stub
		int dialogIcon = R.drawable.ic_menu_info_details;
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Help")
				.setIcon(dialogIcon)
				.setMessage(
						Html.fromHtml(getString(R.string.en_help)));
		builder.create().show();
	}
    
    /**
	 * Start the preference activity as a subactivity
	 * 
	 */
	private void handlePreferenceClick() {

		Intent preferenceIntent = new Intent(ShakeActivity.this,
				SampleSeekBarDialogPreferenceSettings.class);
		startActivityForResult(preferenceIntent, 0);
	}
	  
	
}
