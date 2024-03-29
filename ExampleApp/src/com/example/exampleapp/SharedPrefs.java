package com.example.exampleapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

	Button save;
	Button load;
	EditText etInput;
	TextView tvResult;
	SharedPreferences someData;
	static String filename = "MySharedString";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		initialize();
		someData = getSharedPreferences(filename, 0); // initialize shared preferences in file with name filename
	}

	private void initialize() {
		save = (Button) findViewById(R.id.bSave);
		load = (Button) findViewById(R.id.bLoad);
		tvResult = (TextView) findViewById(R.id.tvResult);
		etInput = (EditText) findViewById(R.id.etInput);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String stringData = etInput.getText().toString();
			// create new editor for editing shared preferences
			SharedPreferences.Editor editor = someData.edit();
			// we can put any data type in editor 
			editor.putString("sharedString", stringData);
			// save changes to shared preferences.
			editor.commit();
			break;
		case R.id.bLoad:
			someData = getSharedPreferences(filename, 0);
			String savedString = someData.getString("sharedString", "default");
			tvResult.setText(savedString);
			break;
		}

	}
}
