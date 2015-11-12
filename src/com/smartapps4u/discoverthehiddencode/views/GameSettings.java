package com.smartapps4u.discoverthehiddencode.views;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.smartapps4u.discoverthehiddencode.R;
import com.smartapps4u.discoverthehiddencode.util.GetPuzzle;
import com.smartapps4u.discoverthehiddencode.util.GlobalConfig;

public class GameSettings extends Activity {
	TextView btnEasy, btnDifficult;
	TextView btn5, btn10, btn15, btnInfinity;
	TextView btnLetter1, btnLetter2, btnThumbpin;
	TextView btnStart, btnBack;
	private SharedPreferences Sharedprefs;
	private Context mContext;
	private SharedPreferences.Editor Sharedprefs_edit;

	int Settings_difficulty = 0, Settings_time = 300, Settings_puzzlepiece = 1;
	int GAME_LEVEL = 0, GAME_TIME = 0, GAME_PIECE = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesettings_layout);
		mContext = this;
		Sharedprefs = getSharedPreferences("smartapps4u_DiscovertheCode", 0);
		// getsettings();

		/**                                                **/
		Sharedprefs_edit = Sharedprefs.edit();

		btnEasy = (TextView) findViewById(R.id.btnEasy);
		btnDifficult = (TextView) findViewById(R.id.btnDifficult);

		btn5 = (TextView) findViewById(R.id.btn5);
		btn10 = (TextView) findViewById(R.id.btn10);
		btn15 = (TextView) findViewById(R.id.btn15);
		btnInfinity = (TextView) findViewById(R.id.btnInfinity);

		btnLetter1 = (TextView) findViewById(R.id.btnLetter1);
		btnLetter2 = (TextView) findViewById(R.id.btnLetter2);
		btnThumbpin = (TextView) findViewById(R.id.btnThumbpin);

		btnStart = (TextView) findViewById(R.id.btnStart);
		btnBack = (TextView) findViewById(R.id.btnBack);

		/*** LISTENERS **/

		btnEasy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_difficulty = 0;
				// btnEasy.setActivated(true);
				btnEasy.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnEasy.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btnDifficult.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnDifficult.setTextColor(getApplicationContext()
						.getResources().getColor(
								R.color.button_default_textcolor));
				// btnDifficult.setActivated(false);
			}
		});

		btnDifficult.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_difficulty = 1;
				// btnEasy.setActivated(false);
				// btnDifficult.setActivated(true);
				btnEasy.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnEasy.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnDifficult.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnDifficult.setTextColor(getApplicationContext()
						.getResources().getColor(
								R.color.button_pressed_textcolor));
			}
		});

		btn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_time = 300;
				btn5.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btn5.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btn10.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn10.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btn15.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn15.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnInfinity.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnInfinity.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		btn10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_time = 600;
				btn5.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn5.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btn10.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btn10.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btn15.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn15.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnInfinity.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnInfinity.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		btn15.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_time = 900;
				btn15.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btn15.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btn10.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn10.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btn5.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn5.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnInfinity.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnInfinity.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		btnInfinity.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_time = -1;
				btnInfinity.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnInfinity.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btn10.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn10.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btn15.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn15.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btn5.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btn5.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		btnLetter1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_puzzlepiece = 1;
				btnLetter1.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnLetter1.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btnLetter2.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnLetter2.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnThumbpin.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnThumbpin.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		btnLetter2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_puzzlepiece = 2;
				btnLetter2.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnLetter2.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btnLetter1.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnLetter1.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnThumbpin.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnThumbpin.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});
		btnThumbpin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings_puzzlepiece = 3;
				btnThumbpin.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_pressed_background));
				btnThumbpin.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_pressed_textcolor));
				btnLetter2.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnLetter2.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
				btnLetter1.setBackgroundDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.button_default_background));
				btnLetter1.setTextColor(getApplicationContext().getResources()
						.getColor(R.color.button_default_textcolor));
			}
		});

		/*
		 * btnEasy.performClick(); btn5.performClick();
		 * btnLetter1.performClick();
		 */
		switch (Sharedprefs.getInt("GAME_DIFFICULTY", 0)) {
		case 0:
			btnEasy.performClick();
			break;
		case 1:
			btnDifficult.performClick();
			break;
		}

		switch (Sharedprefs.getInt("TIME_LIMIT", 300)) {
		case 300:
			btn5.performClick();
			break;
		case 600:
			btn10.performClick();
			break;
		case 900:
			btn15.performClick();
			break;
		case -1:
			btnInfinity.performClick();
			break;
		}

		switch (Sharedprefs.getInt("GAME_PIECE", 1)) {
		case 1:
			btnLetter1.performClick();
			break;

		case 2:
			btnLetter2.performClick();
			break;
		case 3:
			btnThumbpin.performClick();
			break;
		}

		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Sharedprefs_edit.putInt("TIME_LIMIT", Settings_time);
				Sharedprefs_edit.putInt("TIME_ELAPSED", 0);
				Sharedprefs_edit.putBoolean("FIRST_LAUNCH", true);
				Sharedprefs_edit.putInt("GAME_DIFFICULTY", Settings_difficulty);
				Sharedprefs_edit.putInt("GAME_PIECE", Settings_puzzlepiece);
				Sharedprefs_edit.putInt("GAME_POINTS", 0);
				Sharedprefs_edit.commit();
				GlobalConfig.puzzleindex = 0;
				GlobalConfig.puzzles = new ArrayList<String>();
				GlobalConfig.gp = new GetPuzzle(mContext);
				GlobalConfig.gp.setGamedifficuly(Settings_difficulty);

				finish();
				startActivity(new Intent(getApplicationContext(),
						GameScreen.class));
			}
		});

		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
