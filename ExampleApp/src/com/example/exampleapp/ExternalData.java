package com.example.exampleapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
		OnClickListener {

	private TextView canWrite, canRead;
	private String state;
	private boolean canW, canR;
	Spinner spinner;
	String[] paths = { "Music", "Pictures", "Downloads" };
	File path = null;
	EditText saveFile;
	Button confirm, save;
	File file = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);

		initialize();
	}

	private void initialize() {
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		// canWrite.setText("false");
		// canRead.setText("false");
		state = Environment.getExternalStorageState();
		confirm = (Button) findViewById(R.id.bConfirm);
		save = (Button) findViewById(R.id.bSaveFile);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		checkState();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, paths);

		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// read/write
			canWrite.setText("true");
			canRead.setText("true");
			canR = canW = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// only read
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = false;
			canR = false;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;

		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;

		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bSaveFile:
			String fileName = saveFile.getText().toString();
			file = new File(path, fileName + ".png");
			checkState();
			if (canW == canR == true) {
				path.mkdirs();
				try {
					InputStream is = getResources().openRawResource(R.drawable.ball);
					OutputStream os = new FileOutputStream(file);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					// Update files for the user to use.
					MediaScannerConnection.scanFile(ExternalData.this, new String[] {file.toString()},
							null, new MediaScannerConnection.OnScanCompletedListener() {
								
								@Override
								public void onScanCompleted(String path, Uri uri) {
									// TODO Auto-generated method stub
									Toast t = Toast.makeText(ExternalData.this, "Scan complete", Toast.LENGTH_LONG);
									t.show();
								}
							});
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
		case R.id.bConfirm:
			save.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}

	}
}
