package com.example.exampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";
	
	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE_NAME = "people";
	private static final int DATABASE_VERSION = 1;
	
	private DBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	public HotOrNot (Context c) {
		ourContext = c;
	}
	
	public HotOrNot open() {
		ourHelper = new DBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_NAME + " TEXT NOT NULL, " + 
					KEY_HOTNESS + " TEXT NOT NULL);"		
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
			onCreate(db);
		}
		
		
	}

	public long createEntry(String name, String hotness) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDatabase.insert(DATABASE_TABLE_NAME, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE_NAME, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		//loop throug all rows  - we can use easier method instead: while(c.moveToNext())
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n"; 
		}
		return result;
	}

	public String getName(long l) {
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE_NAME, columns, KEY_ROWID + "=" + l, null, null, null, null);
		/*if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}*/
		if (c.moveToFirst()) {
			String name = c.getString(c.getColumnIndex(KEY_NAME));
			c.close();
			return name;
		}
		
		return null;
	}

	public String getHotness(long l) {
		String[] columns = new String[] {KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE_NAME, columns, KEY_ROWID + "=" + l, null, null, null, null);
		/*if (c != null) {
			c.moveToFirst();
			String hotness = c.getString(2);
			return hotness;
		}*/
		if (c.moveToFirst()) {
			String hotness = c.getString(c.getColumnIndex(KEY_HOTNESS));
			c.close();
			return hotness;
		}
		return null;
	}

	public void updateEntry(long lRow, String newName, String newHotness) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_ROWID, lRow);
		cvUpdate.put(KEY_NAME, newName);
		cvUpdate.put(KEY_HOTNESS, newHotness);
		ourDatabase.update(DATABASE_TABLE_NAME, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	public void deleteEntry(long rowId) {
		ourDatabase.delete(DATABASE_TABLE_NAME, KEY_ROWID + "=" + rowId, null);
	}
}
