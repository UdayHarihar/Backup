package com.hrh.shake;

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
        	for(int i=0;i<2;i++)
        	{
        		Toast toast=Toast.makeText(context,"Shake to switch the torch ON / OFF,\n" + 
				"for more go to Menu -> Help.",Toast.LENGTH_LONG);
        		toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
        		toast.show();
        	}
        	Intent intent=new Intent(MainActivity.this,ShakeActivity.class);
        	MainActivity.this.startActivity(intent);
        	
        }
        else 
        {
        	Log.i("Does not support","flasg light");
        	for(int i=0;i<2;i++)
        	{
        	Toast toast=Toast.makeText(context,"Sorry..!!\nYour mobile doesn't have Flash Light...!!,",Toast.LENGTH_LONG);
        	toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
        	toast.show();
        	}
        	Intent intent=new Intent(MainActivity.this,ScreenTorch.class);
        	MainActivity.this.startActivity(intent);  
        	//Intent intent=new Intent(MainActivity.this,ShakeActivity.class);
        	//MainActivity.this.startActivity(intent);
        	//Intent intent=new Intent(MainActivity.this,ShakeService2.class);
        	//MainActivity.this.startService(intent);  
        }
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		onBackPressed();
		super.onRestart();
	}



}
