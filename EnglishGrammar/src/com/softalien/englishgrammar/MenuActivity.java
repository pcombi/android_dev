package com.softalien.englishgrammar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener {

	Button bLessons;
	Button bTests;
	Button bExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		initialize();
	}

	public void initialize() {
		bLessons = (Button) findViewById(R.id.bLessons);
		bTests = (Button) findViewById(R.id.bTests);
		bExit = (Button) findViewById(R.id.bExit);
		bTests.setOnClickListener(this);
		bLessons.setOnClickListener(this);
		bExit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bLessons:

			break;
		case R.id.bTests:
			Intent i = new Intent("android.intent.action.TESTS");
			startActivity(i);
			break;
		case R.id.bExit:

			break;
		}
	}
}
