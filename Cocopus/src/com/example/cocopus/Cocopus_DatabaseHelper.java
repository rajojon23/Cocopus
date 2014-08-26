package com.example.cocopus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Cocopus_DatabaseHelper extends SQLiteOpenHelper{

	public Cocopus_DatabaseHelper(Context context) {
		super(context, "db",null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE holders(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, date NUMERIC, amount INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS holders");
		onCreate(db);
	}
	
}
