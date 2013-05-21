package com.hrh.screentorch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;


import com.hrh.shake.R;
import com.hrh.shake.SampleSeekBarDialogPreferenceSettings;

public class ScreenTorch extends Activity {
	 private Builder builder;
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.shake, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case R.id.about:
        	aboutAppDialog();
        return true;
        case R.id.developer:
        	aboutDeveloperDialog();
        return true;
        case R.id.help:
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
	private void handlePreferenceClick() {

		Intent preferenceIntent = new Intent(ScreenTorch.this,
				SampleSeekBarDialogPreferenceSettings.class);
		startActivityForResult(preferenceIntent, 0);
	}
	}