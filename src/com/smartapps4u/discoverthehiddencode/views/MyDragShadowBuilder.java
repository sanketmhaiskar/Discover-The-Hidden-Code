package com.smartapps4u.discoverthehiddencode.views;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.DragShadowBuilder;

/**
 * This is used to create the floating view and optionally you could add a
 * shadow to the floating view
 * 
 * @author bibek
 */
@SuppressLint("NewApi")
public class MyDragShadowBuilder extends DragShadowBuilder {
	private Drawable mShadow;

	public MyDragShadowBuilder(View v) {

		super(v);

	}

	@Override
	public void onDrawShadow(Canvas canvas) {
		super.onDrawShadow(canvas);

		getView().draw(canvas);
	}
}