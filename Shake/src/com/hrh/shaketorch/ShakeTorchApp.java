package com.hrh.shaketorch;

import android.app.Application;
import com.bugsense.trace.BugSenseHandler;



public class ShakeTorchApp extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//958a991a
		//BugSenseHandler.initAndStartSession(ShakeTorchApp.this, "958a991a");
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		//BugSenseHandler.closeSession(ShakeTorchApp.this);
		super.onTerminate();
	}

}
