package com.ravi.listeners;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class ProvidersOnClickListner implements OnClickListener{
	
	String providerLink;
	Activity activity;
	
	public ProvidersOnClickListner(String providerString,Activity activity) {
		this.providerLink = providerString;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(providerLink));
		activity.startActivity(browserIntent);
		
	}

}
