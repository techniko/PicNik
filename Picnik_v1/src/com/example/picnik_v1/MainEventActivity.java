package com.example.picnik_v1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainEventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_event);
		Button create =(Button)findViewById(R.id.button1);
		Button join = (Button)findViewById(R.id.buttonJoin);
		
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent create=new Intent(MainEventActivity.this, CreateEvent.class);
			startActivity(create);
			}
		});
		join.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent join=new Intent(MainEventActivity.this,JoinActivity.class);
			startActivity(join);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_event, menu);
		return true;
	}

}
