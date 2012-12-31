package com.example.exampleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class Graphics extends Activity {
	
	MyBringBack ourView;
	WakeLock wL;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "whatever");
		super.onCreate(savedInstanceState);
		wL.acquire();
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wL.acquire();
	}
	
}
