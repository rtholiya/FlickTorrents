package com.ravi.torrentino;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.ravi.common.ConfigSettings;
import com.ravi.common.Utils;
import com.ravi.tasks.MoviesFromFileTask;
import com.ravi.tasks.TorrentzAsyncTask;
import com.ravi.views.MyListView;

public class MainActivity extends Activity {
	
	public static final String TAG = "MainActivity";
	
	private MyListView list;
	
	/** Id for the toggle rotation menu item */
    private static final int TOGGLE_ROTATION_MENU_ITEM = 0;

    /** Id for the toggle lighting menu item */
    private static final int TOGGLE_LIGHTING_MENU_ITEM = 1;
    
    public static final String MOVIES_FILE = "movies-file";
    
    public static String torrentSearchString = "http://torrentz.in/verified?f=dvdrip";
    
    File configFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        list = (MyListView)findViewById(R.id.list3d);
//        SwipeDetector sDetector = new SwipeDetector(this);
//        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout);
//        mainLayout.setOnTouchListener(sDetector);
			if(Utils.isOnline(this))
			{
				new MoviesFromFileTask(this).execute(getApplicationContext().getFilesDir().getPath().toString()+MainActivity.MOVIES_FILE);
			   
				MainActivity.torrentSearchString = torrentLink();
				
			    new TorrentzAsyncTask(this).execute(MainActivity.torrentSearchString);
			}
			else
			{
				Toast toast = Toast.makeText(getApplicationContext(), "Not connected.Application requires active network connection.", 30);
				toast.show();
				return;
			}
    }
    
    public String torrentLink()
    {
    	   configFile = new File(getApplicationContext().getFilesDir().getPath().toString()+ConfigActivity.CONFIG_FILE);
		   StringBuffer torrentSearchBuffer = new StringBuffer("http://torrentz.in/verified?f=dvdrip");
			if(configFile.exists())
			{
					ConfigSettings settings = Utils.getConfigSettings(configFile);
					if(null != settings)
					{
						String[] genres = getResources().getStringArray(R.array.genre_arrays);
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
						if(!Utils.isBlankString(chosenGenre))
						{
							torrentSearchBuffer.append("+"+chosenGenre);
						}
						if(chosenYear > 0)
						{
							torrentSearchBuffer.append("+"+chosenYear);
						}
					}
				
			}
			
		   return torrentSearchBuffer.toString();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case TOGGLE_ROTATION_MENU_ITEM:
            	if(null != list)
            	{
                list.enableRotation(!list.isRotationEnabled());
                return true;
            	}
            	else
            		return false;

//            case TOGGLE_LIGHTING_MENU_ITEM:
//                list.enableLight(!list.isLightEnabled());
//                return true;
            case R.id.action_settings:
            	Intent configIntent = new Intent(this,ConfigActivity.class);
            	startActivityForResult(configIntent,1);
            	return true;
            case R.id.action_refresh:
            	this.refreshActivity();
            	return true;

            default:
                return false;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(resultCode == 1)
    	{
    		refreshActivity();
    	}
    }
    
    private void refreshActivity()
    {
    	 Toast toast = Toast.makeText(this, "Refreshing..",Toast.LENGTH_SHORT);
    	 toast.show();
    	 MainActivity.torrentSearchString = torrentLink();
		 new TorrentzAsyncTask(this).execute(MainActivity.torrentSearchString);
    }
    
    @Override
    protected void onResume() {
		 super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(Menu.NONE, TOGGLE_ROTATION_MENU_ITEM, 0, "Toggle Rotation");
//        menu.add(Menu.NONE, TOGGLE_LIGHTING_MENU_ITEM, 1, "Toggle Lighting");
        return true;
    }

}
