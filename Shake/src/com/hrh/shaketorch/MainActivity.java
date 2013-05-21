package com.hrh.shaketorch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.hrh.screentorch.ScreenTorch;




public class MainActivity extends Activity
{
	private  Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//is Flash light available
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
        	Log.i("support","flasg light");
        	for(int i=0;i<1;i++)
        	{
        		Toast toast=Toast.makeText(context,"To switch ON or OFF the Flash-Light just shake the  mobile," +
					"While shaking make sure that swing must meet the accelerometer sensor limit. To run in Background go to " +
					"Menu->Preferences->Enable service.",Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
        		toast.show();
        	}
        	Intent intent=new Intent(MainActivity.this,ShakeActivity.class);
        	MainActivity.this.startActivity(intent);
        }
        else 
        {
        	Log.i("Does not support","flasg light");
        	for(int i=0;i<1;i++)
        	{
        	Toast toast=Toast.makeText(context,"Your mobile doesn't have Flash Light...!!" +
        			"Screen torch Enabled.",Toast.LENGTH_LONG);
        	toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
        	toast.show();
        	}
        	Intent intent=new Intent(MainActivity.this,ScreenTorch.class);
        	MainActivity.this.startActivity(intent);
        }
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("MainActivity","In Ondestroy()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("MainActivity","In OnPause()");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("MainActivity","In OnRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("MainActivity","In OnResume()");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("MainActivity","In OnStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("MainActivity","In OnStop()");
	}

	

}
