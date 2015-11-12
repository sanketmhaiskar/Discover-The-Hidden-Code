package com.smartapps4u.discoverthehiddencode.views;

import com.smartapps4u.discoverthehiddencode.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PuzzleResult extends Activity {
	Bundle mBundle;
	String pz = "";
	TextView txtResult, txtResultStatus, txtPointStatus,
			btnPuzzleResult_Review, btnPuzzleResult_OK, btnPuzzleResult_Next;
	private SharedPreferences Sharedprefs;
	int GAME_POINTS = 0;
	private SharedPreferences.Editor Sharedprefs_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBundle = this.getIntent().getExtras();
		Sharedprefs = getSharedPreferences("smartapps4u_DiscovertheCode", 0);
		GAME_POINTS = Sharedprefs.getInt("GAME_POINTS", 0);
		Sharedprefs_edit = Sharedprefs.edit();
		switch (mBundle.getInt("UserAction")) {
		case 1:
			// User has answered
			if (mBundle.getString("UserAnswer").equals(
					mBundle.getString("CorrectAnswer"))) {
				// For Correct Answer
				setContentView(R.layout.puzzle_result_correct);
				Sharedprefs_edit.putInt("GAME_POINTS", GAME_POINTS + 1);
				Sharedprefs_edit.commit();
				txtResult = (TextView) findViewById(R.id.txtResult);
				txtResultStatus = (TextView) findViewById(R.id.txtResultStatus);
				txtPointStatus = (TextView) findViewById(R.id.txtPointStatus);
				btnPuzzleResult_OK = (TextView) findViewById(R.id.btnPuzzleResult_OK);
				btnPuzzleResult_OK
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(PuzzleResult.this,
										GameScreen.class);
								startActivity(intent);
								finish();
							}
						});

				txtResult.setText("Congratulations");
				txtResultStatus.setText("You solved this Puzzle.");
				txtPointStatus.setText("You get one point.");

			} else {
				// For Incorrect Answer
				setContentView(R.layout.puzzle_result_incorrect);
				Sharedprefs_edit.putInt("GAME_POINTS", GAME_POINTS - 1);
				Sharedprefs_edit.commit();
				txtResult = (TextView) findViewById(R.id.txtResult);
				txtResultStatus = (TextView) findViewById(R.id.txtResultStatus);
				txtPointStatus = (TextView) findViewById(R.id.txtPointStatus);
				btnPuzzleResult_Review = (TextView) findViewById(R.id.btnPuzzleResult_Review);
				btnPuzzleResult_Next = (TextView) findViewById(R.id.btnPuzzleResult_Next);

				txtResult.setText("Wrong Answer");
				txtResultStatus.setText("The correct answer is");
				txtPointStatus.setText("One point deducted.");
				btnPuzzleResult_Next
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(PuzzleResult.this,
										GameScreen.class);
								startActivity(intent);
								finish();
							}
						});

				btnPuzzleResult_Review
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(PuzzleResult.this,
										PuzzleReview.class);
								intent.putExtra("ValueArray",
										mBundle.getString("ValueArray"));
								intent.putExtra("UserAnswer",
										mBundle.getString("UserAnswer"));
								intent.putExtra("PuzzleString",
										mBundle.getString("PuzzleString"));
								intent.putExtra("PuzzlePiece",
										mBundle.getString("PuzzlePiece"));
								startActivity(intent);
								finish();
							}
						});

			}
			break;

		case 2:
			// User Gives UP
			Sharedprefs_edit.putInt("GAME_POINTS", GAME_POINTS - 1);
			Sharedprefs_edit.commit();
			setContentView(R.layout.puzzle_result_correct);

			txtResult = (TextView) findViewById(R.id.txtResult);
			txtResultStatus = (TextView) findViewById(R.id.txtResultStatus);
			txtPointStatus = (TextView) findViewById(R.id.txtPointStatus);
			btnPuzzleResult_OK = (TextView) findViewById(R.id.btnPuzzleResult_OK);

			txtResult.setText("Try Another Puzzle");
			txtResultStatus.setText("Puzzle was unsolved ");
			txtPointStatus.setText("One point deducted.");

			btnPuzzleResult_OK.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(PuzzleResult.this,
							GameScreen.class);
					startActivity(intent);
					finish();
				}
			});

			break;

		}
		/** Set Answer **/
		String correctans = mBundle.getString("CorrectAnswer");
		String valuearray = mBundle.getString("ValueArray");
		pz = mBundle.getString("PuzzlePiece");
		for (int i = 1; i < 5; i++) {
			System.out.println(valuearray.charAt(Integer.valueOf(correctans
					.charAt(i - 1)) - 49));
			int id = getApplicationContext().getResources().getIdentifier(
					pz
							+ "_"
							+ valuearray.charAt(Integer.valueOf(correctans
									.charAt(i - 1)) - 49), "drawable",
					getPackageName());
			int id1 = getApplicationContext().getResources().getIdentifier(
					"correctanswer_letter" + i, "id", getPackageName());
			ImageView setimage;
			setimage = (ImageView) findViewById(id1);
			setimage.setImageResource(id);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamer_result, menu);
		return true;
	}

}
