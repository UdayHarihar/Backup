package com.hrh.shaketorch;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hrh.shake.*;

public class ShakeActivity extends Activity {

	private  Context context = this;
	private boolean flag;
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
			Intent i=new Intent(this, ShakeService.class);
			this.startService(i);
		}
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
	
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shake, menu);
		return true;
	}*/

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("ShakeActivity","In Ondestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("ShakeActivity","In OnPause()");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("ShakeActivity","In OnRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("ShakeActivity","In OnResume()");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("ShakeActivity","In OnStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("ShakeActivity","In OnStop()");
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
     /*   case R.id.help:
        	help();
        return true; */
        default:
        return super.onOptionsItemSelected(item);
        }
    }
    
    /**
	 * Start the preference activity as a subactivity
	 * 
	 */
	private void handlePreferenceClick() {

		Intent preferenceIntent = new Intent(ShakeActivity.this,
				ActivityPreferences.class);
		startActivityForResult(preferenceIntent, 0);
	}
	  



	
}
