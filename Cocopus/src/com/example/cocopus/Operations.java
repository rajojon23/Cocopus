package com.example.cocopus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Operations {
	private Cocopus_DatabaseHelper databaseHelper;
	private static SQLiteDatabase database;

	public Operations(Context c){
		databaseHelper = new Cocopus_DatabaseHelper(c);
	}
	
	public void ouvrirBD(Context c){
		database = databaseHelper.getWritableDatabase();		
	}
	public void fermerBD(){
		database.close();
	}
	public void ajouterHolder(Cocopus_NewHolder h){
		ContentValues cv = new ContentValues();
		cv.put("title", h.getHolderTitle());
		cv.put("date", h.getDate());
		cv.put("amount", h.getAmount());
		
		Log.w("HOLDER", "ADDING HOLDER TO DB");
		database.insert("holders", null, cv);
	}
//	public String getTitleDBHolder(){
//		Cursor curs = database.rawQuery("SELECT * FROM ", selectionArgs);
//		return null;
//		
//	}
}
