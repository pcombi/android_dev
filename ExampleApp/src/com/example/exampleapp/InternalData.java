package com.example.exampleapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{
	
	Button save;
	Button load;
	EditText etInput;
	TextView tvResult;
	FileOutputStream fos;
	static String FILE_NAME = "InternalString";
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		initialize();
	}
	
	private void initialize() {
		save = (Button) findViewById(R.id.bSave);
		load = (Button) findViewById(R.id.bLoad);
		tvResult = (TextView) findViewById(R.id.tvResult);
		etInput = (EditText) findViewById(R.id.etInput);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		try {
			fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String data = etInput.getText().toString();
			// Saving data via file:
			/*File f = new File(FILE_NAME);
			try {
				fos = new FileOutputStream(f);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case R.id.bLoad:
			new LoadSomeStuff().execute(FILE_NAME);
			break;
		}

	}	
	// <String,Integer,String> - first parameter is type of data that we a re passing in (in our case it's FILE_NAME which is a String),
	// second parameter is showing progress. And third parameter is what type of data we return. We return collected String.
	public class LoadSomeStuff extends AsyncTask<String,Integer,String> {
		
		ProgressDialog dialog;
		
		protected void onPreExecute(String f) {
			
			
		}
		
		@Override
		protected void onPreExecute() {
			// example of setting up something
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			FileInputStream fis = null;
			String collected = null;
			
			for (int i=0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILE_NAME);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collected = new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					//tvResult.setText(collected);
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(progress[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			tvResult.setText(result);
		}
		
	}
}
