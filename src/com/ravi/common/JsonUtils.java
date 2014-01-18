package com.ravi.common;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class JsonUtils {
	
	private static final String TAG = "JsonUtils";
	
	public static JSONArray parseJson(String json)
	{
		try {
			return new JSONArray(json);
		} catch (JSONException e) {
			Log.i(TAG,e.getMessage());
		}
		return null;
	}

}
