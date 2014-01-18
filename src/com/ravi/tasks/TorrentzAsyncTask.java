package com.ravi.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ravi.adaptors.MoviesAdaptor;
import com.ravi.adaptors.StringAdaptor;
import com.ravi.common.ConfigSettings;
import com.ravi.common.JsonUtils;
import com.ravi.common.Movie;
import com.ravi.common.MovieDetails;
import com.ravi.common.Utils;
import com.ravi.rest.WebServiceClient;
import com.ravi.torrentino.ConfigActivity;
import com.ravi.torrentino.MainActivity;
import com.ravi.torrentino.R;
import com.ravi.torrentz.TorrentzHtmlParser;
import com.ravi.views.MyListView;
import com.ravi.views.SimpleDynamics;


    public class TorrentzAsyncTask  extends AsyncTask<String, Void, MoviesAdaptor>{
    	
    	private static final String TAG = "TorrentAsyncTask";
    	private List<Movie> movs = new ArrayList<Movie>();
    	private LinearLayout pbLayout;
    	private File moviesFile;
    	File configFile;
    	String[] ratings;
    	String[] genres;
    	List<Integer> years;
    	
    	Activity activity;
    	
    	public TorrentzAsyncTask(Activity activity) {
    		
    		this.activity = activity;
    		this.pbLayout =  (LinearLayout)activity.findViewById(R.id.pbLayout);
    		moviesFile = new File(activity.getApplicationContext().getFilesDir().getPath().toString()+MainActivity.MOVIES_FILE);
    	}

    	@Override
    	protected void onPreExecute() {
//    		setProgressBarIndeterminate(true);
//    		if(!moviesFile.exists())
//    		{
//    		activity.runOnUiThread(new Runnable() {
//				
//				@Override
//				public void run() {
//					Toast toast = Toast.makeText(activity, "Refreshing the results..",Toast.LENGTH_SHORT);
//		    	       toast.show();
//				}
//			});
    			pbLayout.setVisibility(View.VISIBLE);
//    		}
    	}
    	
    	@Override
    	protected void onPostExecute(MoviesAdaptor adaptor) {
//    		setProgressBarIndeterminate(false);
    		pbLayout.setVisibility(View.GONE);
    	}
    	
    	@Override
    	protected MoviesAdaptor doInBackground(String... params) {
    		Map<String, Movie> linkz = new HashMap<String, Movie>();
    		 try {
    			    linkz = TorrentzHtmlParser.parseHtmlForTorrentLinkz(params[0]);
    				for(String s : linkz.keySet())
    			        {
    					    String ratingData = WebServiceClient.getService(s);
    					    JSONArray arr = JsonUtils.parseJson(ratingData);
    					    Movie movie = linkz.get(s);
    					    MovieDetails mDetails = new MovieDetails();
    					    if(null != arr)
    					    {
    					    	for(int i=0;i<arr.length();i++)
    					    	{
    					    		JSONObject obj = arr.getJSONObject(i);
    					    		String movieTitle = obj.getString("title");
    					    		
    					    		if(!Utils.isBlankString(movieTitle) && movieTitle.equalsIgnoreCase(movie.getMovie()))
    					    		{
    					    			Iterator<String> itr = obj.keys();
    	    						    while(itr.hasNext())
    	    						    {
    	    						    	String key = itr.next();
    	    						    	String val = obj.getString(key);
    	    						    	if(key.equalsIgnoreCase("genres"))
    	    						    	{
    	    						    		mDetails.setGenres(val);
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("title"))
    	    						    	{
    	    						    	    mDetails.setTitile(val);	
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("imdb_url"))
    	    						    	{
    	    						    	   mDetails.setImdbUrl(val);	
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("imdb_id"))
    	    						    	{
    	    						    		mDetails.setImdbId(val);
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("poster"))
    	    						    	{
    	    						    		mDetails.setPoster(val);
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("rating"))
    	    						    	{
    	    						    		mDetails.setRatings(Float.valueOf(val));
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("year"))
    	    						    	{
    	    						    		mDetails.setYear(val);
    	    						    	}
    	    						    	else if(key.equalsIgnoreCase("plot_simple"))
    	    						    	{
    	    						    		mDetails.setPlot(val);
    	    						    	}
    	    						    	
    	    						    	Log.d(TAG, "Key:"+key+" Value:"+val);
    	    						    }
    					    		}
    					    	}
    						    
    						    //Take all movies having ratings more that configured ratings.
    					    	configFile = new File(activity.getApplicationContext().getFilesDir().getPath().toString()+ConfigActivity.CONFIG_FILE);
								ConfigSettings settings = Utils.getConfigSettings(configFile);
								if(null != settings)
								{
									ratings = activity.getResources().getStringArray(R.array.ratings_arrays);
									String chosenRating = ratings[settings.getRatings()];
									genres = activity.getResources().getStringArray(R.array.genre_arrays);
									String chosenGenre = genres[settings.getGenere()];
									int i = 0;
									int yr = Calendar.getInstance().get(Calendar.YEAR);
									List<Integer> years = new ArrayList<Integer>();
									while(i < 5)
									{
										years.add(yr - i);
										i++;
									}
									int chosenYear = years.get(settings.getYear());
									Log.i(TAG,"Configure values:"+settings.toString());
	    						    if(mDetails.getRatings() > Integer.valueOf(chosenRating) && mDetails.getYear().equalsIgnoreCase(String.valueOf(chosenYear)) && mDetails.getGenres().indexOf(chosenGenre) != -1)
	    						    {
	    						    	movs.add(movie);
	    						    	movie.setMovieDetails(mDetails);
	    						    }
								}
								else
								{
									Log.e(TAG,"From "+this.getClass()+" Settings is null.");
								}
								
    					    }
    					    
    			        	
    			        }
    			} 
    	        catch (JSONException e) {
    	        	Log.e(TAG, "Msg:"+e.getMessage());
    			} catch (ClientProtocolException e) {
    				Log.e(TAG, "Msg:"+e.getMessage());
    			} catch (IOException e) {
    				Log.e(TAG, "Msg:"+e.getMessage());
    			}
    	        
    		
    	        activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						MyListView list = (MyListView)activity.findViewById(R.id.list3d);
						 if(!(movs.size() > 0))
			    		 {
			    			 final StringAdaptor blankAdaptor = new StringAdaptor(activity);
			    			 list.setAdapter(blankAdaptor);
			    			 Toast toast = Toast.makeText(activity, "No torrents found..", Toast.LENGTH_SHORT);
			    			 toast.show();
			    		 }
						 else
						 {
							   final MoviesAdaptor mAdaptor = new MoviesAdaptor(movs,activity);
							   list.setAdapter(mAdaptor);
						 }
						 list.setDynamics(new SimpleDynamics(0.9f, 0.9f));
						  
					}
				});
    	        
    	        
    	        try{
    	        	//Write data to file
    	        	if(moviesFile.exists())
    	        	{
    	        		moviesFile.delete();
    	        	}
    	        	if(moviesFile.createNewFile())
    	        	{
    	        		FileOutputStream fos = new FileOutputStream(moviesFile);
        			    ObjectOutputStream oos = new ObjectOutputStream(fos);
        			    oos.writeObject(movs);
    	        	}
    	        	
    	        }catch(IOException e)
    	        {
    	        	Log.e(TAG,"Msg:"+e.getMessage());
    	        }
                return null;
    	}
    
	 }

