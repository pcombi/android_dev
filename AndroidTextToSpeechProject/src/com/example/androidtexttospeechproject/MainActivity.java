package com.example.androidtexttospeechproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener {
	private int MY_DATA_CHECK_CODE = 0;

	private EditText inputText;
	private Button speakButton;
	private TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		inputText = (EditText) findViewById(R.id.input_text);
		speakButton = (Button) findViewById(R.id.speak_button);
		tts = new TextToSpeech(this, this);
		speakButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String text = inputText.getText().toString();
				if (text != null && text.length() > 0) {
					Toast.makeText(MainActivity.this, "Saying " + text, Toast.LENGTH_LONG).show();
					
					tts.speak(text, TextToSpeech.QUEUE_ADD, null);
				}
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				
                // success, create the TTS instance

                tts = new TextToSpeech(this, this);

            }

            else {

                // missing data, install it

                Intent installIntent = new Intent();

                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);

                startActivity(installIntent);

            }

		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			
            Toast.makeText(MainActivity.this,

                    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();

        }

        else if (status == TextToSpeech.ERROR) {

            Toast.makeText(MainActivity.this,

                    "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();

        }

	}

}
