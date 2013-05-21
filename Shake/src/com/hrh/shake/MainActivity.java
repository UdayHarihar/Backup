package com.hrh.shake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.hrh.screentorch.ScreenTorch;


public class MainActivity extends Activity
{
	private  Context context = this;
	private Button screentorch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		screentorch= (Button) findViewById(R.id.bt_scrrentorch);
		screentorch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,ScreenTorch.class);
	        	MainActivity.this.startActivity(intent);
			}
		});
		
		
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
        	Toast toast=Toast.makeText(context,"Sorry..!!\nYour mobile doesn't have Flash Light...!!," +
        			"Screen torch enabled.",Toast.LENGTH_LONG);
        	toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
        	toast.show();
        	}
        	Intent intent=new Intent(MainActivity.this,ScreenTorch.class);
        	MainActivity.this.startActivity(intent);  	
        }
	}



}
