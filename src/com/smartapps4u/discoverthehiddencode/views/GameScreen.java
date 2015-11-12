package com.smartapps4u.discoverthehiddencode.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartapps4u.discoverthehiddencode.R;
import com.smartapps4u.discoverthehiddencode.util.GlobalConfig;

public class GameScreen extends Activity {

	String pz = "";

	ImageView clue1_letter1, clue1_letter2, clue1_letter3, clue1_letter4;
	ImageView clue2_letter1, clue2_letter2, clue2_letter3, clue2_letter4;
	ImageView clue3_letter1, clue3_letter2, clue3_letter3, clue3_letter4;
	ImageView clue4_letter1, clue4_letter2, clue4_letter3, clue4_letter4;

	ImageView answer_1, answer_2, answer_3, answer_4;

	Drawable ImgAns;
	ImageView option_letter1, option_letter2, option_letter3, option_letter4,
			option_letter5, option_letter6;

	LinearLayout layoutDropArea;
	TextView btn_GameScreenCheck, btn_GameScreenGiveUp, btn_GameScreenBack;

	private int answerValue = 0;

	private Context mContext;
	int ansindex = 0;
	private int GAME_TIME = 0;
	private int gamesecs = 0;
	private int GAME_POINTS = 0;
	private int GAME_PIECE = 1;
	private int GAME_DIFFICULTY = 0;
	private Boolean FIRST_LAUNCH = false;

	private SharedPreferences Sharedprefs;
	private SharedPreferences.Editor Sharedprefs_edit;

