package com.softalien.englishgrammar;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class PresentSimple extends Activity {
	WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theoryview);
		webView = (WebView) findViewById(R.id.wview);
		webView.loadUrl("file:///android_asset/present_simple.html"); 
	}
}
