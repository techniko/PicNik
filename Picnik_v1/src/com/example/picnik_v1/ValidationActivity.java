package com.example.picnik_v1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ValidationActivity extends Activity {

	EditText value;
	SQLiteDatabase db;
	String tablename="Details";
	TextView errormsg,title;
	int eventid,memberID;
	OnClickListener eventidListener;
	OnClickListener memberIDListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_validation);
		value=(EditText)findViewById(R.id.eventId);
		Button join=(Button)findViewById(R.id.join);
		Button login=(Button)findViewById(R.id.loginbtn);

		errormsg=(TextView)findViewById(R.id.eventIDError);
		title=(TextView)findViewById(R.id.textView1);
		final TextView msg1=(TextView)findViewById(R.id.textView2);
        final TextView msg2=(TextView)findViewById(R.id.textView3);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        eventid = bundle.getInt("eventid");
        if(eventid<0)
        {}
        else
        {
        value.setText(""+eventid);
        value.setEnabled(false);
        msg2.setText(""+eventid);
        msg1.setVisibility(1);
        msg2.setVisibility(1);
        }
	
        eventidListener= new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	System.out.println("in eventidListener onclick");
	        	title.setText("Enter existing memberID");
	    		eventid=Integer.parseInt(value.getText().toString());
	    		value.setText("");
	            v.setOnClickListener(memberIDListener);
	        }
	    };

	    memberIDListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	System.out.println("in memberidListener onclick");
	        	db=openOrCreateDatabase("MyDB",MODE_PRIVATE, null);
				Cursor mid=db.rawQuery("SELECT memberID from Tour where eventid = "+eventid+" and memberID = "+Integer.parseInt(value.getText().toString()), null);
				if(mid.getCount()>0)
				{
					Intent Details=new Intent(ValidationActivity.this, LoginDetails.class);
					Bundle bundle = new Bundle();
					  //Add your data to bundle
					  bundle.putInt("eventid", eventid);  
					  bundle.putInt("memberID", Integer.parseInt(value.getText().toString()));
					  //Add the bundle to the intent
					  Details.putExtras(bundle);
					  db.close();
					startActivity(Details);
				}
				else
				{
					System.out.println("memberIDListener in else");
					errormsg.setVisibility(1);
					v.setOnClickListener(eventidListener);
				}
	            
	        }
	    };
	
	}
	public void _loginEvent(View V)
	{
		System.out.println("in loginEvent function");
	}
	public void joinEvent(View V)
	{
		String eid=value.getText().toString();
		db=openOrCreateDatabase("MyDB",MODE_PRIVATE, null);
		Cursor c=db.rawQuery("select * from "+tablename+" where eventid="+Integer.parseInt(eid), null);
		System.out.println("EventID="+eid+"count"+c.getCount());
		if(c.getCount()>0)
		{
			Intent Details=new Intent(ValidationActivity.this, LoginDetails.class);
			Bundle bundle = new Bundle();
			  //Add your data to bundle
			  bundle.putInt("eventid", Integer.parseInt(eid));  
			  //Add the bundle to the intent
			  Details.putExtras(bundle);
			  c.close();
			startActivity(Details);
			
		}
		else
		{
			errormsg.setVisibility(1);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_validation, menu);
		return true;
	}

}
