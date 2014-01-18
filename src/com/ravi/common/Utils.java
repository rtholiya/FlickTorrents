package com.ravi.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
	
	public static boolean isBlankString(String str)
	{
		if(null != str && !"".equalsIgnoreCase(str))
		{
			return false;
		}
		return true;
	}
	
	public static String trim(String str)
	{
		return str.trim();
	}
	
	public static boolean isOnline(Activity activity) {
	    ConnectivityManager cm =
	        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	static public float getOutOf5(float rating)
	{
		float outOf5 = (rating/10)*5;
		return outOf5;
	}
	
	public static synchronized ConfigSettings getConfigSettings(File configFile)
	{
		FileInputStream ins;
		ConfigSettings settings = new ConfigSettings(0, 2013, 0);
		try {
			ins = new FileInputStream(configFile);
			ObjectInputStream obs = new ObjectInputStream(ins);
			settings = (ConfigSettings)obs.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return settings;
	}
	
	public static synchronized void setConfigSettings(File configFile,int genre,int year,int rating) 
	{
		
		try {
			configFile.createNewFile();
			FileOutputStream os;
			os = new FileOutputStream(configFile);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ConfigSettings settings = new ConfigSettings(genre,year,rating);
			oos.writeObject(settings);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Converting byte arrays to objects
	 */
	static public Object bytes2Object( byte raw[] )
	        throws IOException, ClassNotFoundException {
	      ByteArrayInputStream bais = new ByteArrayInputStream( raw );
	      ObjectInputStream ois = new ObjectInputStream( bais );
	      Object o = ois.readObject();
	      return o;
	    }

	/**
	 * Converting objects to byte arrays
	 */
	static public byte[] object2Bytes( Object o ) throws IOException {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream( baos );
	      oos.writeObject( o );
	      return baos.toByteArray();
	    }
}
