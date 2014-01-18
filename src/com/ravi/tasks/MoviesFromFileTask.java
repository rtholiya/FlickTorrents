package com.ravi.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ravi.adaptors.MoviesAdaptor;
import com.ravi.common.Movie;
import com.ravi.torrentino.R;
import com.ravi.views.MyListView;
import com.ravi.views.SimpleDynamics;

public class MoviesFromFileTask extends AsyncTask<String, Void, MoviesAdaptor> {
	
	private List<Movie> movs = new ArrayList<Movie>();
	private Activity activity;
	private LinearLayout pbLayout;
	
	public static final String TAG = "MoviesFromFileTask";
	
	public MoviesFromFileTask(Activity activity) {
		this.activity = activity;
		this.pbLayout =  (LinearLayout)activity.findViewById(R.id.pbLayout);
	}
	
	@Override
	protected void onPreExecute() {
		pbLayout.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onPostExecute(MoviesAdaptor result) {
//		pbLayout.setVisibility(View.GONE);
	}

	@Override
	protected MoviesAdaptor doInBackground(String... params) {
		File movies = new File(params[0]);
		try{
			if(movies.exists())
			{
				FileInputStream fin = new FileInputStream(movies);
				ObjectInputStream ois = new ObjectInputStream(fin);
				ArrayList<Movie> readObject = (ArrayList<Movie>)ois.readObject();
				movs = readObject;
				
				if(null != movs && movs.size()>0)
				{
					
				
			    final MoviesAdaptor mAdaptor = new MoviesAdaptor(movs,activity);
		        activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						   Toast toast = Toast.makeText(activity, "Showing cached results.. Please wait while we refresh..", Toast.LENGTH_SHORT);
				           toast.show();
						   MyListView list = (MyListView)activity.findViewById(R.id.list3d);
						   list.setAdapter(mAdaptor);
						   list.setDynamics(new SimpleDynamics(0.9f, 0.9f));
					}
				});
		        
				}
				else
				{
					movies.delete();
					activity.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							pbLayout.setVisibility(View.VISIBLE);
						}
					});
				}
				activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						pbLayout.setVisibility(View.GONE);
					}
				});
			}
			else
			{
              activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						pbLayout.setVisibility(View.VISIBLE);
					}
				});
				Log.i(TAG,"No movies file found.");
			}
			
		}catch(Exception e)
		{
			Log.e(TAG, "Msg:"+e.getMessage());
			movies.delete();
		}

		return null;
	}

}