	TextView clue1_rw, clue2_rw, clue3_rw, clue4_rw;
	TextView game_timer, game_points, txtgame_difficulty;
	Boolean optionclicked = false;
	Boolean answerclicked = false;
	static int[] valueArray = new int[6];
	int[] UserAnswerArray = { 0, 0, 0, 0 };
	int anscount = 1;
	int removeindex = 0;
	String PuzzleString;
	Handler handler;
	Timer timer;
	RelativeLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamescreen);

		// valueArray = new int[] { 1, 2, 4, 5, 6, 3 };
		Sharedprefs = getSharedPreferences("smartapps4u_DiscovertheCode", 0);
		GAME_TIME = Sharedprefs.getInt("TIME_LIMIT", 0);
		GAME_POINTS = Sharedprefs.getInt("GAME_POINTS", 0);
		GAME_PIECE = Sharedprefs.getInt("GAME_PIECE", 1);
		GAME_DIFFICULTY = Sharedprefs.getInt("GAME_DIFFICULTY", 0);
		FIRST_LAUNCH = Sharedprefs.getBoolean("FIRST_LAUNCH", false);
		container = (RelativeLayout) findViewById(R.id.container);

		option_letter1 = (ImageView) findViewById(R.id.option_letter1);
		option_letter2 = (ImageView) findViewById(R.id.option_letter2);
		option_letter3 = (ImageView) findViewById(R.id.option_letter3);
		option_letter4 = (ImageView) findViewById(R.id.option_letter4);
		option_letter5 = (ImageView) findViewById(R.id.option_letter5);
		option_letter6 = (ImageView) findViewById(R.id.option_letter6);

		game_timer = (TextView) findViewById(R.id.txtTimer);
		game_points = (TextView) findViewById(R.id.txtPoints);
		txtgame_difficulty = (TextView) findViewById(R.id.txtDifficulty);

		if (GAME_DIFFICULTY == 0)
			txtgame_difficulty.setText("Easy");
		else
			txtgame_difficulty.setText("Difficult");
		game_points.setText("Points: " + GAME_POINTS);

		layoutDropArea = (LinearLayout) findViewById(R.id.linlay_answers);
		answer_1 = (ImageView) findViewById(R.id.answer_letter1);
		answer_2 = (ImageView) findViewById(R.id.answer_letter2);
		answer_3 = (ImageView) findViewById(R.id.answer_letter3);
		answer_4 = (ImageView) findViewById(R.id.answer_letter4);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				game_timer.setText("Time: " + convertSecs(msg.what));
				if (msg.what >= GAME_TIME) {
					timer.cancel();
					finish();
					startActivity(new Intent(getApplicationContext(),
							TimeFinishedScreen.class));
				}
			}

		};

		if (android.os.Build.VERSION.SDK_INT > 10) {

			container.setOnDragListener(new View.OnDragListener() {

				@Override
				public boolean onDrag(View v, DragEvent event) {
					// TODO Auto-generated method stub

					if (event.getAction() == DragEvent.ACTION_DROP
							&& !optionclicked) {
						UserAnswerArray[removeindex] = 0;
						ClipData clip = event.getClipData();
						ClipData.Item item = clip.getItemAt(0);
						int id = getApplicationContext().getResources()
								.getIdentifier("red_question", "drawable",
										getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(item.getText().toString(), "id",
										getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
						optionclicked = false;
						answerclicked = false;
					}

					return true;
				}
			});
			answer_1.setOnDragListener(new View.OnDragListener() {

				@Override
				public boolean onDrag(View v, DragEvent event) {
					// TODO Auto-generated method stub

					if (event.getAction() == DragEvent.ACTION_DROP) {
						if (UserAnswerArray[0] == 0) {
							if (answerclicked) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
								answer_1.setImageDrawable(ImgAns);
								UserAnswerArray[0] = answerValue;
								optionclicked = false;
								System.err.println("HERE IN ANSWER CLICKED");
							} else {
								answer_1.setImageDrawable(ImgAns);
								UserAnswerArray[0] = answerValue;
								optionclicked = false;
							}
						} else {
							if (answerclicked && removeindex != 0) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
							}
						}
					}

					return true;
				}
			});

			answer_2.setOnDragListener(new View.OnDragListener() {

				@Override
				public boolean onDrag(View v, DragEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == DragEvent.ACTION_DROP) {
						if (UserAnswerArray[1] == 0) {
							if (answerclicked) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
								answer_2.setImageDrawable(ImgAns);
								UserAnswerArray[1] = answerValue;
								optionclicked = false;
								System.err.println("HERE IN ANSWER CLICKED");
							} else {
								answer_2.setImageDrawable(ImgAns);
								UserAnswerArray[1] = answerValue;
								optionclicked = false;
							}
						} else {
							if (answerclicked && removeindex != 1) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
							}

						}
					}

					return true;
				}
			});
			answer_3.setOnDragListener(new View.OnDragListener() {

				@Override
				public boolean onDrag(View v, DragEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == DragEvent.ACTION_DROP) {
						if (UserAnswerArray[2] == 0) {
							if (answerclicked) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
								answer_3.setImageDrawable(ImgAns);
								UserAnswerArray[2] = answerValue;
								optionclicked = false;
								System.err.println("HERE IN ANSWER CLICKED");
							} else {
								answer_3.setImageDrawable(ImgAns);
								UserAnswerArray[2] = answerValue;
								optionclicked = false;
							}
						} else {

							if (answerclicked && removeindex != 2) {
								answerclicked = false;
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
							}
						}
					}

					return true;
				}
			});
			answer_4.setOnDragListener(new View.OnDragListener() {

				@Override
				public boolean onDrag(View v, DragEvent event) {
					// TODO Auto-generated method stub

					if (event.getAction() == DragEvent.ACTION_DROP) {
						if (UserAnswerArray[3] == 0) {
							if (answerclicked) {
								answerclicked = false;

								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);
								// optionclicked=false;
								answer_4.setImageDrawable(ImgAns);
								UserAnswerArray[3] = answerValue;
								optionclicked = false;
								System.err.println("HERE IN ANSWER 4 CLICKED"
										+ removeindex);
							} else {
								answer_4.setImageDrawable(ImgAns);
								UserAnswerArray[3] = answerValue;
								optionclicked = false;
							}
						} else {

							if (answerclicked && removeindex != 3) {
								UserAnswerArray[removeindex] = 0;
								ClipData clip = event.getClipData();
								ClipData.Item item = clip.getItemAt(0);
								int id = getApplicationContext().getResources()
										.getIdentifier("red_question",
												"drawable", getPackageName());
								int id1 = getApplicationContext()
										.getResources().getIdentifier(
												item.getText().toString(),
												"id", getPackageName());
								System.err.println(id + "::" + id1);
								ImageView setimage;
								setimage = (ImageView) findViewById(id1);
								setimage.setImageResource(id);

							}
						}
					}

					return true;
				}
			});

			answer_1.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						optionclicked = false;
						answerclicked = true;
						answerValue = UserAnswerArray[0];
						ImgAns = answer_1.getDrawable();
						removeindex = 0;
						ClipData data = ClipData.newPlainText("Data",
								"answer_letter1");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
					} else {
						answerclicked = false;
					}
					return false;
				}
			});
			answer_2.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						optionclicked = false;
						answerclicked = true;
						answerValue = UserAnswerArray[1];
						ImgAns = answer_2.getDrawable();
						removeindex = 1;
						ClipData data = ClipData.newPlainText("Data",
								"answer_letter2");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
					} else {
						answerclicked = false;
					}
					return false;
				}
			});
			answer_3.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						optionclicked = false;
						answerclicked = true;
						answerValue = UserAnswerArray[2];
						ImgAns = answer_3.getDrawable();
						removeindex = 2;
						ClipData data = ClipData.newPlainText("Data",
								"answer_letter3");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
					} else {
						answerclicked = false;
					}
					return false;
				}
			});
			answer_4.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						optionclicked = false;
						answerclicked = true;
						answerValue = UserAnswerArray[3];
						ImgAns = answer_4.getDrawable();
						removeindex = 3;
						ClipData data = ClipData.newPlainText("Data",
								"answer_letter4");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
					} else {
						answerclicked = false;
					}
					return false;
				}
			});

			option_letter1.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter1.getDrawable();
						answerValue = 1;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});

			option_letter2.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter2.getDrawable();
						answerValue = 2;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});

			option_letter3.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter3.getDrawable();
						answerValue = 3;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});
			option_letter4.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter4.getDrawable();
						answerValue = 4;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});
			option_letter5.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter5.getDrawable();
						answerValue = 5;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});
			option_letter6.setOnTouchListener(new View.OnTouchListener() {

				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						ImgAns = option_letter6.getDrawable();
						answerValue = 6;
						optionclicked = true;
						answerclicked = false;
						ClipData data = ClipData.newPlainText("zz", "zz");

						v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
						System.out.println("HERE EEE");
					} else {

						optionclicked = false;
					}
					return false;
				}
			});

		} else {

			option_letter1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 1;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[0],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});

			option_letter2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 2;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[1],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});
			option_letter3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 3;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[2],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});
			option_letter4.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 4;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[3],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});
			option_letter5.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 5;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[4],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});
			option_letter6.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (checkanscount()) {
						UserAnswerArray[ansindex] = 6;
						ansindex++;
						int id = getApplicationContext().getResources()
								.getIdentifier(pz + "_" + valueArray[5],
										"drawable", getPackageName());
						int id1 = getApplicationContext().getResources()
								.getIdentifier(
										"answer_letter"
												+ String.valueOf(ansindex),
										"id", getPackageName());

						ImageView setimage;
						setimage = (ImageView) findViewById(id1);
						setimage.setImageResource(id);
					}
				}
			});

		}
		btn_GameScreenCheck = (TextView) findViewById(R.id.btn_GameScreenCheck);
		btn_GameScreenGiveUp = (TextView) findViewById(R.id.btn_GameScreenGiveUp);
		btn_GameScreenBack = (TextView) findViewById(R.id.btn_GameScreenBack);
		mContext = this;
		PuzzleString = "434200234121124111532111334230634230";
		PuzzleString = GlobalConfig.gp.getNextPuzzle();

		parsePuzzle(GAME_PIECE);

		/** SET on click Listeners **/

		if (GAME_TIME == -1) {
			game_timer.setText("Time: ∞");
		} else {
			mUpdateTimer(Sharedprefs.getInt("TIME_ELAPSED", 0));
		}
		btn_GameScreenCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!checkanscount()) {
					// timer.cancel();
					StringBuilder bs = new StringBuilder();
					for (int i = 0; i < 4; i++)
						bs.append(String.valueOf(UserAnswerArray[i]));
					StringBuilder bs1 = new StringBuilder();
					for (int i = 0; i < 6; i++)
						bs1.append(String.valueOf(valueArray[i]));
					Intent intent = new Intent(getApplicationContext(),
							PuzzleResult.class);
					intent.putExtra("UserAnswer", bs.toString());
					intent.putExtra("CorrectAnswer",
							PuzzleString.subSequence(0, 4));
					intent.putExtra("ValueArray", bs1.toString());
					intent.putExtra("UserAction", 1);
					intent.putExtra("PuzzleString", PuzzleString);
					intent.putExtra("PuzzlePiece", pz);
					startActivity(intent);
					finish();
				}

			}
		});

		btn_GameScreenGiveUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				StringBuilder bs1 = new StringBuilder();
				for (int i = 0; i < 6; i++)
					bs1.append(String.valueOf(valueArray[i]));
				Intent intent = new Intent(getApplicationContext(),
						PuzzleResult.class);
				intent.putExtra("CorrectAnswer", PuzzleString.subSequence(0, 4));
				intent.putExtra("ValueArray", bs1.toString());
				intent.putExtra("UserAction", 2);
				intent.putExtra("PuzzlePiece", pz);
				startActivity(intent);
				finish();
			}
		});

		btn_GameScreenBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				startActivity(new Intent(getApplicationContext(),
						GameSettings.class));
			}
		});

	}

	public void parsePuzzle(int PuzzlePiece) {
		// 434200234121124111532111334230634230
		// Parse code

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
		/*
		 * if (PuzzleString.length() == 36) { LinearLayout linlay_clue5 =
		 * (LinearLayout) findViewById(R.id.LinLay_clue5);
		 * linlay_clue5.setVisibility(View.VISIBLE); // 5 clues nclues = 5; }
		 * else { // 4Clues nclues = 4; }
		 */

		switch (PuzzlePiece) {
		case 1:
			pz = "letters1";
			break;

		case 2:
			pz = "letters2";
			break;

		case 3:
			pz = "thumb";
			break;
		}
		k = 6;
		// System.err.println(FIRST_LAUNCH);
		if (FIRST_LAUNCH) {
			Random rng = new Random(); // Ideally just create one instance
										// globally
			List<Integer> generated = new ArrayList<Integer>();
			for (int i = 0; i < 6; i++) {
				while (true) {
					Integer next = rng.nextInt(6) + 1;
					if (!generated.contains(next)) {
						// Done for this iteration
						generated.add(next);

						valueArray[i] = next;
						break;
					}
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			int id = getApplicationContext().getResources().getIdentifier(
					pz + "_" + valueArray[i], "drawable", getPackageName());

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
								+ valueArray[Integer.valueOf(PuzzleString
										.charAt(k)) - 49], "drawable",
						getPackageName());
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

	}

	public int getDrawableId(View iv) {
		return (Integer) iv.getTag();
	}

	private boolean processDrop(DragEvent event) {
		ClipData data = event.getClipData();
		if (data != null) {
			if (data.getItemCount() > 0) {
				Item item = data.getItemAt(0);
				String textData = (String) item.getText();

				return true;
			}
		}
		return false;
	}

	public void mUpdateTimer(int i) {
		timer = new Timer();
		gamesecs = i;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Message msg = Message.obtain(handler, gamesecs);
				handler.sendMessage(msg);
				gamesecs++;
			}
		};
		timer.schedule(task, 0, 1000);

	}

	public static String convertSecs(int secs) {

		return String.valueOf((int) (secs / 60) + ":" + (secs % 60));

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			timer.cancel();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Sharedprefs_edit = Sharedprefs.edit();
		Sharedprefs_edit.putInt("TIME_ELAPSED", gamesecs);
		Sharedprefs_edit.putBoolean("FIRST_LAUNCH", false);
		Sharedprefs_edit.commit();
	}

	public void setAnswer(int lettervalue) {
		UserAnswerArray[ansindex] = 1;
		ansindex++;
		int id = getApplicationContext().getResources().getIdentifier(
				pz + "_" + lettervalue, "drawable", getPackageName());
		int id1 = getApplicationContext().getResources().getIdentifier(
				"answer_letter" + String.valueOf(ansindex), "id",
				getPackageName());

		ImageView setimage;
		setimage = (ImageView) findViewById(id1);
		setimage.setImageResource(id);
	}

	public Boolean checkanscount() {
		anscount = 1;
		for (int i = 0; i < 4; i++) {
			if (UserAnswerArray[i] > 0) {
				anscount++;
			}
		}
		System.err.println(anscount);
		if (anscount > 4)
			return false;
		else
			return true;
	}
}
