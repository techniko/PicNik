package com.example.picnik_v1;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateEvent extends Activity {
	String eventname,startdate,enddate,place;
	SQLiteDatabase db;
	 TableRow tableRow;
	   TextView textView,textView1,textView2,textView3,textView4,textView5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		final EditText ename=(EditText)findViewById(R.id.editText1);
		final EditText sdate=(EditText)findViewById(R.id.editText2);
		final EditText edate=(EditText)findViewById(R.id.editText3);
		final EditText plce=(EditText)findViewById(R.id.editText4);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = sdf.format(new Date());
		
		//create db 
		db=openOrCreateDatabase("MyDB1",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Event(eventname VARCHAR,startdate VARCHAR,enddate VARCHAR,place VARCHAR);");

        Button submit=(Button)findViewById(R.id.button1);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.delete("Event", null, null);
				eventname=ename.getText().toString();
				startdate=sdate.getText().toString();
				enddate=edate.getText().toString();
				place=plce.getText().toString();
				db.execSQL("INSERT INTO  Event VALUES('"+eventname+"','"+startdate+"','"+enddate+"','"+place+"');");

			}
		});
	
	
	}
	public void showdata(View view)
	{
	    Cursor c=db.rawQuery("SELECT * from Event", null);
	     int count= c.getCount();
	    c.moveToFirst();
	    TableLayout tableLayout = new TableLayout(getApplicationContext());
	    tableLayout.setVerticalScrollBarEnabled(true);
	   TableRow tableRow;
	   TextView textView,textView1,textView2,textView3,textView4,textView5;
	   tableRow = new TableRow(getApplicationContext());
	   textView=new TextView(getApplicationContext());
	   textView.setText("EventName");
	   textView.setTextColor(Color.RED);
	  	textView.setTypeface(null, Typeface.BOLD);
	  	 textView.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView);
	    textView4=new TextView(getApplicationContext());
	  	textView4.setText("StartDate");
	  	textView4.setTextColor(Color.RED);
	  	textView4.setTypeface(null, Typeface.BOLD);
	  	 textView4.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView4);
	    textView5=new TextView(getApplicationContext());
	  	textView5.setText("Enddate");
	  	textView5.setTextColor(Color.RED);
	  	textView5.setTypeface(null, Typeface.BOLD);
	  	textView5.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView5);
	  	textView3=new TextView(getApplicationContext());
	  	textView3.setText("Place");
	  	textView3.setTextColor(Color.RED);
	  	textView3.setTypeface(null, Typeface.BOLD);
	  	textView3.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView3);
	  	tableLayout.addView(tableRow);
	     for (Integer j = 0; j < count; j++)
	     {
	         tableRow = new TableRow(getApplicationContext());
	         textView1 = new TextView(getApplicationContext());
	         textView1.setText(c.getString(c.getColumnIndex("eventname")));
	         textView2 = new TextView(getApplicationContext());
	         textView2.setText(c.getString(c.getColumnIndex("startdate")));
	         textView3 = new TextView(getApplicationContext());
	         textView3.setText(c.getString(c.getColumnIndex("enddate")));
	         textView4 = new TextView(getApplicationContext());
	         textView4.setText(c.getString(c.getColumnIndex("place")));
	         textView1.setPadding(20, 20, 20, 20);
	         textView1.setTextColor(Color.BLACK);
	         textView2.setPadding(20, 20, 20, 20);
	         textView2.setTextColor(Color.BLACK);
	         textView3.setPadding(20, 20, 20, 20);
	         textView3.setTextColor(Color.BLACK);
	         textView4.setPadding(20, 20, 20, 20);
	         textView4.setTextColor(Color.BLACK);
	         tableRow.addView(textView1);
	         tableRow.addView(textView2);
	         tableRow.addView(textView3);
	         tableRow.addView(textView4);
	         
	         tableLayout.addView(tableRow);
	         c.moveToNext() ;
	     }
	     setContentView(tableLayout);
	db.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_event, menu);
		return true;
	}

}
