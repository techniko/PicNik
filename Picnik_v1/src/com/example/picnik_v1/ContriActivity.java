package com.example.picnik_v1;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContriActivity extends Activity {

	int membercount=0,amt;
	int tick=0;
	SQLiteDatabase db;
	int eventid,memberID;
	EditText amount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contri);
		amount=(EditText)findViewById(R.id.editText1);
		EditText reason=(EditText)findViewById(R.id.editText2);
		Bundle b=getIntent().getExtras();
		eventid=b.getInt("eventid");
		memberID=b.getInt("memberID");

	}

	public void add(View v)
	{
		System.out.println("in add of mycontri");
		amt=Integer.parseInt(amount.getText().toString());
		displayListView();
	}
	private void displayListView() {
		  
		  //Array list of countries
		  ArrayList<String> nameList = new ArrayList<String>();
		  int memID[]=new int[50];
		  db=openOrCreateDatabase("MyDB",MODE_PRIVATE, null);

	      Cursor names=db.rawQuery("SELECT name,memberID from Tour where eventid = "+eventid,null);
	      names.moveToFirst();
	      System.out.println("names count="+names.getCount()+"names position"+names.getPosition());
	      final CheckBox name[]=new CheckBox[20];
	      LinearLayout listlayout=new LinearLayout(getApplicationContext());
	      listlayout.setOrientation(LinearLayout.VERTICAL);
	      TextView title=new TextView(this);
	      title.setText("Member Names:");
	      listlayout.addView(title);
	      for(int i=0;i<names.getCount();i++)
	      {
	      memID[i]=names.getInt(names.getColumnIndex("memberID"));
		  nameList.add(i,names.getString(names.getColumnIndex("name")));
		  System.out.println("name="+names.getString(names.getColumnIndex("name")));	  
		  name[i] = new CheckBox(this);
	      name[i].setText(names.getString(names.getColumnIndex("name")));
	      names.moveToNext();
	      listlayout.addView(name[i]);
	      membercount++;
	      }
	      names.close();
	      Button addcontri=new Button(getApplicationContext());
	      addcontri.setText("Add");
	      listlayout.addView(addcontri);
	      final int tickedMem[]=new int[20];
	      
	      System.out.println("membercount="+membercount);
	      
	      addcontri.setOnClickListener(new OnClickListener() {
	    	int cnt=0;
			@Override
			public void onClick(View arg0) {

				for(int j=0;j<membercount;j++)
				{
					if(name[j].isChecked())
					{
						tickedMem[cnt]=j;
						System.out.println("tickedMem["+cnt+"]="+j);
						tick++;
						cnt++;
					}
				}
				System.out.println("tick="+tick+"amt="+amt+"tickedmem.length"+tickedMem.length);
			      int contri=amt/tick;
			      int current=0;
			      String currentContri="null";
			      Cursor mem;
			      for(int i=0;i<tick;i++)
			      {
			    	  mem=db.rawQuery("SELECT contri from Trans where eventid = "+eventid, null);
			    	  System.out.println("mem.count="+mem.getCount());
			    	  mem.moveToFirst();
			    	  current=mem.getInt(tickedMem[i]);
			    	  System.out.println("prev contri="+mem.getInt(tickedMem[i])+"current ="+contri);
			    	  
			    	  String strSQL = "UPDATE Trans SET contri = "+(current-contri)+" WHERE memberID = "+tickedMem[i]+" AND eventid = "+eventid ;

		    		  db.execSQL(strSQL);
		    		  System.out.println("update done");
			    	  
//		    		  System.out.println("c.getcount="+c.getCount());
//		    		  current=c.getInt(c.getColumnIndex("contri"));
			      }
			      
			    /*  for(int k=0;k<membercount;k++)
			      {
			    	  if(k==tickedMem[k])
			    	  {
			    		  System.out.println("eventid="+eventid+"memberID="+memberID);
			    		  //error here
			    		  Cursor c=db.rawQuery("SELECT * from Trans where eventid = "+eventid, null);
			    		  System.out.println("c.getcount="+c.getCount());
			    		  current=c.getInt(c.getColumnIndex("contri"));
			    		 
			    		  db.execSQL("INSERT OR REPLACE INTO Trans" 
			    					+ " (eventid, memberID, contri)" + " VALUES ('"
			    					+ eventid + "','" + memberID + "','" +  contri + "','"
			    					+  "');");
			    		  
			    		  
			    		  String strSQL = "UPDATE Trans SET contri = "+(current-contri)+" WHERE memberID = "+memberID+" AND eventid = "+eventid ;

			    		  db.execSQL(strSQL);
			    		  System.out.println("update done");
			    		  
			    	  }
			    	  else
			    	  {
			    		  System.out.println("in else ");
			    	  }
			      }

			
*/		
			}
	     });
	      //calculate contri
	      //	      ListView name = new ListView(this);
	      
//	      ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
//	      name.setAdapter(modeAdapter);
	      
			 // setContentView(listlayout);
		  setContentView(listlayout);
		 }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contri, menu);
		return true;
	}

}
