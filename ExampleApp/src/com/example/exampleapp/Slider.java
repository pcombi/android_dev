package com.example.exampleapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener {
	private Button handle1;
	private Button handle2;
	private Button handle3;
	private Button handle4;
	private Button handle;
	private CheckBox checkBox;
	private SlidingDrawer sd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		handle1 = (Button) findViewById(R.id.handle1);
		handle2 = (Button) findViewById(R.id.handle2);
		handle3 = (Button) findViewById(R.id.handle3);
		handle4 = (Button) findViewById(R.id.handle4);
		handle = (Button) findViewById(R.id.handle);
		checkBox = (CheckBox) findViewById(R.id.cbSlidable);
		sd = (SlidingDrawer) findViewById(R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
		handle.setOnClickListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
		checkBox.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.handle1:
				sd.open();
			break;
		case R.id.handle2:
					
			break;
		case R.id.handle3:
			sd.toggle();
			break;
		case R.id.handle4:
			sd.close();
		break;
		default:
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg0.isChecked()) {
			sd.lock();
		} else {
			sd.unlock();
		}
	}

	@Override
	public void onDrawerOpened() {
		MediaPlayer mp = MediaPlayer.create(Slider.this, R.raw.explosion);
		mp.start();
	}
	
}
