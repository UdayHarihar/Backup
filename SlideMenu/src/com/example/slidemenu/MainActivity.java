package com.example.slidemenu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockFragmentActivity {
	private SlideMenu slidemenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Since we are using action bar sherlock we don't need SDK version
				// check
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// This enables us to use custom view for he action bar
				this.getSupportActionBar().setDisplayShowCustomEnabled(true);
				// This hides the default action bar
				this.getSupportActionBar().setDisplayShowTitleEnabled(false);
				// Custom layout for the action bar. Used if we custom typefaced title
				// for the actionbar.
				View customActionBar = getLayoutInflater().inflate(R.layout.action_bar_title, null);
				TextView title = (TextView) customActionBar.findViewById(R.id.actionbar_title);
				// Add title, it would be the application name
				//title.setText("Slide Menu");
				//Add the view to action bar
				getSupportActionBar().setCustomView(customActionBar);
				//your onCreate code
			    slidemenu = new SlideMenu(this);
			    slidemenu.checkEnabled();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
		//getSupportMenuInflater().inflate(R.menu.activity_podcast_details, menu);
		//return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			//NavUtils.navigateUpFromSameTask(this);
			Log.d("home","home selected");
			slidemenu.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	//	return super.onOptionsItemSelected(item);
	}



}
