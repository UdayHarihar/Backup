package com.hrh.shake;

import android.annotation.SuppressLint;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class ShakeService extends Service {
	
		//Set boolean flag when torch is turned on/off
		private boolean isFlashOn = false;
		private boolean release = false;
		//BroadcastReceiver mReceiver = new ScreenReceiver();
		
	    //Create camera object to access flahslight
		
		private ShakeListener mShaker;
		private KeyguardLock lock;
		private  Context context = this;
		private Camera camera;

	 @Override
     public void onCreate() {
         super.onCreate();
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
       PackageManager p = context.getPackageManager();	
		Log.i("call","in onrestart");
		// Toast.makeText(this, "Background mode activated", Toast.LENGTH_LONG).show();
	    //Shake detection in Activity
		mShaker = new ShakeListener(this);
	    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener ()
	    {
	      @SuppressLint("NewApi")
		  public void onShake()
	      {
	        vibe.vibrate(100);  
      	  if (isFlashOn) 
      	  {
      		// Log.i("info", "torch is turned off!");
      		 Toast toast= Toast.makeText(getApplicationContext(),
      			    	"Torch is turned off!",Toast.LENGTH_SHORT);
      		 toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
      		 toast.show();

      		Parameters p = camera.getParameters();
      		p.setFlashMode(Parameters.FLASH_MODE_OFF);
      		camera.setParameters(p);
      		camera.stopPreview();
      		camera.release();
      		camera = null;
      		isFlashOn = false;
      	  }
      	  //If Flag is set to false
      	  else 
      	  {
      		  Log.i("info", "torch is turned on!");
      		  
      		 Toast toast= Toast.makeText(getApplicationContext(),
    			    	"Torch is turned on!",Toast.LENGTH_SHORT);
      		  toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
      		  toast.show();

      	/*	Camera camera = Camera.open();
      		Parameters p = camera.getParameters();
      		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
      		camera.setParameters(p);*/
      		
      		try {
      	        	releaseCameraAndPreview();
      	        	camera = Camera.open();
      	        	//  qOpened = (camera != null);
      	    	} catch (Exception e) 
      	    	{
      	        Log.e(getString(R.string.app_name), "failed to open Camera");
      	        e.printStackTrace();
      	    	}
      		Parameters p = camera.getParameters();
      		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
      		camera.setParameters(p);
      		isFlashOn = true;
      		    
      	/*	Parameters p = camera.getParameters();
      		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
      		camera.setParameters(p);
      		camera.startPreview();
      		  **/

      	  }
      }
	      
	    });
     }
	 
	 private void releaseCameraAndPreview() {
		   // mPreview.setCamera(null);
		    if (camera != null) {
		    	camera.release();
		    	camera = null;
		    }
		}
	 

	   @Override
	    public void onStart(Intent intent, int startId) {
		   
		   boolean screenOn = intent.getBooleanExtra("screen_state", false);
           if (!screenOn) {
               // your code	   
        	   
        	   Log.d("service","turn off in background");
           } else {
               // your code
        	  
        	   Log.d("service","turn on in background");
        	  
           }
			
	    }
	  

		@Override
	    public void onDestroy() {
	        Toast.makeText(this, "Background mode Stopped", Toast.LENGTH_LONG).show();
	      //  Log.d("Shake Service", "onDestroy");
	       // unregisterReceiver(mReceiver);
	      if (camera != null) {
				  camera.release();
				  camera = null;
				  }
	       mShaker.pause();   
	       // this.stopSelf();    
	    }

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// TODO Auto-generated method stub
			//return super.onStartCommand(intent, flags, startId);
			return START_STICKY;
		}
		
		@Override
		public IBinder onBind(Intent arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		

}
