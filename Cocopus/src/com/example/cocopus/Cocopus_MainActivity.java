package com.example.cocopus;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//TAKE CARE OF VUEDUNELIGNE JAVA
//GO ON WITH MONADAPTER
//CUSTOMIZE NUMBERPICKEROR MAKE IT WORK
//TAKE CARE OF PASSING DATAS
public class Cocopus_MainActivity extends Activity {
	
	private ListView listHolder;
	private Button newHolderButton;
	private ArrayList<Hashtable> list;
	MonAdapter adapter;
	private int ticketdata = 2;
	private int ticketH;
	private VueDuneLigne vlc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listHolder = (ListView)findViewById(R.id.listHolder);
        newHolderButton = (Button)findViewById(R.id.newHolderButton);
        remplirArrayList();
        
        Ecouteur ec = new Ecouteur();
        newHolderButton.setOnClickListener(ec);
        Ecouteur ec2 = new Ecouteur();
        listHolder.setOnItemClickListener(ec2);
        
        
        adapter = new MonAdapter(this,list);
        listHolder.setAdapter(adapter);
    }

    private class Ecouteur implements OnClickListener,OnItemClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent i = new Intent("NEWHOLDER");
			startActivity(i);
			Log.w("HOLDER", "BUTTON CLICKED");
			
			
			
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
			vlc =(VueDuneLigne)arg1.getTag();
//			sendData();
			Intent i = new Intent("ADDTICKET");
			
			String t = (String) vlc.getHolderAmount().getText();
			i.putExtra("MESSAGE", t);
			
			
			startActivityForResult(i, ticketdata);
			Log.w("HOLDER", "ADDING TICKETS...");
			
			
		}
    	
    }
    public class MonAdapter extends BaseAdapter{

    	
    	public ArrayList<Hashtable> list;
    	Context context;
    	
    	public MonAdapter(Context context,ArrayList<Hashtable> list){
    		super();
    		this.context = context;
    		this.list = list;
    	}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			VueDuneLigne hold;
			View v = convertView;
			
			if(v == null){
				LayoutInflater vi;
				vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				v = vi.inflate(R.layout.itemholder, parent, false);
				hold = new VueDuneLigne();
				hold.setHolderTitle((TextView)v.findViewById(R.id.holderTitle));
				hold.setHolderDate((TextView)v.findViewById(R.id.holderDate));
				hold.setHolderAmount((TextView)v.findViewById(R.id.holderAmount));
				
				v.setTag(hold);
			}
			else{
				hold = (VueDuneLigne)v.getTag();
			}
			Hashtable h = list.get(position);
			hold.getHolderTitle().setText((String)h.get("title"));
			hold.getHolderDate().setText((String)h.get("date"));
			hold.getHolderAmount().setText(h.get("amount").toString());
			
//			Log.w("HOLDER", h.get("amount").toString());
//			hold.getHolderAmount().setText((Integer)h.get("amount"));
			return v;
		}
    	
    	
    }
    private void remplirArrayList(){
    	
    	Log.w("HOLDER", "remplissage ArrayList");
    	list = new ArrayList<Hashtable>();
    	Hashtable holder = new Hashtable();
    	holder.put("id", 0);
    	holder.put("title", "STM Centre-Ville");
    	holder.put("date", "11-11-11");
    	holder.put("amount", 10);
    	
    	list.add(holder);
    	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.w("HOLDER", "RESULT IN ACTION, resultCode=" + resultCode);
		Log.w("HOLDER", "RESULT IN ACTION, requestCode=" + requestCode);
		if(resultCode == RESULT_CANCELED){
			Log.w("HOLDER", "RESULT CANCELLED");

		}
		
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			ticketH = extras.getInt("MESSAGE");
			
			Log.w("HOLDER", "GOT THE MESSAGE : " + ticketH + " old amount :" + vlc.getHolderAmount().getText());
			updateHolderTickets(vlc.getHolderTitle().getText().toString(),ticketH);
			adapter.notifyDataSetChanged();
		}
	}
	
	private void updateHolderTickets(String holderTitle, int newAmount){
		Iterator iter = list.iterator();
		Log.w("HOLDER","UPDATING AMOUNT of " + holderTitle + " to " + newAmount);
		while (iter.hasNext()){
			Hashtable elem = (Hashtable) iter.next();
			if(elem.get("title").equals(holderTitle)){
				int  a = (Integer)elem.get("amount");
				elem.put("amount", newAmount);
			}
		}
	}
	
	private void sendData(){
		Log.w("HOLDER", "SENDING DATA FROM MAIN TO ADDTICKET : " + vlc.getHolderAmount().getText());
		String t = (String) vlc.getHolderAmount().getText();
		
		Intent intent = new Intent(Cocopus_MainActivity.this, Cocopus_AddTicketProject.class);
		intent.putExtra("MESSAGE", t);
		
		
	}
}
