package com.example.imageload;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.imageload.Constants.Extra;
import static com.example.imageload.Constants.IMAGES;
import com.nostra13.universalimageloader.utils.L;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends BaseActivity {
	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
		if (!testImageOnSdCard.exists()) {
			copyTestImageToSdCard(testImageOnSdCard);
		}
	}
	public void onImageGridClick(View view) {
		Intent intent = new Intent(this, ImageGridActivity.class);
		intent.putExtra(Extra.IMAGES, IMAGES);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		imageLoader.stop();
		super.onBackPressed();
	}
	
	private void copyTestImageToSdCard(final File testImageOnSdCard) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					InputStream is = getAssets().open(TEST_FILE_NAME);
					FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
					byte[] buffer = new byte[8192];
					int read;
					try {
						while ((read = is.read(buffer)) != -1) {
							fos.write(buffer, 0, read);
						}
					} finally {
						fos.flush();
						fos.close();
						is.close();
					}
				} catch (IOException e) {
					L.w("Can't copy test image onto SD card");
				}
			}
		}).start();
}
}
