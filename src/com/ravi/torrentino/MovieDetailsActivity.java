package com.ravi.torrentino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ravi.common.Constants;
import com.ravi.common.Movie;
import com.ravi.common.Utils;
import com.ravi.listeners.ProvidersOnClickListner;
import com.ravi.tasks.BitMapTask;
import com.ravi.torrentz.TorrentzHtmlParser;

public class MovieDetailsActivity extends Activity{
	
	Movie movie;
	private static final String TAG= "MovieDetailsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle dataBundle = getIntent().getExtras();
//		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		try{
			movie = (Movie)Utils.bytes2Object(dataBundle.getByteArray(Constants.movie_byte_array));
		}catch(Exception e)
		{
			Log.e(TAG, "Msg:"+e.getMessage());
		}
		setContentView(R.layout.movie_details);
		
		TextView movieName = (TextView) findViewById(R.id.movie_title);
		movieName.setText(movie.getMovieDetails().getTitile());
		ImageView img = (ImageView)findViewById(R.id.movie_poster);
		new BitMapTask(img).execute(movie.getMovieDetails().getPoster());
		TextView moviePlot = (TextView) findViewById(R.id.movie_plot);
		moviePlot.setText(movie.getMovieDetails().getPlot());
		RatingBar ratingsBar = (RatingBar)findViewById(R.id.movie_ratings);
		
		ratingsBar.setRating(Utils.getOutOf5(movie.getMovieDetails().getRatings()));
		ratingsBar.setEnabled(false);
		new ProvidersTask(this).execute(movie);
		
		//get the links
		
//		ListView listOfLinks = (ListView)findViewById(R.id.movie_torrent_links);
//		LinkAdapter dataAdapter = new LinkAdapter(movie.getLinks());
//		listOfLinks.setAdapter(dataAdapter);
	}
	
	private class ProvidersTask extends AsyncTask<Movie, Void, String>{
		
		Activity activity;
		
        public ProvidersTask(Activity activity) {
        	this.activity = activity;
		}
        
        @Override
        protected void onPreExecute() {
//        	setProgressBarIndeterminate(true);
        }
        
        @Override
        protected void onPostExecute(String result) {
//        	setProgressBarIndeterminate(false);
        }
        
		@Override
		protected String doInBackground(Movie... params) {
			try {
				Map<String, String> providers = TorrentzHtmlParser.getTorrentProviderLink(params[0]);
				final ListView proviList = (ListView)findViewById(R.id.provi_list);
				final LinkAdapter links = new LinkAdapter(providers.keySet(),providers,activity);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						proviList.setAdapter(links);					
					}
				});
			} catch (IOException e) {
				Log.e("MovieDetailsActivity", "MSG:"+e.getMessage());
			}
			return null;
		}
		
	}
	
	private class LinkAdapter extends BaseAdapter{

    	Activity activity;
        List<String> providersString = new ArrayList<String>();
        Map<String, String> providers = new HashMap<String, String>();

        public LinkAdapter() {
		}
        
        public LinkAdapter(Set<String> providersStrs,Map<String, String> providers,Activity activity) {
        	if(null != providersStrs)
        		this.providersString = Arrays.asList(providersStrs.toArray(new String[0]));
        	if(null != providers)
        		this.providers = providers;
        	
        	this.activity = activity;
		}
    	
    	@Override
    	public int getCount() {
    		return providersString.size();
    	}

    	@Override
    	public String getItem(int arg0) {
    		return providersString.get(arg0);
    	}

    	@Override
    	public long getItemId(int arg0) {
    		return arg0;
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View view = convertView;
    		ImageView img;
    		TextView name;
    		String providerName = getItem(position);
    		
    		if(null == view)
    		{
    			LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			view = vi.inflate(R.layout.provideres_list, parent,false);
    		}
    		//Set the image.
    		img = (ImageView)view.findViewById(R.id.provider_poster);
    		//new BitMapTask(img).execute(R.drawable.ic_launcher);
    		//Set Name
    		img.setImageResource(Constants.SUPPORTED_SITES_IMG.get(providerName));
    		img.setOnClickListener(new ProvidersOnClickListner(providers.get(providerName), activity));
    		name = (TextView)view.findViewById(R.id.provider_link);
    		name.setText(providerName);
    		name.setOnClickListener(new ProvidersOnClickListner(providers.get(providerName), activity));
    		//Set description
//    		desc = (TextView)view.findViewById(R.id.desc);
//    		desc.setText(movie.getDesc());
//    		List<View> touchebles = view.getTouchables();
//    		for(View v : touchebles)
//    		{
//    			//set onclick listner
//    			v.setOnClickListener(new ProvidersOnClickListner(providers.get(providerName), activity));
//    		}
    		return view;
    	}

	}

}
