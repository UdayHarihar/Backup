package com.hrh.shake;

import android.app.Activity;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

public class ScreenReceiver extends BroadcastReceiver {
	  // thanks Jason
   public static boolean wasScreenOn = true;
    ShakeActivity obj ;
 
    @Override
    public void onReceive(Context context, Intent intent) {
    /*	obj=new ShakeActivity();
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
        	Log.d("screen","screen is off");
        	Toast.makeText(context, "screen is off", Toast.LENGTH_LONG).show();
        	
        	//obj.serviceoff();
        	//obj.serviceoff();
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
        	Log.d("screen","screen is on");
        	Toast.makeText(context, "screen is on", Toast.LENGTH_LONG).show();
        	
        	//obj.serviceon();
        	//obj.serviceon();
            wasScreenOn = true;
        }
        Intent i = new Intent(context, ShakeService.class);
        i.putExtra("screen_state", wasScreenOn);
        context.startService(i);*/
    }
    
    public class TorchONOff extends Activity
    {
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
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);	
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
		}
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
		}
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
		}
		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}
		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			if (camera != null) {
				  camera.release();
				  }
	    // mShaker.pause();
		}
		
	
		
    
    }
    

}
