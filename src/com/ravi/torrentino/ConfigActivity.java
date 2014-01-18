package com.ravi.torrentino;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ravi.common.ConfigSettings;
import com.ravi.common.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ConfigActivity extends Activity implements OnClickListener{
	
	public static final String CONFIG_FILE = "config-file";
	private static final String TAG = "ConfingActivity";
	Spinner year;
	Spinner genre;
	Spinner ratings;
	Button save;
	Button cancel;
	File configFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_layout);
		int yr = Calendar.getInstance().get(Calendar.YEAR);
		year = (Spinner)findViewById(R.id.year_spinner);
		int i = 0;
		List<Integer> years = new ArrayList<Integer>();
		while(i < 5)
		{
			years.add(yr - i);
			i++;
		}
		ArrayAdapter<Integer> yrAdaptor = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,years.toArray(new Integer[0]));
		year.setAdapter(yrAdaptor);
		
		genre = (Spinner)findViewById(R.id.genere_spinner);
		ratings = (Spinner)findViewById(R.id.rating_spinner);
		save = (Button)findViewById(R.id.config_save);
		save.setOnClickListener(this);
		cancel = (Button)findViewById(R.id.config_cancel);
		cancel.setOnClickListener(this);
		
		
		configFile = new File(getApplicationContext().getFilesDir().getPath().toString()+CONFIG_FILE);
		if(configFile.exists())
		{
				ConfigSettings settings = Utils.getConfigSettings(configFile);
				if(null != settings)
				{
					genre.setSelection(Integer.valueOf(settings.getGenere()));
					year.setSelection(Integer.valueOf(settings.getYear()));
					ratings.setSelection(Integer.valueOf(settings.getRatings()));
				}
			
		}
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.config_save:
			if(configFile.exists())
			{
				configFile.delete();
			}
				Utils.setConfigSettings(configFile, genre.getSelectedItemPosition(), year.getSelectedItemPosition(), ratings.getSelectedItemPosition());
				setResult(1);
				finish();
			
			
			break;

		case R.id.config_cancel:
			finish();
			break;
		default:
			break;
		}
		
	}


}
