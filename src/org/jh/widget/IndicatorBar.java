/*
 * Copyright (C) 2013 jh
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jh.widget;

import com.example.scorlltabbar.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Scroller;


/**
 * IndicatorBar is a slide for android Toolbar
 * 
 * @author jh
 *
 */
public class IndicatorBar extends View implements IndictorSwitcher {

	private Bitmap mBitmap;

	private Paint mPaint;

	private int mWidth;

	private int mPrePosition;

	private Scroller mScroller;

	private int mIndicNum;

	private static final int DEFAULT_INDIC_NUM = 4; 

	private static final int DEFAULT_INDIC_SPEED = 1000;

	public IndicatorBar(Context context, AttributeSet attrs) {

		this(context, attrs, 0);

	}

	public IndicatorBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initData();

		mScroller = new Scroller(context);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.Indicator, defStyle, 0);

		Drawable mDrawable = a.getDrawable(R.styleable.Indicator_android_src);
		
		if(mDrawable != null){

			BitmapDrawable bd = (BitmapDrawable) mDrawable;

			mBitmap = bd.getBitmap();
			
		}

		mIndicNum = a.getInteger(R.styleable.Indicator_indicCount,
				DEFAULT_INDIC_NUM);

		a.recycle();

	}

	public IndicatorBar(Context context) {
		super(context);
	}

	/**
	 * data init
	 */
	private void initData() {

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		DisplayMetrics dm = new DisplayMetrics();

		dm = getResources().getDisplayMetrics();

		mWidth = dm.widthPixels;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (mBitmap == null)

			return;

		canvas.drawBitmap(mBitmap,
				(mWidth / mIndicNum - mBitmap.getWidth()) / 2, 0, mPaint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	/**
	 * Determines the width of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Calculate the width according the views count
		else {
			result = (int) (getPaddingLeft() + getPaddingRight() + mWidth);
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/*
	 * Determines the height of this view
	 * 
	 * @param measureSpec A measureSpec packed into an int
	 * 
	 * @return The height of the view, honoring constraints from measureSpec
	 */
	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Measure the height
		else {
			result = (int) (mBitmap.getHeight() + getPaddingTop()
					+ getPaddingBottom() + 1);
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	@Override
	public void switchView(int position) {

		if (position == mPrePosition)

			return;

		mScroller.abortAnimation();

		int scrollX = mWidth / mIndicNum * (mPrePosition - position);

		mScroller.startScroll(-mPrePosition * mWidth / mIndicNum, 0, scrollX,
				0, DEFAULT_INDIC_SPEED);

		mPrePosition = position;

		invalidate();

	}

	@Override
	public void computeScroll() {
		super.computeScroll();

		if (mScroller.computeScrollOffset()) {

			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

			postInvalidate();
		}
	}

}
