package com.hrh.screentorch;

import com.hrh.shake.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class ScreenTorch extends Activity {
	
	 private Context context;
	 /** Called when the activity is first created. */
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		 
		 super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_screen_torch);
	     
	   //is Flash light available
/*	        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
	        {
	        	Log.i("support","flasg light");
	        }
	        else Log.i("Does not support","flasg light");
*/	        

	     SeekBar BackLightControl = (SeekBar)findViewById(R.id.backlightcontrol);
	     final TextView BackLightSetting = (TextView)findViewById(R.id.backlightsetting);
	  
	     BackLightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

	 @Override
	 public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
	  // TODO Auto-generated method stub
	  float BackLightValue = (float)arg1/100;
	 
	  BackLightSetting.setText(String.valueOf(arg1)+"%");
	  if(arg1 > 5)
	  {
	 	WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
	 	layoutParams.screenBrightness = BackLightValue;
	 	getWindow().setAttributes(layoutParams);
	  }
	  else 
	  {
		  	/*Toast.makeText(MainActivity.this,"Can't decrease less then 10 %",Toast.LENGTH_LONG).show();
			WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
	  		layoutParams.screenBrightness = 0.10f;
	  		getWindow().setAttributes(layoutParams);*/
	  }
	
	 }

	 @Override
	 public void onStartTrackingTouch(SeekBar arg0) {
	  // TODO Auto-generated method stub

	 }

	 @Override
	 public void onStopTrackingTouch(SeekBar arg0) {
	  // TODO Auto-generated method stub

	 }});
	 }
	}