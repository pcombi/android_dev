package com.example.openingstandardprograms;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void doWeb(View view) {
		Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
		startActivity(i);
	}
	
	public void doCall(View view) {
		Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+12149319721"));
		startActivity(i);
	}
	public void doMap(View view) {
		Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+12149319721"));
		startActivity(i);
	}
}
