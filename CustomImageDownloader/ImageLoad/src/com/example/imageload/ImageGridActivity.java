/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.imageload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imageload.Constants.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageGridActivity extends AbsListViewBaseActivity {

	String[] imageUrls;
	Context contxt;
	DisplayImageOptions options;
	Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_grid);

		Bundle bundle = getIntent().getExtras();
		imageUrls = bundle.getStringArray(Extra.IMAGES);

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.cacheInMemory()
			.cacheOnDisc()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();

		listView = (GridView) findViewById(R.id.gridview);
		((GridView) listView).setAdapter(new ImageAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
	}

	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public  class ViewHolder
	    {
	        public ImageView imgViewFlag;
	        public TextView txtViewTitle;
	    }
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = null;
			ViewHolder view;
	       // LayoutInflater inflator = activity.getLayoutInflater();
	 
	       if(convertView==null)
	        {
	            view = new ViewHolder();
	            convertView = getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
	 
	            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView);
	            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.image);
	          //  imageView = (ImageView) getLayoutInflater().inflate(R.id.image, parent, false);
	            convertView.setTag(view);
	        }
	        else
	        {
	            view = (ViewHolder) convertView.getTag();
	        }
	 
	        view.txtViewTitle.setText("new text");
	        imageLoader.displayImage(imageUrls[position], view.imgViewFlag, options);
	 
	        return convertView;
	/*	if (convertView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
			}
			else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;*/
			
			
			
		}
	}
}