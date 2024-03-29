package com.example.mp3playerdemo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

class Mp3Filter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return (name.endsWith(".mp3"));
	}
}

public class MainActivity extends ListActivity {
	private static final String SD_PATH = new String("/sdcard/Download/");
	private List<String> songs = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updatePlayList();
		
		Button stopBtn = (Button)findViewById(R.id.stopBtn);
		stopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.stop();
			}
		});
	}
	
	private void updatePlayList() {
		File home = new File(SD_PATH);
		if (home.listFiles(new Mp3Filter()).length > 0) {
			for (File file: home.listFiles(new Mp3Filter())) {
				songs.add(file.getName());
			}
		}
		ArrayAdapter<String> songList = new ArrayAdapter<String>(this, R.layout.song_item, songs);
		setListAdapter(songList);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
		try { 
		mp.reset();
		mp.setDataSource(SD_PATH + songs.get(position));
		mp.prepare();
		mp.start();
		} catch (IOException e) {
			Log.v(getString(R.string.app_name), e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
