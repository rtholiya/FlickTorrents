package com.ravi.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebServiceClient {
	
	public static final String imdbUrl = "http://imdbapi.org/?title=%s&type=json&plot=simple&episode=1&limit=5&yg=0&mt=none&lang=en-US&offset=&aka=simple&release=simple&business=0&tech=0";
	
	public static String getService(String movieName) throws ClientProtocolException, IOException
	{
		String result="";
		movieName = movieName.replace(" ", "+");
		String reqUrl = String.format(imdbUrl, movieName);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(reqUrl);
		HttpResponse response = client.execute(request);
		//Response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		while((line=rd.readLine())!=null)
		{
			result += line;
		}
		return result;
	}

}
