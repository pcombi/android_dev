package com.example.exampleapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		sqlUpdate = (Button) findViewById(R.id.bUpdateSQL);
		sqlView = (Button) findViewById(R.id.bView);
		sqlName = (EditText) findViewById(R.id.etName);
		sqlHotness = (EditText) findViewById(R.id.etHotness);
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		
		sqlRow = (EditText) findViewById(R.id.etRowId);
		sqlModify = (Button) findViewById(R.id.bEditEntry);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bDeleteEntry);
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bUpdateSQL:
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();
				
				HotOrNot entry = new HotOrNot(SQLiteExample.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
			} catch (Exception e) {
				e.printStackTrace();
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bView:
			Intent i = new Intent("android.intent.action.SQLVIEW");
			startActivity(i);
			break;
		case R.id.bGetInfo:
			String str = sqlRow.getText().toString();
			long l = Long.parseLong(str);
			HotOrNot hon = new HotOrNot(this);
			try {
				hon.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String returnedName = hon.getName(l);
			String returnedHotness = hon.getHotness(l);
			hon.close();
			sqlName.setText(returnedName);
			sqlHotness.setText(returnedHotness);
			break;
		case R.id.bEditEntry:
			String sRow = sqlRow.getText().toString();
			long lRow = Long.parseLong(sRow);
			String newName = sqlName.getText().toString();
			String newHotness = sqlHotness.getText().toString();
			
			HotOrNot ex = new HotOrNot(this);
			ex.open();
			ex.updateEntry(lRow, newName, newHotness);
			ex.close();
			
			break;
		case R.id.bDeleteEntry:
			String dRow = sqlRow.getText().toString();
			long rowId = Long.parseLong(dRow);
			
			HotOrNot delHon = new HotOrNot(this);
			delHon.open();
			delHon.deleteEntry(rowId);
			delHon.close();
			break;
		}
	}

}
