package com.example.cocopus;

import java.util.Hashtable;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;

public class Cocopus_NewHolder extends Activity{

	
	//ADDING STUFF TO THE LIBRARY
	//CREATING A TABLE FOR THE LIST
	private Button bAddHolder;
	private ListView listHLib;
	private TableRow firstRow;

	Vector<String> list;
	private Hashtable<String, String> tableTickets = new Hashtable<String,String>();
	private VueDuneLigne v;
	private String title;
	private String date;
	private int amount;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newholder);
//		listHLib = (ListView)
		bAddHolder = (Button)findViewById(R.id.bAddHolder);
		bAddHolder.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.w("HOLDER","NEW HOLDER FINISHED");
				finish();
			}
			
		});
		firstRow = (TableRow) findViewById(R.id.tableRow1);
		firstRow.setOnClickListener(new OnClickListener() {
		       @Override
		        public void onClick(View v) {
		            // TODO: do your logic here
		    	   Log.w("HOLDER", "ROW CLICKED");
		    	   firstRow.setBackgroundColor(Color.parseColor("#92C94A"));
		        }   
		});
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void remplirArrayList(){
		list = new Vector<String>();
		
		tableTickets.put("STM Montreal", "STM-OPUS");
		tableTickets.put("STM off-island", "STM");
		tableTickets.put("RTL Longueil", "RTL-OPUS");
		tableTickets.put("AMT Chateauguay", "ATM-SO-OPUS-E");
		
		list.add(tableTickets.get("STM Montreal"));
		list.add(tableTickets.get("STM off-island"));
		list.add(tableTickets.get("RTL Longueil"));
		list.add(tableTickets.get("AMT Chateauguay"));
	}
	
	
	/***********************DATABASE ACCESS*************************/
	public Cocopus_NewHolder(){
		super();
	
	}
	public Cocopus_NewHolder(String title, String date, int amount){
		this.title = title;
		this.date = date;
		this.amount = amount;
	}
	public String getHolderTitle() {
		return title;
	}
	public void setHolderTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
