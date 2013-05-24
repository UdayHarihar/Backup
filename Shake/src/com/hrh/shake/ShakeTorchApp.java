package com.hrh.shake;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;


import android.app.Application;
@ReportsCrashes(formKey = "5de572c5439fe3d4c4beb72ea8492c45")
public class ShakeTorchApp extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//958a991a
		//BugSenseHandler.initAndStartSession(ShakeTorchApp.this, "958a991a");
		ACRA.init(this);
		ACRA.getErrorReporter().setReportSender(new HockeySender());
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		//BugSenseHandler.closeSession(ShakeTorchApp.this);
		super.onTerminate();
	}

}
