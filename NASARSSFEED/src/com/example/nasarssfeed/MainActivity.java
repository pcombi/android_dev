package com.example.nasarssfeed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView title;
	ImageView image;
	TextView date;
	TextView description;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iniitalize();
		
		/*Integer a = 127;
		Integer b = 127;
		Integer c = 128;
		Integer d = 128;
		System.out.println(a==b);
		System.out.println(c==d);*/
		new LoadRSS().execute("test");
		
	}
	
	public void iniitalize() {
		image = (ImageView) findViewById(R.id.image);
		description = (TextView) findViewById(R.id.tvDescription);
		date = (TextView) findViewById(R.id.tvImageDate);
		title = (TextView) findViewById(R.id.tvImageTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public class LoadRSS extends AsyncTask<String, Integer, String> {
		IotdHandler handler;
		ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
			
		}

		protected String doInBackground(String... arg0) {
			handler = new IotdHandler();
			handler.processFeed();
			return null;
		}
		
		protected void onPostExecute(String result) {
			image.setImageBitmap(handler.getImage());
			title.setText(handler.getTitle());
			date.setText(handler.getDate());
			description.setText(handler.getDescription());
			dialog.dismiss();
		}
		
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(progress[0]);
		}
		
	}
	


}
