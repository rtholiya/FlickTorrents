package com.ravi.adaptors;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ravi.common.Movie;
import com.ravi.listeners.ListViewClickListener;
import com.ravi.tasks.BitMapTask;
import com.ravi.torrentino.MainActivity;
import com.ravi.torrentino.R;

    public class MoviesAdaptor extends BaseAdapter {
    	
        List<Movie> movies = new ArrayList<Movie>();
        Activity activity;
        
        public MoviesAdaptor() {
		}
        
        public MoviesAdaptor(List<Movie> movs,Activity activity) {
        	this.movies = movs;
        	this.activity = activity;
		}
    	
    	@Override
    	public int getCount() {
    		return movies.size();
    	}

    	@Override
    	public Movie getItem(int arg0) {
    		return movies.get(arg0);
    	}

    	@Override
    	public long getItemId(int arg0) {
    		return arg0;
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
//    		TextView txt;
//    		
//    		if(convertView == null)
//    		{
//    			txt = (TextView)getLayoutInflater().inflate(R.layout.list_item, parent,false);
//    		}
//    		else
//    		{
//    			txt = (TextView)convertView;
//    		}
//    		final String movie = getItem(position).getmovie();
//    		txt.setText(movie);
//    		return txt;
    		
    		View view = convertView;
    		ImageView img;
    		TextView name;
    		Movie movie = getItem(position);
    		
    		if(null == view)
    		{
    			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			view = vi.inflate(R.layout.list_item, parent,false);
    		}
    		//Set the image.
    		img = (ImageView)view.findViewById(R.id.image);
    		new BitMapTask(img).execute(movie.getMovieDetails().getPoster());
    		//Set Name
    		name = (TextView)view.findViewById(R.id.name);
    		name.setText(movie.getMovie());
    		//Set description
//    		desc = (TextView)view.findViewById(R.id.desc);
//    		desc.setText(movie.getDesc());
    		List<View> touchebles = view.getTouchables();
    		for(View v : touchebles)
    		{
    			v.setOnClickListener(new ListViewClickListener(movie, activity));
    		}
    		return view;
    	}

    }
