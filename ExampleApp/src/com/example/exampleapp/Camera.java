package com.example.exampleapp;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {
	Intent i;
	ImageButton ib;
	Button b;
	ImageView iv;
	Bitmap bmp;
	final static int cameraData = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}
	
	private void initialize() {
		iv = (ImageView) findViewById(R.id.ivReturnedPic);
		b = (Button) findViewById(R.id.bSetWall);
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bSetWall:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				e.printStackTrace();
			}
				break;
			case R.id.ibTakePic:
				i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(i, cameraData);
				break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}
	
	
}
