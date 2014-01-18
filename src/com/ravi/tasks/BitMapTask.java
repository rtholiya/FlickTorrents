package com.ravi.tasks;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class BitMapTask extends AsyncTask<String, Void, Bitmap>{
	
	ImageView bmImage;
	
	public BitMapTask(ImageView bmImage)
	{
		this.bmImage = bmImage;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		String url = params[0];
		Bitmap mIcon = null;
		try{
			InputStream in = new URL(url).openStream();
			mIcon = BitmapFactory.decodeStream(in);
		}catch(Exception e)
		{
			Log.e("BitMapTask","Message:"+e.getMessage());
			e.printStackTrace();
			
		}
		return mIcon;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		bmImage.setImageBitmap(result);
	}

}
