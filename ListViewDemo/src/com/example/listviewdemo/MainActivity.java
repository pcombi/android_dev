package com.example.listviewdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	String [] presidents = {
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4",
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4",
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4",
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4",
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4",
			"Test 1",
			"Test 2",
			"Test 3",
			"Test 4"
	}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, presidents));
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "clicked back", 100).show();
		//super.onBackPressed();
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
		Toast.makeText(this, "You have selected " + presidents[position], Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
