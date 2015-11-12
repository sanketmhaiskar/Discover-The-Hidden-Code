package com.smartapps4u.discoverthehiddencode.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartapps4u.discoverthehiddencode.R;

public class BaseActivity extends Activity{

	TextView btnStartNow,btnLearnToPlay;
	private SharedPreferences Sharedprefs;
	private SharedPreferences.Editor Sharedprefs_edit;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Sharedprefs=getSharedPreferences("smartapps4u_DiscovertheCode", 0);
		Sharedprefs_edit=Sharedprefs.edit();
		btnStartNow=(TextView)findViewById(R.id.btnStartNow);
		btnLearnToPlay=(TextView)findViewById(R.id.btnLearnToPlay);
		btnStartNow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startNow =new Intent(BaseActivity.this,GameSettings.class);
				startActivity(startNow);
			}
		});
		btnLearnToPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startNow = new Intent(BaseActivity.this,LearnToPlay.class);
				startActivity(startNow);
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		/*Sharedprefs_edit.clear();
		Sharedprefs_edit.commit();*/
		finish();
	}
}
