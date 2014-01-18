package com.ravi.listeners;

import com.ravi.common.Constants;
import com.ravi.common.Movie;
import com.ravi.common.MovieDetails;
import com.ravi.common.Utils;
import com.ravi.torrentino.MovieDetailsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ListViewClickListener implements OnClickListener {
	
	Movie movie;
	Activity activity;
	private static final String TAG = "ListViewClickListner";
	
	
	public ListViewClickListener(Movie movie,Activity activity) {
		this.movie = movie;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		
		Toast t = Toast.makeText(activity.getApplicationContext(), "Ratings:"+movie.getMovieDetails().getRatings(), Toast.LENGTH_LONG);
		t.show();
		Bundle dataBundle = new Bundle();
		try{
			dataBundle.putByteArray(Constants.movie_byte_array, Utils.object2Bytes(movie));
		}catch (Exception e) {
			Log.e(TAG, "Msg:"+e.getMessage());
		}
		
		Intent movieDetails = new Intent(activity,MovieDetailsActivity.class);
		movieDetails.putExtras(dataBundle);
        activity.startActivity(movieDetails);
	}

}
