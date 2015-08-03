package com.winca.info;

import android.util.Log;

public class MpegInfo {

	public static final int MPEG_TYPE_CD = 0X01;
	public static final int MPEG_TYPE_DVD_AUDIO = 0X02;
	public static final int MPEG_TYPE_DVD_VIDEO = 0X03;

	public static int mpegType = -1;
	public static int mpegPlayStatus = -1;
	public static int mpegMenuType = -1;
	public static String mTimeStringCurrent = "00:00";
	public static String mTimeStringTotal = "00:00";
	public static int mCurrentTimeSecounds = 0;
	public static int mTotalTimeSecounds = 0;
	public static int mTrackCurrent = 0;
	public static int mTrackTotal = 0;
	public static int mRepeatType = -1;
	public static int mRandomType = -1;
	public static boolean mHandBreak = false;
	public static boolean isDiscExist = true;
	public static MpegInfo mMpegInfo = null;

	public MpegInfo() {

	}

	public void mpegInfoInit() {
		mpegType = MPEG_TYPE_DVD_VIDEO;
		mpegPlayStatus = -1;
		mpegMenuType = -1;
		mTimeStringCurrent = "00:00";
		mTimeStringTotal = "00:00";
		mTrackCurrent = 0;
		mTrackTotal = 0;
		mRepeatType = -1;
		mRandomType = -1;
		mCurrentTimeSecounds = 0;
		mTotalTimeSecounds = 0;
		mHandBreak = false;
	}

	public static MpegInfo getMpegInfo() {
		if (mMpegInfo == null) {
			mMpegInfo = new MpegInfo();
		}
		return mMpegInfo;
	}

	public int getMpegType() {
		return mpegType;
	}

	public void setMpegType(int mpegType) {
		Log.d("hua", "mpegType = " + mpegType);
		this.mpegType = mpegType;
	}

	public int getMpegPlayStatus() {
		return mpegPlayStatus;
	}

	public void setMpegPlayStatus(int mpegPlayStatus) {
		this.mpegPlayStatus = mpegPlayStatus;
	}

	public int getMpegMenuType() {
		return mpegMenuType;
	}

	public void setMpegMenuType(int mpegMenuType) {
		this.mpegMenuType = mpegMenuType;
	}

	public String getmTimeStringCurrent() {
		return mTimeStringCurrent;
	}

	public void setmTimeStringCurrent(String mTimeStringCurrent) {
		this.mTimeStringCurrent = mTimeStringCurrent;
	}

	public String getmTimeStringTotal() {
		return mTimeStringTotal;
	}

	public void setmTimeStringTotal(String mTimeStringTotal) {
		this.mTimeStringTotal = mTimeStringTotal;
	}

	public int getmCurrentTimeSecounds() {
		return mCurrentTimeSecounds;
	}

	public void setmCurrentTimeSecounds(int mCurrentTimeSecounds) {
		this.mCurrentTimeSecounds = mCurrentTimeSecounds;
	}

	public int getmTotalTimeSecounds() {
		return mTotalTimeSecounds;
	}

	public void setmTotalTimeSecounds(int mTotalTimeSecounds) {
		this.mTotalTimeSecounds = mTotalTimeSecounds;
	}

	public int getmTrackCurrent() {
		return mTrackCurrent;
	}

	public void setmTrackCurrent(int mTrackCurrent) {
		this.mTrackCurrent = mTrackCurrent;
	}

	public int getmTrackTotal() {
		return mTrackTotal;
	}

	public void setmTrackTotal(int mTrackTotal) {
		this.mTrackTotal = mTrackTotal;
	}

	public int getmRepeatType() {
		return mRepeatType;
	}

	public void setmRepeatType(int mRepeatType) {
		this.mRepeatType = mRepeatType;
	}

	public int getmRandomType() {
		return mRandomType;
	}

	public void setmRandomType(int mRandomType) {
		this.mRandomType = mRandomType;
	}

	public boolean ismHandBreak() {
		return mHandBreak;
	}

	public void setmHandBreak(boolean mHandBreak) {
		this.mHandBreak = mHandBreak;
	}

	public boolean isDiscExist() {
		return isDiscExist;
	}

	public void setDiscExist(boolean isDiscExist) {
		this.isDiscExist = isDiscExist;
	}

}
