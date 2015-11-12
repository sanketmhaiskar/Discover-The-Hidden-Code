package com.smartapps4u.discoverthehiddencode.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartapps4u.discoverthehiddencode.R;

public class TimeFinishedScreen extends Activity {

	private SharedPreferences sharedprefs;
	private SharedPreferences.Editor sharedprefs_edit;
	TextView txtGameDifficulty, txtTimeLimit, txtYourScore, btnGameResult_OK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sharedprefs = getSharedPreferences("smartapps4u_DiscovertheCode", 0);

		setContentView(R.layout.timefinished_layout);
		txtGameDifficulty = (TextView) findViewById(R.id.txtGameDifficulty);
		txtTimeLimit = (TextView) findViewById(R.id.txtTimeLimit);
		txtYourScore = (TextView) findViewById(R.id.txtYourScore);
		btnGameResult_OK = (TextView) findViewById(R.id.btnGameResult_OK);

		if (sharedprefs.getInt("GAME_DIFFICULTY", 0) < 1)
			txtGameDifficulty.setText("Game Difficulty: Easy");
		else
			txtGameDifficulty.setText("Game Difficulty: Difficult");

		if (sharedprefs.getInt("TIME_LIMIT", -1) < 0) {
			txtTimeLimit.setText("Time Limit: No Time Limit");
		} else
			txtTimeLimit
					.setText("Time Limit: "
							+ convertsecstoString((sharedprefs.getInt(
									"TIME_LIMIT", -1))) + " minutes");
		// Reset Stats
		txtYourScore.setText("Your Score :"
				+ sharedprefs.getInt("GAME_POINTS", 0));
		// Go to Game Settings
		btnGameResult_OK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				startActivity(new Intent(TimeFinishedScreen.this,
						GameSettings.class));
			}
		});

	}

	public String convertsecstoString(int i) {
		return String.valueOf((int) (i / 60));

	}
}
