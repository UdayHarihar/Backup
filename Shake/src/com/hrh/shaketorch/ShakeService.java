package com.hrh.shaketorch;

import java.util.logging.Logger;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.app.KeyguardManager.KeyguardLock;
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
		
	    //Create camera object to access flahslight
		private Camera camera;
		private ShakeListener mShaker;
		private KeyguardLock lock;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	   @Override
	    public void onStart(Intent intent, int startId) {
	        Toast.makeText(this, "Shake Service Started", Toast.LENGTH_LONG).show();
	        Log.d("Shake Service", "onStart");
	        
	        Context context = this;
			PackageManager pm = context.getPackageManager();
			
			final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			
			camera = Camera.open();
	    	final Parameters p = camera.getParameters();
	    	
		    KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE); 
			lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
					
			mShaker = new ShakeListener(this);
     	    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener ()
     	    {
     	      public void onShake()
     	      {
     	        vibe.vibrate(100);

     	    	//lock_screen();
             	//If Flag is set to true
     	        
             	  if (isFlashOn) 
             	  {
             		  Log.i("info", "torch is turned off!");

             		 Toast toast= Toast.makeText(getApplicationContext(),
             			    	"Torch is turned off!",Toast.LENGTH_SHORT);
             		 toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
             		 toast.show();

             		  //Set the flashmode to off                   
             		  p.setFlashMode(Parameters.FLASH_MODE_OFF);
             		  //Pass the parameter ti camera object
             		  camera.setParameters(p);   
             		  //Set flag to false               
             		  isFlashOn = false;
             		  //Set the button text to Torcn-ON
             		  // button.setText("Torch-ON");
             	  }
             	  //If Flag is set to false
             	  else 
             	  {
             		  Log.i("info", "torch is turned on!");
             		  
             		 Toast toast= Toast.makeText(getApplicationContext(),
           			    	"Torch is turned on!",Toast.LENGTH_SHORT);
             		  toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
             		  toast.show();
             		  
             		  //Set the flashmode to on
             		  p.setFlashMode(Parameters.FLASH_MODE_TORCH);
             		  //Pass the parameter ti camera object
             		  camera.setParameters(p);
             		  //Set flag to true
             		  isFlashOn = true;
             		  //Set the button text to Torcn-OFF
             		  // button.setText("Torch-OFF");
             	  }
     	    	 
     	      }
     	    });
			
	    }
	 
	    @Override
	    public void onDestroy() {
	        Toast.makeText(this, "Shake Service Stopped", Toast.LENGTH_LONG).show();
	        Log.d("Shake Service", "onDestroy");
	        if (camera != null) {
				  camera.release();
				  }
	       mShaker.pause();   
	        this.stopSelf();
	        
	    }

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// TODO Auto-generated method stub
			//return super.onStartCommand(intent, flags, startId);
			return START_STICKY;
		}

}
