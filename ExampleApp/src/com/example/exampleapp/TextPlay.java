package com.example.exampleapp;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button chkCmd ;
	ToggleButton tgPassword ;
	EditText input ;
	TextView display ;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondary);
		
		initializeControls();
		
		tgPassword.setOnClickListener(this);
		chkCmd.setOnClickListener(this);
		
	}
	public void initializeControls() {
		chkCmd = (Button)findViewById(R.id.bResults);
		tgPassword = (ToggleButton) findViewById(R.id.toggle);
		input = (EditText) findViewById(R.id.etCommand);
		display = (TextView) findViewById(R.id.tvResults);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bResults:
			String content = input.getText().toString();
			display.setText(content);
			if (content.contentEquals("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (content.contentEquals("right")) {
				display.setGravity(Gravity.RIGHT);
			} else if (content.contentEquals("center")) {
				display.setGravity(Gravity.CENTER);
			} else if (content.contentEquals("blue")) {
				display.setTextColor(Color.BLUE);
			} else if (content.contentEquals("WTF")) {
				Random crazy = new Random();
				display.setText("WTF!!!");
				display.setTextSize(crazy.nextInt(75));
				display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));
				switch(crazy.nextInt(3)) {
				case 0:
					display.setGravity(Gravity.LEFT);
					break;
				case 1:
					display.setGravity(Gravity.CENTER);
					break;
				case 2:
					display.setGravity(Gravity.RIGHT);
					break;
				}
			} else {
				display.setText("invalid");
				display.setGravity(Gravity.CENTER);
			}
			break;
		case R.id.toggle:
			if (tgPassword.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}

}
