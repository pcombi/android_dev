package com.example.exampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {
	TabHost th;
	TextView showResults;
	long start,stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		Button newTab = (Button)findViewById(R.id.bAddTab);
		Button bStart = (Button)findViewById(R.id.bStartWatch);
		Button bStop = (Button)findViewById(R.id.bStopWatch);
		showResults = (TextView)findViewById(R.id.tvShowResults);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		newTab.setOnClickListener(this);
		th.setup();
		TabSpec tspecs = th.newTabSpec("tag1");
		tspecs.setContent(R.id.tab1);
		tspecs.setIndicator("StopWatch");
		th.addTab(tspecs);
		tspecs = th.newTabSpec("tag2");
		tspecs.setContent(R.id.tab2);
		tspecs.setIndicator("Tab2");
		th.addTab(tspecs);
		tspecs = th.newTabSpec("tag3");
		tspecs.setContent(R.id.tab3);
		tspecs.setIndicator("Tab3");
		th.addTab(tspecs);
		start = 0;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bAddTab:
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new Tab");
					return text;
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			break;
		case R.id.bStartWatch:
			start = System.currentTimeMillis();
			showResults.setText("started");
			break;
		case R.id.bStopWatch:
			stop = System.currentTimeMillis();
			showResults.setText(stop + " - stopped");
			
			if (start != 0) {
				long result = stop - start;
				int millis = (int)result;
				int seconds = (int)result/ 1000;
				int minutes = seconds/60;
				millis = millis % 100;
				seconds = seconds % 60;
				showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
			}
			break;
		}
	}

}
