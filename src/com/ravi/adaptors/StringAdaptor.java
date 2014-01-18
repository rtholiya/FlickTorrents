package com.ravi.adaptors;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ravi.torrentino.R;

public class StringAdaptor extends BaseAdapter{
	
	List<String> blank = new ArrayList<String>();
	Activity activity;
	
	public StringAdaptor(Activity activity) {
		this.activity = activity;
		blank.add("No movies found");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return blank.size();
	}

	@Override
	public String getItem(int arg0) {
		// TODO Auto-generated method stub
		return blank.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	View view = convertView;
	TextView name;
	String bl = getItem(position);
	
	if(null == view)
	{
		LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = vi.inflate(R.layout.list_item, parent,false);
	}
	
	//Set Name
	name = (TextView)view.findViewById(R.id.name);
	name.setText(bl);
	return view;
	}

}
