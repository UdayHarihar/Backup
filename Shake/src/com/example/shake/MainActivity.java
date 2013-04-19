package com.example.shake;

import java.util.logging.Logger;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity
{
	//Set boolean flag when torch is turned on/off
	private boolean isFlashOn = false;
	
		//Create camera object to access flahslight
	 private Camera camera;
	
	 private ShakeListener mShaker;
	 private KeyguardLock lock;
	 boolean gate=true;
	 private WakeLock screenWakeLock;
	 
	 
	
	  @Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    Context context = this;
		PackageManager pm = context.getPackageManager();
		
		final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		
		if(!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) 
		{
	    	Logger message;
	    	Log.e("err", "Device has no camera!");
	    	//Toast a message to let the user know that camera is not
	    	//installed in the device
	    	Toast.makeText(getApplicationContext(),
	    	"Your device doesn't have camera!",Toast.LENGTH_SHORT).show();
	    	//Return from the method, do nothing after this code block
	    	return;
	    }

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
        		  //Set the flashmode to on
        		  p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        		  //Pass the parameter ti camera object
        		  camera.setParameters(p);
        		  //Set flag to true
        		  isFlashOn = true;
        		  //Set the button text to Torcn-OFF
        		  // button.setText("Torch-OFF");
        	  }
	    	 
	   /*       if (gate == true)
	        {
	        	
	        	  gate=false;
	        }
	        else if(gate == false)
	        {
	        	 gate=true;
	        	//unlockScreen();
	        	if (camera != null) {
	        		camera.release();
	        		}
	        }*/
	      /*  new AlertDialog.Builder(MainActivity.this)
	          .setPositiveButton(android.R.string.ok, null)
	          .setMessage("Shooken!")
	          .show();*/
	        
	       // Log.i("acc","lock");
	       //lock.reenableKeyguard();
	        //lock.disableKeyguard();
	      }
	    });
	    
	   // camera = Camera.open();
	    
	  }
	  
	 private void unlockScreen() {
	       // Window window = this.getWindow();
	        WindowManager.LayoutParams layout = getWindow().getAttributes();
	        layout.screenBrightness = 1F;
	        getWindow().setAttributes(layout);
	       // window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
	       // window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
	       // window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
	        gate=true;
		  Log.i("shake","unlock");
	    }
	 
	 /* public void unlock_screen()
	  {
		/*  PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		  screenWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
		                                  "screenWakeLock");
		  screenWakeLock.acquire();
		  gate=true;
		  Log.i("shake","unlock");
	  }*/
	 
	  public void lock_screen()
	  {
		  Log.i("shake","lock");
		 	Window mywindow = getWindow();
	        WindowManager.LayoutParams lp = mywindow.getAttributes();
	        lp.screenBrightness = 0.1f;
	        mywindow.setAttributes(lp);
	        gate=false;
	        
	  }
	  

	  
	  @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		  if (camera != null) {
			  camera.release();
			  }
		super.onStop();
	}
	@Override
	  public void onResume()
	  {
	    mShaker.resume();
	 /*  if (screenWakeLock != null) {
	    	   if(screenWakeLock.isHeld())
	    	      screenWakeLock.release();
	    	   screenWakeLock = null;
	    	}*/
	    super.onResume();
	  }
	  
	  @Override
	  public void onPause()
	  {
	    mShaker.pause();
	    super.onPause();
	  }
	}
