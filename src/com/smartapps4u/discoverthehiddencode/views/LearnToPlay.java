package com.smartapps4u.discoverthehiddencode.views;

import com.smartapps4u.discoverthehiddencode.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LearnToPlay extends Activity {
	ImageView btnNext, btnPrev;
	TextView btnMainMenu;
	ImageView imgTUT;
	int index = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learntoplay_layout);
		imgTUT = (ImageView) findViewById(R.id.imgTUT);
		btnNext = (ImageView) findViewById(R.id.btnNextLTP);
		btnPrev = (ImageView) findViewById(R.id.btnPreviousLTP);
		btnMainMenu = (TextView) findViewById(R.id.btnHome);
		btnPrev.setVisibility(View.INVISIBLE);
		btnNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnPrev.setVisibility(View.VISIBLE);
				index++;
				int id = getApplicationContext().getResources().getIdentifier(
						"manual" + index, "drawable", getPackageName());
				imgTUT.setImageResource(id);
				if (index == 5) {
					btnNext.setVisibility(View.INVISIBLE);
				}

			}
		});
		btnPrev.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index--;
				btnNext.setVisibility(View.VISIBLE);
				int id = getApplicationContext().getResources().getIdentifier(
						"manual" + index, "drawable", getPackageName());
				imgTUT.setImageResource(id);
				if (index == 1) {
					btnPrev.setVisibility(View.INVISIBLE);
				}

			}
		});
		btnMainMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
