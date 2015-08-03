package com.winca.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;

public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MarqueeTextView(Context context) {
		this(context, null);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		//return super.isFocused();
		return true;
	}

	@Override
	@ExportedProperty
	public boolean isClickable() {
		// TODO Auto-generated method stub
		//return super.isClickable();
		return true;
	}

	@Override
	@ExportedProperty
	public boolean isInTouchMode() {
		// TODO Auto-generated method stub
		//return super.isInTouchMode();
		return true;
	}

}
