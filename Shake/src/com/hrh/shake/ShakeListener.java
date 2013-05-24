package com.hrh.shake;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class ShakeListener implements SensorEventListener
{
 
	 // private static final int FORCE_THRESHOLD = 9000;
	  private static final int TIME_THRESHOLD = 100;
	  private static final int SHAKE_TIMEOUT = 500;
	  private static final int SHAKE_DURATION = 1000;
	  private static final int SHAKE_COUNT = 3;
 
	  private SensorManager mSensorMgr;
	  private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
	  private long mLastTime;
	  private OnShakeListener mShakeListener;
	  private static Context mContext;
	  private int mShakeCount = 0;
	 // private int FORCE_THRESHOLD;
	  private long mLastShake;
	  private long mLastForce;
	  private int FORCE_THRESHOLD;
 
	  public interface OnShakeListener
	  {
		  
	    public void onShake();
	  }
 
	  public ShakeListener(Context context)
	  {
	    mContext = context;
	    resume();
	  }
 
	  public void setOnShakeListener(OnShakeListener listener)
	  {
	    mShakeListener = listener;
	  }
 
	  public void resume() {
	    mSensorMgr = (SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
	    if (mSensorMgr == null) {
	      throw new UnsupportedOperationException("Sensors not supported");
	    }
	    boolean supported = false;
	    try {
	    supported = mSensorMgr.registerListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	    } catch (Exception e) {Toast.makeText(mContext, "Shaking not supported", Toast.LENGTH_LONG).show();}
 
	    if ((!supported)&&(mSensorMgr != null)) mSensorMgr.unregisterListener(this);
	  }
 
	  public void pause() {
	    if (mSensorMgr != null) {
	      mSensorMgr.unregisterListener(this);
	      mSensorMgr = null;
	    }
	  }
 
	  public void onAccuracyChanged(Sensor sensor, int accuracy) { }
 
	  public void onSensorChanged(SensorEvent event)
	  {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
              return;
	    long now = System.currentTimeMillis();
 
	    if ((now - mLastForce) > SHAKE_TIMEOUT) {
	      mShakeCount = 0;
	    }
 
	    if ((now - mLastTime) > TIME_THRESHOLD) {
	      long diff = now - mLastTime;
	      float speed = Math.abs(event.values[SensorManager.DATA_X] + event.values[SensorManager.DATA_Y] + event.values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ) / diff * 10000;
	      SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
	      int force=settings.getInt("seekBar",40);
	      force=force*30;
	      FORCE_THRESHOLD = force;
	     // Log.d("value", "lister "+FORCE_THRESHOLD);
	     //Log.d("value", "lister "+value);
	      if (speed > FORCE_THRESHOLD) {
	        if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
	          mLastShake = now;
	          mShakeCount = 0;
	          if (mShakeListener != null) {
	            mShakeListener.onShake();
	          }
	        }
	        mLastForce = now;
	      }
	      mLastTime = now;
	      mLastX = event.values[SensorManager.DATA_X];
	      mLastY = event.values[SensorManager.DATA_Y];
	      mLastZ = event.values[SensorManager.DATA_Z];
	    }
	  }
}