package com.smartapps4u.discoverthehiddencode.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartapps4u.discoverthehiddencode.R;

public class PuzzleReview extends Activity {
	Bundle mBundle;
	String pz, valueArray, UserAnswer;
	TextView btn_GameReviewNext, txtTimer, txtPoints, txtDifficulty;

	private SharedPreferences Sharedprefs;
	private SharedPreferences.Editor Sharedprefs_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puzzle_review);
		mBundle = this.getIntent().getExtras();
		btn_GameReviewNext = (TextView) findViewById(R.id.btn_GameReviewNext);
		txtTimer = (TextView) findViewById(R.id.txtTimer);
		txtPoints = (TextView) findViewById(R.id.txtPoints);
		txtDifficulty = (TextView) findViewById(R.id.txtDifficulty);

		Sharedprefs = getSharedPreferences("smartapps4u_DiscovertheCode", 0);
		txtTimer.setText("Time: "
				+ GameScreen.convertSecs(Sharedprefs.getInt("TIME_ELAPSED", 0)));
		txtPoints.setText("Points :" + Sharedprefs.getInt("GAME_POINTS", 0));
		if (Sharedprefs.getInt("GAME_DIFFICULTY", 0) < 1)
			txtDifficulty.setText("Easy");
		else
			txtDifficulty.setText("Difficult");
		pz = mBundle.getString("PuzzlePiece");
		UserAnswer = mBundle.getString("UserAnswer");
		valueArray = mBundle.getString("ValueArray");
		parsePuzzle(mBundle.getString("PuzzleString"));

		btn_GameReviewNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				startActivity(new Intent(getApplicationContext(),
						GameScreen.class));
			}
		});

	}

	public void parsePuzzle(String PuzzleString) {

		int nclues = 0;
		int k = 0;
		nclues = PuzzleString.length() / 6 - 1;
		System.err.println(nclues);
		for (int i = 1; i < nclues + 1; i++) {
			int id1 = getApplicationContext().getResources().getIdentifier(
					"LinLay_clue" + String.valueOf(i), "id", getPackageName());
			LinearLayout linlay_clue = (LinearLayout) findViewById(id1);
			linlay_clue.setVisibility(View.VISIBLE);
		}
		k = 6;

		for (int i = 0; i < 6; i++) {

			int id = getApplicationContext().getResources().getIdentifier(
					pz + "_" + valueArray.charAt(i), "drawable",
					getPackageName());
			int id1 = getApplicationContext().getResources().getIdentifier(
					"option_letter" + String.valueOf(i + 1), "id",
					getPackageName());
			// clue1_letter1.setImageResource(id);
			ImageView setimage;
			setimage = (ImageView) findViewById(id1);
			setimage.setImageResource(id);
		}

		for (int i = 1; i < nclues + 1; i++) {
			for (int j = 1; j < 5; j++) {

				int id = getApplicationContext().getResources().getIdentifier(
						pz
								+ "_"
								+ valueArray.charAt(Integer
										.valueOf(PuzzleString.charAt(k)) - 49),
						"drawable", getPackageName());
				int id1 = getApplicationContext().getResources().getIdentifier(
						"clue" + i + "_letter" + j, "id", getPackageName());

				// clue1_letter1.setImageResource(id);

				ImageView setimage;
				setimage = (ImageView) findViewById(id1);
				setimage.setImageResource(id);
				k++;
			}
			// Concat r and w
			StringBuilder rws = new StringBuilder();

			for (int r = 0; r < (Integer.valueOf(PuzzleString.charAt(k)) - 48); r++) {
				rws.append("r");
			}
			k++;

			for (int w = 0; w < (Integer.valueOf(PuzzleString.charAt(k)) - 48); w++) {
				rws.append("w");
			}
			k++;

			int id_rw = getApplicationContext().getResources().getIdentifier(
					"clue" + i + "_rw", "id", getPackageName());

			TextView rwtxt;
			rwtxt = (TextView) findViewById(id_rw);
			rwtxt.setText(rws.toString());

		}
		// Set UserAnswer
		for (int i = 0; i < 4; i++) {
			try {
				int id = getApplicationContext().getResources().getIdentifier(
						pz
								+ "_"
								+ valueArray.charAt(Integer.valueOf(UserAnswer
										.charAt(i)) - 49), "drawable",
						getPackageName());
				System.out.println(id);
				int id1 = getApplicationContext().getResources().getIdentifier(
						"answer_letter" + String.valueOf(i + 1), "id",
						getPackageName());
				System.out.println(id1);
				ImageView setimage;
				setimage = (ImageView) findViewById(id1);
				setimage.setImageResource(id);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
}
