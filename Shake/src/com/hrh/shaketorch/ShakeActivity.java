package com.hrh.shaketorch;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.hrh.shaketorch.*;



public class ShakeActivity extends Activity {

	private  Context context = this;
	private boolean flag;
	//BroadcastReceiver mReceiver = new ScreenReceiver();
	//Create camera object to access flahslight
    private boolean isFlashOn = false;
	private Camera camera;
	private ShakeListener mShaker;
	private KeyguardLock lock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake);
		// initialize receiver
        //IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        //filter.addAction(Intent.ACTION_SCREEN_OFF);
     
       // registerReceiver(mReceiver, filter);
        
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
		// initialize receiver
       /*IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        
        registerReceiver(mReceiver, filter);*/
        // your code
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
		//unregisterReceiver(mReceiver);
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
				SampleSeekBarDialogPreferenceSettings.class);
		startActivityForResult(preferenceIntent, 0);
	}
	  
	
}
