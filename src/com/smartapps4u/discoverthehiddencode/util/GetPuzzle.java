package com.smartapps4u.discoverthehiddencode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;

public class GetPuzzle {

	private int gamedifficulty = 0;
	private Context mContext;
	AssetManager am;
	InputStream inputstream = null;
	InputStreamReader inputStreamReader;
	BufferedReader bufferedReader;

	String line = null;

	public GetPuzzle(Context mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;

		try {
			am = mContext.getResources().getAssets();
			inputstream = am.open("puzzles.txt");
			inputStreamReader = new InputStreamReader(inputstream);
			bufferedReader = new BufferedReader(inputStreamReader);

			line = null;
			while ((line = bufferedReader.readLine()) != null) {
				GlobalConfig.puzzles.add(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setGamedifficuly(int gamedifficulty) {
		this.gamedifficulty = gamedifficulty;
	}

	public String getNextPuzzle() {

		while (true) {
			if (GlobalConfig.puzzleindex >= GlobalConfig.puzzles.size())
				GlobalConfig.puzzleindex = 0;
			line = GlobalConfig.puzzles.get(GlobalConfig.puzzleindex)
					.toString();

			if (getpuzzletype(line) == gamedifficulty) {
				GlobalConfig.puzzleindex++;
				return line;
			}
			GlobalConfig.puzzleindex++;
		}
	}

	public int getpuzzletype(String puzzle) {
		System.err.println(puzzle + "::" + GlobalConfig.puzzleindex + "::"
				+ GlobalConfig.puzzles.size());
		Boolean easyFlag = false;
		int i = 10;
		int rcount = 0, wcount = 0;
		while (i < puzzle.length()) {
			rcount = 0;
			wcount = 0;
			if (Integer.valueOf(puzzle.charAt(i) - '0') == 3) {
				easyFlag = true;
				break;
			}
			rcount = Integer.valueOf(puzzle.charAt(i) - '0');
			wcount = Integer.valueOf(puzzle.charAt(i + 1) - '0');
			if (rcount + wcount == 4) {

				easyFlag = true;
				break;
			}
			i = i + 6;
		}
		/*
		 * System.err.println("RCOUNT::WCOUNT" + rcount + "::" + wcount); if
		 * ((rcount == 4) || (wcount == 4)) { easyFlag = true; }
		 */
		System.err.println("EASYFLAG::" + easyFlag);
		if (easyFlag)
			return 0;
		else
			return 1;
	}
}
