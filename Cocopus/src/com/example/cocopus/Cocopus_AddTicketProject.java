package com.example.cocopus;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;

public class Cocopus_AddTicketProject extends Activity{
	private NumberPicker np;
	private Button buttonTicket;
	private ArrayList<Hashtable> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numberpicker);
		buttonTicket = (Button)findViewById(R.id.buttonTicket);
		np = (NumberPicker)findViewById(R.id.numberPicker1);
		np.setMinValue(0);
		np.setMaxValue(99);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String value = extras.getString("MESSAGE");
		    Log.w("HOLDER", "DATA RECEIVED : " + value);
		    
		    try {
		    	np.setValue(Integer.parseInt(value));
		    } catch(NumberFormatException nfe) {
		       System.out.println("Could not parse " + nfe);
		    } 
		}
		else{
			Log.w("HOLDER", "NO DATA FROM MAIN");
		}


		
		Ecouteur ec = new Ecouteur();
		buttonTicket.setOnClickListener(ec);
		
		
	}
	public class Ecouteur implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			sendData();
		}
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("HOLDER", "ADDTICKET ONACTIVITYRESULT");
		//		if(resultCode == 2){
//			Bundle extras = data.getExtras();
//			
//		}
	
	}


	public void sendData(){
		Log.w("HOLDER", "ACTIVITY PAUSED");
		Integer t = np.getValue();
		Log.w("HOLDER", "VALUE IS " + t);
		
		Intent intent = new Intent(Cocopus_AddTicketProject.this, Cocopus_MainActivity.class);
//		Intent intent = new Intent("ADDTICKET");

		intent.putExtra("MESSAGE", t);
		
		int ticketdata = 2;
		setResult(RESULT_OK , intent);
		
		finish();
	}

}
