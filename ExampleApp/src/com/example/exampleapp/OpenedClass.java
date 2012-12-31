package com.example.exampleapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	TextView question, test;
	Button returnData;
	RadioGroup rgAnswers;
	String gotBread,setData;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name" , "Travis is ...");
		String values = getData.getString("list", "4");
		if (values.contentEquals("1")) {
			question.setText(et);
		} else if (values.contentEquals("2")) {
			
		} else if (values.contentEquals("2")) {
			
		}
		//Bundle gotBasket = getIntent().getExtras();
		//gotBread = gotBasket.getString("bread");
		//question.setText(gotBread);
	}
	
	private void initialize() {
		question = (TextView)findViewById(R.id.tvQuestion);
		test = (TextView)findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		returnData.setOnClickListener(this);
		rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
		rgAnswers.setOnCheckedChangeListener(this);
	}
	

	public void onClick(View v) {
		Intent person = new Intent();
		Bundle backPack = new Bundle();
		backPack.putString("answer", setData);
		person.putExtras(backPack);
		setResult(RESULT_OK, person);
		finish();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId) {
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
		case R.id.rSuperSexy:
			setData = "Definitely right!";
			break;
		case R.id.rBoth:
			setData = "Spot On!";
			break;
		}
		test.setText(setData);
	}
	
}
