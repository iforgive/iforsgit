package com.winca.info;

public class TVInfo {

	public static final String TV_STATUS_CHANGE_ACTION = "com.winca.service.tv.tvinfochange";

	public static final int TV_MODLE_TYPE_ANALOG = 0X0;
	public static final int TV_MODLE_TYPE_DIGIT = 0X01;
	public static final int TV_MODLE_TYPE_CMMB = 0X02;
	public static final int TV_MODLE_TYPE_ISDB_T = 0X03;
	public static final int TV_MODLE_TYPE_DVB_T2 = 0X04;

	public static final int IMAGE_FORMAT_PAL = 0X01;
	public static final int IMAGE_FORMAT_NTSC = 0X02;
	public static final int IMAGE_FORMAT_SECAM = 0X03;
	public static final int IMAGE_FORMAT_PAL_M = 0X04;
	public static final int IMAGE_FORMAT_PAL_N = 0X05;

	public static final int VOICE_FORMAT_DK = 0X01;
	public static final int VOICE_FORMAT_I = 0X02;
	public static final int VOICE_FORMAT_BG = 0X03;
	public static final int VOICE_FORMAT_M = 0X04;
	public static final int VOICE_FORMAT_N = 0X05;
	public static final int VOICE_FORMAT_L = 0X06;

	public static final int TV_BAND_VLF = 0X01;
	public static final int TV_BAND_VHF = 0X02;
	public static final int TV_BAND_UHF = 0X03;

	private int mTVModleType = TV_MODLE_TYPE_ANALOG;
	private int mCurrentChannelId = 0;
	private int mCurrentFrequency = 0;
	private int mImageFormat = IMAGE_FORMAT_PAL;
	private int mVoiceFormat = VOICE_FORMAT_BG;
	private int mSearchingFrequency = 0;
	private int mSearchingBand = TV_BAND_VHF;
	private int mSearchingprogress = 0;
	private int mTotalChannelNum = 0;
	private int mCurrentChannelNum = 0;
	private boolean isTVSearch = false;

	public TVInfo() {
	}

	public int getmTVModleType() {
		return mTVModleType;
	}

	public void setmTVModleType(int mTVModleType) {
		this.mTVModleType = mTVModleType;
	}

	public int getmCurrentChannelId() {
		return mCurrentChannelId;
	}

	public void setmCurrentChannelId(int mCurrentChannelId) {
		this.mCurrentChannelId = mCurrentChannelId;
	}

	public int getmCurrentFrequency() {
		return mCurrentFrequency;
	}

	public void setmCurrentFrequency(int mCurrentFrequency) {
		this.mCurrentFrequency = mCurrentFrequency;
	}

	public int getmImageFormat() {
		return mImageFormat;
	}

	public void setmImageFormat(int mImageFormat) {
		this.mImageFormat = mImageFormat;
	}

	public int getmVoiceFormat() {
		return mVoiceFormat;
	}

	public void setmVoiceFormat(int mVoiceFormat) {
		this.mVoiceFormat = mVoiceFormat;
	}

	public int getmSearchingFrequency() {
		return mSearchingFrequency;
	}

	public void setmSearchingFrequency(int mSearchingFrequency) {
		this.mSearchingFrequency = mSearchingFrequency;
	}

	public int getmSearchingBand() {
		return mSearchingBand;
	}

	public void setmSearchingBand(int mSearchingBand) {
		this.mSearchingBand = mSearchingBand;
	}

	public int getmSearchingprogress() {
		return mSearchingprogress;
	}

	public void setmSearchingprogress(int mSearchingprogress) {
		this.mSearchingprogress = mSearchingprogress;
	}

	public int getmTotalChannelNum() {
		return mTotalChannelNum;
	}

	public void setmTotalChannelNum(int mTotalChannelNum) {
		this.mTotalChannelNum = mTotalChannelNum;
	}

	public int getCurrentChannelNum() {
		return mCurrentChannelNum;
	}

	public void setCurrentChannelNum(int currentChannelNum) {
		this.mCurrentChannelNum = currentChannelNum;
	}

	public boolean isTVSearch() {
		return isTVSearch;
	}

	public void setTVSearch(boolean isTVSearch) {
		this.isTVSearch = isTVSearch;
	}

}
