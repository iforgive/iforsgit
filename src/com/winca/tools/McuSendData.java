package com.winca.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;

import com.android.internal.wincamcu.IWincaMcu;

public class McuSendData {

	private static final boolean DEBUG = true;
	private static final String TAG = "McuSendData";

	public static final String PATCH_HD = "/mnt/internal_sd";
	public static final String PATCH_GPS = "/mnt/external_sd0";
	public static final String PATCH_SD = "/mnt/external_sd1";
	public static final String PATCH_USB0 = "/mnt/usb_storage0";
	public static final String PATCH_USB1 = "/mnt/usb_storage1";

	public static final String PAGE = "Page";
	public static final String PAGE_FACTORYSETTING = "FactoryMode";
	public static final String PAGE_REARZONE = "RearZone";
	public static final String PAGE_SETTINGBOOTLOGO = "SettingBootLogo";
	public static final String PAGE_SETTINGKEYCOLOR = "SettingKeyColor";
	public static final String PAGE_SETTING = "Setting";
	public static final String PAGE_SOUNDDSP = "SoundDSP";
	public static final String PAGE_SOUNDEQ = "SoundEQ";
	public static final String PAGE_SOUND = "Sound";
	public static final String PAGE_SOUNDVOLUME = "SoundVolume";
	public static final String PAGE_SWC = "Swc";

	public static final int RADIO_BAND_FM = 0x00;
	public static final int RADIO_BAND_AM = 0x01;

	public static final int USB_MODLE_11 = 1;
	public static final int USB_MODLE_20 = 2;

	public static final int VIDEO_MODE_CLOSE = 0;
	public static final int VIDEO_MODE_OPEN_DVD = 1;
	public static final int VIDEO_MODE_OPEN_NTSC = 10;
	public static final int VIDEO_MODE_OPEN_PAL = 20;

	public static final int MCU_VIDEO_TYPE_NTSC = 1;
	public static final int MCU_VIDEO_TYPE_PAL = 2;

	public static final boolean AUTO_HIDE = true;
	public static final int AUTO_HIDE_DELAY_MILLIS = 5000;

	public static final int[] play = { 0x0c, 0x00, 0x0b };
	// public static final int[] play = { 0xff, 0xff, 0x0b };
	public static final int[] black = { 0x00, 0x00, 0x00 };

	private static McuSendData mMcuSendData;
	public static IWincaMcu mService;

	public static final byte Data1 = 0x41;

	public static final byte systemData2 = 0x01;
	public static final byte fmData2 = 0x02;
	public static final byte discData2 = 0x06;
	public static final byte musicData2 = 0x08;
	public static final byte tvData2 = 0x0e;
	public static final byte tvAnalogData2 = 0x0f;
	public static final byte CarDVRSData2 = 0x12;
	public static final byte carSetData2 = 0x20;
	public static final byte factoryData2 = 0x21;
	public static final byte directionData2 = 0x22;
	public static final byte rearZoneData2 = 0x23;

	// mcu channel id
	public static final int MCU_CHANNEL_FM = 0x01;
	public static final int MCU_CHANNEL_DVD = 0x02;
	public static final int MCU_CHANNEL_NAVI = 0x03;
	public static final int MCU_CHANNEL_BT_PHONE = 0x04;
	public static final int MCU_CHANNEL_AUX1 = 0x05;
	public static final int MCU_CHANNEL_AUX2 = 0x06;
	public static final int MCU_CHANNEL_MPUVIDIO = 0x07;
	public static final int MCU_CHANNEL_MPUAUDIO = 0x08;
	public static final int MCU_CHANNEL_TV = 0x0a;
	public static final int MCU_CHANNEL_IPOD = 0x0C;
	public static final int MCU_CHANNEL_BT_AUDIO = 0x0D;
	public static final int MCU_CHANNEL_APP = 0x0E;
	public static final int MCU_CHANNEL_CAR_DVR = 0x0F;
	public static final int MCU_CHANNEL_ORIGINAL_CAR = 0x10;

	// mcu menu id
	public static final int MENU_NULL = 0x00;
	public static final int MENU_RADIO = 0x02;
	public static final int MENU_BT = 0x03;
	public static final int MENU_DVD = 0x04;
	public static final int MENU_TV = 0x07;
	public static final int MENU_AUX1 = 0x08;
	public static final int MENU_AUX2 = 0x09;
	public static final int MENU_IPOD = 0x0B;
	public static final int MENU_VIDEOPLAY = 0x0C;
	public static final int MENU_AUDIOPLAY = 0x0D;
	public static final int MENU_DVR = 0x0E;
	public static final int MENU_FORCED_BACKCAR = 0x12;
	public static final int MENU_NAVI = 0x15;
	public static final int MENU_SWC = 0x17;
	public static final int MENU_ENGINEER_MODE = 0x1C;
	public static final int MENU_CAR_INFO = 0x30;

	public static final int VIDEO_TYPE_NTSC = 1;
	public static final int VIDEO_TYPE_PAL = 2;

	public static final byte[] DATA_QUREY_RADIO_INFO = { 0x03, 0x01 };
	public static final byte[] DATA_QUREY_DISC_INFO = { 0x03, 0x02 };
	public static final byte[] DATA_QUREY_TV_INFO = { 0x03, 0x03 };
	public static final byte[] DATA_QUREY_CARSETTING_INFO = { 0x03, 0x04 };
	public static final byte[] DATA_QUREY_FACTORY_MODE_INFO = { 0x03, 0x05 };
	public static final byte[] DATA_QUREY_DIRECTION_INFO = { 0x03, 0x06 };
	public static final byte[] DATA_QUREY_REGIONAL_INFO = { 0x03, 0x07 };
	public static final byte[] DATA_QUREY_VERSION_INFO = { 0x03, 0x08 };

	// === system ===
	public static final byte[] SYSTEM_DATA_OFF_SCREEN = { Data1, systemData2,
			0x02 };
	public static final byte[] SYSTEM_DATA_ON_SCREEN = { Data1, systemData2,
		0x01 };

	// === fm ===
	public static final byte[] FM_DATA_FM = { Data1, fmData2, 0x00 };
	public static final byte[] FM_DATA_AM = { Data1, fmData2, 0x01 };
	public static final byte[] FM_DATA_PREV = { Data1, fmData2, 0x23 };
	public static final byte[] FM_DATA_NEXT = { Data1, fmData2, 0x24 };

	// === CAR_SET ===
	public static final byte[] CAR_SET_DATA_SOUND_MODE = { Data1, carSetData2,
			0x01 };
	public static final byte[] CAR_SET_DATA_HIGH_SOUND = { Data1, carSetData2,
			0x02 };
	public static final byte[] CAR_SET_DATA_IN_SOUND = { Data1, carSetData2,
			0x03 };
	public static final byte[] CAR_SET_DATA_LOW_SOUND = { Data1, carSetData2,
			0x04 };
	public static final byte[] CAR_SET_DATA_HEAVY_LOW_SOUND = { Data1,
			carSetData2, 0x05 };
	public static final byte[] CAR_SET_DATA_BEFORE = { Data1, carSetData2, 0x06 };
	public static final byte[] CAR_SET_DATA_FADER = { Data1, carSetData2, 0x07 };
	public static final byte[] CAR_SET_DATA_VOLUME = { Data1, carSetData2, 0x08 };
	public static final byte[] CAR_SET_DATA_LOUDNESS = { Data1, carSetData2,
			0x09 };
	public static final byte[] CAR_SET_DATA_VOICE = { Data1, carSetData2, 0x0a };
	public static final byte[] CAR_SET_DATA_VOICE_RESET = { Data1, carSetData2,
			0x0b };
	public static final byte[] CAR_SET_DATA_VIDEO_MODE = { Data1, carSetData2,
			0x0c };
	public static final byte[] CAR_SET_DATA_VIDEO_BRIGHTNESS = { Data1,
			carSetData2, 0x0d };
	public static final byte[] CAR_SET_DATA_VIDEO_CONTRAST = { Data1,
			carSetData2, 0x0e };
	public static final byte[] CAR_SET_DATA_VIDEO_CHROMA = { Data1,
			carSetData2, 0x0f };
	public static final byte[] CAR_SET_DATA_INIT_LOGO = { Data1, carSetData2,
			0x10 };
	public static final byte[] CAR_SET_DATA_VOICE_NAVIGATION = { Data1,
			carSetData2, 0x11 };
	public static final byte[] CAR_SET_DATA_KEY_SOUND = { Data1, carSetData2,
			0x12 };
	public static final byte[] CAR_SET_DATA_KEY_COLOR = { Data1, carSetData2,
			0x13 };
	public static final byte[] CAR_SET_DATA_BACKLIGHT = { Data1, carSetData2,
			0x14 };
	public static final byte[] CAR_SET_DATA_LAMP_CHECK = { Data1, carSetData2,
			0x15 };
	public static final byte[] CAR_SET_DATA_HANDBRAKE_CHECK = { Data1,
			carSetData2, 0x16 };
	public static final byte[] CAR_SET_DATA_REVERSING_CHECK = { Data1,
			carSetData2, 0x17 };
	public static final byte[] CAR_SET_DATA_REVERSING_MUTE = { Data1,
			carSetData2, 0x18 };
	public static final byte[] CAR_SET_DATA_REVERSING_BOOT = { Data1,
			carSetData2, 0x19 };
	public static final byte[] CAR_SET_DATA_ACC_AOTO_VIDEO = { Data1,
			carSetData2, 0x1a };
	public static final byte[] CAR_SET_DATA_BOOT_AOTO_VIDEO = { Data1,
			carSetData2, 0x1b };
	public static final byte[] CAR_SET_DATA_KEY_COLOR_CUSTOM = { Data1,
			carSetData2, 0x1c };
	public static final byte[] CAR_SET_DATA_REVERSING_TRAJECTORY = { Data1,
			carSetData2, 0x1d };
	public static final byte[] CAR_SET_DATA_INIT_SETTING = { Data1,
			carSetData2, 0x30 };
	public static final byte[] CAR_SET_DATA_EXIT = { Data1, carSetData2, 0x40 };
	public static final byte[] CAR_SET_DATA_RESET_BOOT = { Data1, carSetData2,
			0x41 };

	public static final byte[] CAR_SET_DATA_BGAIN = { Data1, carSetData2,
			(byte) 0x80 };
	public static final byte[] CAR_SET_DATA_DSP = { Data1, carSetData2,
			(byte) 0x81 };
	public static final byte[] CAR_SET_DATA_EQ = { Data1, carSetData2,
			(byte) 0x82 };
	public static final byte[] CAR_SET_DATA_SRS = { Data1, carSetData2,
			(byte) 0x83 };
	public static final byte[] CAR_SET_DATA_SRS_RESET = { Data1, carSetData2,
			(byte) 0x86 };

	// === music ===
	public static final byte[] MUSIC_DATA_STOP = { Data1, musicData2, 0x01,
			0x00 };
	public static final byte[] MUSIC_DATA_PLAY = { Data1, musicData2, 0x01,
			0x02 };
	public static final byte[] MUSIC_DATA_PAUSE = { Data1, musicData2, 0x01,
			0x08 };
	public static final byte[] MUSIC_DATA_TIME = { Data1, musicData2, 0x02 };
	public static final byte[] MUSIC_DATA_TRACK = { Data1, musicData2, 0x03 };
	public static final byte[] MUSIC_DATA_PLAY_STATUS = { Data1, musicData2,
			0x04 };

	// === disc ===
	public static final byte[] DISC_DATA_00 = { Data1, discData2, 0x00 };
	public static final byte[] DISC_DATA_01 = { Data1, discData2, 0x01 };
	public static final byte[] DISC_DATA_02 = { Data1, discData2, 0x02 };
	public static final byte[] DISC_DATA_03 = { Data1, discData2, 0x03 };
	public static final byte[] DISC_DATA_04 = { Data1, discData2, 0x04 };
	public static final byte[] DISC_DATA_05 = { Data1, discData2, 0x05 };
	public static final byte[] DISC_DATA_06 = { Data1, discData2, 0x06 };
	public static final byte[] DISC_DATA_07 = { Data1, discData2, 0x07 };
	public static final byte[] DISC_DATA_08 = { Data1, discData2, 0x08 };
	public static final byte[] DISC_DATA_09 = { Data1, discData2, 0x09 };
	public static final byte[] DISC_DATA_FORWARD = { Data1, discData2, 0x0a };
	public static final byte[] DISC_DATA_RETREAT = { Data1, discData2, 0x0b };
	public static final byte[] DISC_DATA_PREV = { Data1, discData2, 0x0c };
	public static final byte[] DISC_DATA_NEXT = { Data1, discData2, 0x0d };
	public static final byte[] DISC_DATA_PLAY = { Data1, discData2, 0x0e };
	public static final byte[] DISC_DATA_STOP = { Data1, discData2, 0x0f };
	public static final byte[] DISC_DATA_REPEAT = { Data1, discData2, 0x10 };
	public static final byte[] DISC_DATA_RANDOM = { Data1, discData2, 0x11 };
	public static final byte[] DISC_DATA_PCB = { Data1, discData2, 0x12 };
	public static final byte[] DISC_DATA_ZOOM = { Data1, discData2, 0x13 };
	public static final byte[] DISC_DATA_SCREENSHOT = { Data1, discData2, 0X14 };
	public static final byte[] DISC_DATA_INFO = { Data1, discData2, 0x15 };
	public static final byte[] DISC_DATA_EJECT = { Data1, discData2, 0x16 };
	public static final byte[] DISC_DATA_SETTING = { Data1, discData2, 0x17 };
	public static final byte[] DISC_DATA_BACK = { Data1, discData2, 0x18 };
	public static final byte[] DISC_DATA_AB = { Data1, discData2, 0x19 };
	public static final byte[] DISC_DATA_AUDIO = { Data1, discData2, 0x1a };
	public static final byte[] DISC_DATA_ANGLE = { Data1, discData2, 0x1b };
	public static final byte[] DISC_DATA_SUB_T = { Data1, discData2, 0x1c };
	public static final byte[] DISC_DATA_10_ADD = { Data1, discData2, 0x1d };
	public static final byte[] DISC_DATA_OK = { Data1, discData2, 0x1e };
	public static final byte[] DISC_DATA_GOTO = { Data1, discData2, 0x1f };
	public static final byte[] DISC_DATA_UP = { Data1, discData2, 0x20 };
	public static final byte[] DISC_DATA_DOWN = { Data1, discData2, 0x21 };
	public static final byte[] DISC_DATA_LEFT = { Data1, discData2, 0x22 };
	public static final byte[] DISC_DATA_RIGHT = { Data1, discData2, 0x23 };
	public static final byte[] DISC_DATA_TITLE_MENU = { Data1, discData2, 0x24 };
	public static final byte[] DISC_DATA_SELECT_TRACKS = { Data1, discData2,
			0X3A };
	public static final byte[] DISC_DATA_JUMP = { Data1, discData2, 0x3b };
	public static final byte[] DISC_DATA_EXIT = { Data1, discData2, 0x40 };

	// === tv ===
	public static final byte[] TV_DIGIT_NUM_00 = { Data1, tvData2, 0x00 };
	public static final byte[] TV_DIGIT_NUM_01 = { Data1, tvData2, 0x01 };
	public static final byte[] TV_DIGIT_NUM_02 = { Data1, tvData2, 0x02 };
	public static final byte[] TV_DIGIT_NUM_03 = { Data1, tvData2, 0x03 };
	public static final byte[] TV_DIGIT_NUM_04 = { Data1, tvData2, 0x04 };
	public static final byte[] TV_DIGIT_NUM_05 = { Data1, tvData2, 0x05 };
	public static final byte[] TV_DIGIT_NUM_06 = { Data1, tvData2, 0x06 };
	public static final byte[] TV_DIGIT_NUM_07 = { Data1, tvData2, 0x07 };
	public static final byte[] TV_DIGIT_NUM_08 = { Data1, tvData2, 0x08 };
	public static final byte[] TV_DIGIT_NUM_09 = { Data1, tvData2, 0x09 };

	public static final byte[] TV_DIGIT_POW = { Data1, tvData2, 0x0A };
	public static final byte[] TV_DIGIT_MUTE = { Data1, tvData2, 0x0B };
	public static final byte[] TV_DIGIT_UP = { Data1, tvData2, 0x0C };
	public static final byte[] TV_DIGIT_DOWN = { Data1, tvData2, 0x0D };
	public static final byte[] TV_DIGIT_LEFT = { Data1, tvData2, 0x0E };
	public static final byte[] TV_DIGIT_RIGHT = { Data1, tvData2, 0x0F };
	public static final byte[] TV_DIGIT_OK = { Data1, tvData2, 0x10 };
	public static final byte[] TV_DIGIT_MENU = { Data1, tvData2, 0x11 };
	public static final byte[] TV_DIGIT_EXIT = { Data1, tvData2, 0x12 };
	public static final byte[] TV_DIGIT_RECALL = { Data1, tvData2, 0x13 };
	public static final byte[] TV_DIGIT_RED = { Data1, tvData2, 0x14 };
	public static final byte[] TV_DIGIT_GREEN = { Data1, tvData2, 0x15 };
	public static final byte[] TV_DIGIT_YELLOW = { Data1, tvData2, 0x16 };
	public static final byte[] TV_DIGIT_BLUE = { Data1, tvData2, 0x17 };
	public static final byte[] TV_DIGIT_INFO = { Data1, tvData2, 0x18 };
	public static final byte[] TV_DIGIT_EPG = { Data1, tvData2, 0x19 };
	public static final byte[] TV_DIGIT_AUDIO = { Data1, tvData2, 0x1A };
	public static final byte[] TV_DIGIT_TTXT = { Data1, tvData2, 0x1B };
	public static final byte[] TV_DIGIT_SUBT = { Data1, tvData2, 0x1C };
	public static final byte[] TV_DIGIT_FAV = { Data1, tvData2, 0x1D };
	public static final byte[] TV_DIGIT_TV_RADIO = { Data1, tvData2, 0x1E };
	public static final byte[] TV_DIGIT_TV_SCAN = { Data1, tvData2, 0x1F };

	public static final byte[] TV_ANALOG_NUM_00 = { Data1, tvAnalogData2, 0x00 };
	public static final byte[] TV_ANALOG_NUM_01 = { Data1, tvAnalogData2, 0x01 };
	public static final byte[] TV_ANALOG_NUM_02 = { Data1, tvAnalogData2, 0x02 };
	public static final byte[] TV_ANALOG_NUM_03 = { Data1, tvAnalogData2, 0x03 };
	public static final byte[] TV_ANALOG_NUM_04 = { Data1, tvAnalogData2, 0x04 };
	public static final byte[] TV_ANALOG_NUM_05 = { Data1, tvAnalogData2, 0x05 };
	public static final byte[] TV_ANALOG_NUM_06 = { Data1, tvAnalogData2, 0x06 };
	public static final byte[] TV_ANALOG_NUM_07 = { Data1, tvAnalogData2, 0x07 };
	public static final byte[] TV_ANALOG_NUM_08 = { Data1, tvAnalogData2, 0x08 };
	public static final byte[] TV_ANALOG_NUM_09 = { Data1, tvAnalogData2, 0x09 };

	public static final byte[] TV_ANALOG_CHENNAL_DESC = { Data1, tvAnalogData2,
			0x0A };
	public static final byte[] TV_ANALOG_CHENNAL_ADD = { Data1, tvAnalogData2,
			0x0B };
	public static final byte[] TV_ANALOG_SEARCH = { Data1, tvAnalogData2, 0x0D };
	public static final byte[] TV_ANALOG_STEP_DOWN = { Data1, tvAnalogData2,
			0x0E };
	public static final byte[] TV_ANALOG_STEP_UP = { Data1, tvAnalogData2, 0x0F };
	public static final byte[] TV_ANALOG_SEARCH_STOP = { Data1, tvAnalogData2,
			0x10 };
	public static final byte[] TV_ANALOG_SEARCH_SIGNAL = { Data1, tvAnalogData2,
		0x12, 0x01 };
	public static final byte[] TV_ANALOG_SEARCH_NO_SIGNAL = { Data1, tvAnalogData2,
		0x12, 0x00 };
	// === factory ===
	public static final byte[] FACTORY_DATA_ARM_VERSION = { Data1,
			factoryData2, 0x00 };
	public static final byte[] FACTORY_DATA_RADIO_REGIONAL = { Data1,
			factoryData2, 0x01 };
	public static final byte[] FACTORY_DATA_RDS = { Data1, factoryData2, 0x02 };
	public static final byte[] FACTORY_DATA_TV = { Data1, factoryData2, 0x03 };
	public static final byte[] FACTORY_DATA_MOTOR_TEST = { Data1, factoryData2,
			0x04 };
	public static final byte[] FACTORY_DATA_USER_FUNCTION = { Data1,
			factoryData2, 0x05 };
	public static final byte[] FACTORY_DATA_AMP_OPTION = { Data1, factoryData2,
			0x06 };
	public static final byte[] FACTORY_DATA_CANBUS_TYPE = { Data1,
			factoryData2, 0x07 };
	public static final byte[] FACTORY_DATA_CANBUSAMP_VOLUME = { Data1,
			factoryData2, 0x09 };
	public static final byte[] FACTORY_DATA_SOUND_CONTROL = { Data1,
			factoryData2, 0x0a };
	// === Direction ===
	public static final int IMPEDANCE_MODE_HIGH = 1;
	public static final int IMPEDANCE_MODE_LOW = 0;
	public static final int SWC_ORDER_MAX = 14;

	public static final int MODE_SWC_ORDER = 0;
	public static final int PREV_SWC_ORDER = 1;
	public static final int PLAY_PAUSE_SWC_ORDER = 2;
	public static final int NEXT_SWC_ORDER = 3;
	public static final int VOLUME_PLUS_SWC_ORDER = 4;
	public static final int VOLUME_DEC_SWC_ORDER = 5;
	public static final int VOLUME_MUTE_SWC_ORDER = 6;
	public static final int RADIO_SWC_ORDER = 7;
	public static final int NAVIGATE_SWC_ORDER = 8;
	public static final int DISC_SWC_ORDER = 9;
	public static final int MENU_SWC_ORDER = 10;
	public static final int ENTER_SWC_ORDER = 11;
	public static final int DIAL_SWC_ORDER = 12;
	public static final int END_SWC_ORDER = 13;

	public static final int SWC_INFO_CLEAR_ALL = 0;
	public static final int SWC_INFO_SELECT_FUNC = 1;
	public static final int SWC_INFO_PRESS_START = 2;
	public static final int SWC_INFO_PRESS_SW = 3;
	public static final int SWC_INFO_OK_SELECT_NEXT = 4;
	public static final int SWC_INFO_FAIL_AGAIN = 5;
	public static final int SWC_INFO_STORAGE_OK = 6;
	public static final int SWC_INFO_STORAGE_FAIL = 7;

	public static final byte[] DIRECTION_DATA_KEY_START = { Data1,
			directionData2, 0x01 };
	public static final byte[] DIRECTION_DATA_HIGH_LOW = { Data1,
			directionData2, 0x02 };
	public static final byte[] DIRECTION_DATA_KEY_CLEAN = { Data1,
			directionData2, 0x03 };
	public static final byte[] DIRECTION_DATA_KEY_SAVE = { Data1,
			directionData2, 0x04 };
	public static final byte[] DIRECTION_DATA_KEY_SELECT = { Data1,
			directionData2, 0x05 };
	public static final byte[] DIRECTION_DATA_CONFIGURE = { Data1,
			directionData2, 0x07 };

	// === rearZone ===
	public static final byte[] REARZONE_DATA_CONTROL = { Data1, rearZoneData2,
			0x01 };
	public static final byte[] REARZONE_DATA_CONTROL_BG = { Data1,
			rearZoneData2, 0x02 };

	public static final byte[] REARZONE_DATA = { 0x05, /* 0x0b, */0x0a, 0x02, /* 0x06 */
	0x00 };

	public static final byte CONTROL_BG_PREV = 0x00;
	public static final byte CONTROL_BG_NEXT = 0x02;
	public static final byte CONTROL_BG_PLAY_PAUSE = 0x07;

	public static final int VOLUME_EQ_MODE = 7;
	public static final int VOLUME_EQ_MODE_STANDARD = 0x00;
	public static final int VOLUME_EQ_MODE_POP = 0x01;
	public static final int VOLUME_EQ_MODE_CLASSIC = 0x02;
	public static final int VOLUME_EQ_MODE_ROCK = 0x03;
	public static final int VOLUME_EQ_MODE_JAZZ = 0x04;
	public static final int VOLUME_EQ_MODE_CUSTOM = 0x05;

	public static final byte IS_TRUE = 0x01;
	public static final byte IS_FALSE = 0x00;

	// === CarDVRS ===
	public static final byte[] CARDVRS_DATA_MENU = { Data1, CarDVRSData2, 0x00 };
	public static final byte[] CARDVRS_DATA_BACK_PLAY = { Data1, CarDVRSData2,
			0x01 };
	public static final byte[] CARDVRS_DATA_VIDEO = { Data1, CarDVRSData2, 0x02 };
	public static final byte[] CARDVRS_DATA_PHOTOGRAPH = { Data1, CarDVRSData2,
			0x03 };
	public static final byte[] CARDVRS_DATA_UP = { Data1, CarDVRSData2, 0x04 };
	public static final byte[] CARDVRS_DATA_DOWN = { Data1, CarDVRSData2, 0x05 };
	public static final byte[] CARDVRS_DATA_LEFT = { Data1, CarDVRSData2, 0x06 };
	public static final byte[] CARDVRS_DATA_RIGHT = { Data1, CarDVRSData2, 0x07 };
	public static final byte[] CARDVRS_DATA_OK = { Data1, CarDVRSData2, 0x08 };
	public static final byte[] CARDVRS_DATA_START_VIDEO = { Data1,
			CarDVRSData2, 0x09 };
	public static final byte[] CARDVRS_DATA_STOP = { Data1, CarDVRSData2, 0x0a };
	public static final byte[] CARDVRS_DATA_COORDINATE = { Data1, CarDVRSData2,
			0x20 };

	public McuSendData() {
		super();
		mService = IWincaMcu.Stub.asInterface(ServiceManager
				.getService("winca_mcu"));
	}

	public static McuSendData getMcuSendData() {
		if (mMcuSendData == null) {
			mMcuSendData = new McuSendData();
		}
		return mMcuSendData;
	}

	public void PushData(int... args) {
		List<Byte> buffer = new ArrayList<Byte>();
		for (int b : args) {
			buffer.add((byte) b);
		}
		SendData(tobyte(buffer));
	}

	public void PushData(byte[] byte1, byte[] byte2, int... args) {
		List<Byte> buffer = new ArrayList<Byte>();
		for (byte b : byte1) {
			buffer.add(b);
		}
		for (byte b : byte2) {
			buffer.add(b);
		}
		for (int b : args) {
			buffer.add((byte) b);
		}
		SendData(tobyte(buffer));
	}

	public void PushData(byte[] byte1, int... args) {
		List<Byte> buffer = new ArrayList<Byte>();
		for (byte b : byte1) {
			buffer.add(b);
		}
		for (int b : args) {
			buffer.add((byte) b);
		}
		SendData(tobyte(buffer));
	}

	public void PushData(byte[] cmd, String str) {

		List<Byte> strBuffer = new ArrayList<Byte>();

		for (byte b : cmd) {
			strBuffer.add(b);
		}

		byte[] strByte = null;

		try {
			strByte = str.getBytes("gb2312");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		strBuffer.add((byte) strByte.length);
		strBuffer.add((byte) 1);

		for (int i = 0; i < strByte.length; i++) {
			strBuffer.add(strByte[i]);
		}

		strByte = tobyte(strBuffer);

		if (mService != null) {
			try {
				mService.sendMcuDate(strByte, strByte.length);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void SendData(byte[] buffer) {
		if (DEBUG) {
			String s = "";
			for (byte b : buffer) {
				if (b <= 0x0e) {
					s += " 0x0" + Integer.toHexString(b);
				} else {
					s += " 0x" + Integer.toHexString(b);
				}
			}
			Log.d(TAG, "send mcu data = " + s);
		}
		if (mService != null) {
			try {
				mService.sendMcuDate(buffer, buffer.length);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] tobyte(List<Byte> buffer) {
		byte[] byteBuffer = new byte[buffer.size()];
		for (int i = 0; i < buffer.size(); i++) {
			byteBuffer[i] = (byte) buffer.get(i);
		}
		return byteBuffer;
	}

	public void touchUp(float x, float y) {
		if (mService != null) {
			int[] buffer = { (int) x, (int) y };
			try {
				mService.method_VIA(GlobalMethodID.METHOD_ID_TOUCH_UP, buffer);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void touchDown(float x, float y) {
		if (mService != null) {
			int[] buffer = { (int) x, (int) y };
			try {
				mService.method_VIA(GlobalMethodID.METHOD_ID_TOUCH_DOMN, buffer);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void touchMove(float x, float y) {
		if (mService != null) {
			int[] buffer = { (int) x, (int) y };
			try {
				mService.method_VIA(GlobalMethodID.METHOD_ID_TOUCH_MOVE, buffer);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void qureyInfo(byte[] dataQureyInfo) {
		if (mService != null) {
			try {
				mService.sendMcuDate(dataQureyInfo, dataQureyInfo.length);
				if (DEBUG) {
					Log.d(TAG, "qureyInfo = "
							+ dataQureyInfo[dataQureyInfo.length - 1]);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void switchSource(int mcuChannel) {
		if (DEBUG) {
			Log.d(TAG, "switchSource = " + mcuChannel);
		}
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_REQ_CHANNEL,
						mcuChannel);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void switchPageInfo(int page) {
		if (DEBUG) {
			Log.d(TAG, "switchPageInfo = " + page);
		}
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_SET_MENU, page);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isHandBreak() {
		if (mService != null) {
			try {
				return mService
						.getMethod_BV(GlobalMethodID.METHOD_ID_HANDBREAK_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isNoSignal() {
		if (mService != null) {
			try {
				return mService
						.getMethod_BV(GlobalMethodID.METHOD_ID_NOSIGNAL_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean isDPS() {
		if (mService != null) {
			try {
				return mService
						.getMethod_BV(GlobalMethodID.METHOD_ID_DSP_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isKeyColourful() {
		if (mService != null) {
			try {
				return mService
						.getMethod_BV(GlobalMethodID.METHOD_ID_KEY_COLOR_TYPE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isDiscExist() {
		if (mService != null) {
			try {
				return mService
						.getMethod_BV(GlobalMethodID.METHOD_ID_DISC_EXIST_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void setNaviInfo(String packageName, String className) {
		String[] naviInfo = new String[2];
		naviInfo[0] = packageName;
		naviInfo[1] = className;
		if (mService != null) {
			try {
				mService.method_VSA(GlobalMethodID.METHOD_ID_SET_NAVI, naviInfo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void setLogo(String logoName) {
		if (mService != null) {
			try {
				mService.method_VS(GlobalMethodID.METHOD_ID_SET_LOGO_FILE,
						logoName);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void setUSBVersion(int usbVersion) {
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_SET_USB_TYPE,
						usbVersion);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void setDrivingPosition(int drivingPosition) {
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_DRIVING_SEAT,
						drivingPosition);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void setParrotBTPairedNumber(int parrotBTPairedNumber) {
		if (mService != null) {
			try {
				mService.method_VI(
						GlobalMethodID.METHOD_ID_BT_PAIRED_DEVICES_NUM,
						parrotBTPairedNumber);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public int getParrotBTPairedNumber() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_BT_PAIRED_DEVICES_NUM_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	public int getDrivingPosition() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_DRIVING_SEAT_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return McuData.DRIVINGPOSITIONLEFT;
	}

	public int getCanbusType() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_CANBUS_TYPE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int getSource() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_CHANNEL_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int getLastSource() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_LAST_CHANNEL_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public String getNaviClassName() {
		if (mService != null) {
			try {
				return mService
						.getMethod_SV(GlobalMethodID.METHOD_ID_NAVI_CLS_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getNaviPackageName() {
		if (mService != null) {
			try {
				return mService
						.getMethod_SV(GlobalMethodID.METHOD_ID_NAVI_PKG_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getVideoType() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_VIDEO_TYPE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int getBalance() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_BALANCE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 7;
	}

	public int getFader() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_FADER_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 7;
	}

	public int getRearzoneStatus() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_REARZONE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public String getLogo() {
		if (mService != null) {
			try {
				return mService
						.getMethod_SV(GlobalMethodID.METHOD_ID_LOGO_FILE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public int getTVMode() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_TVMODE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public String getVersion(int id) {
		if (mService != null) {
			try {
				return mService.getMethod_SV(id);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getCanbusConnect() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_CANBUS_CONNECT_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int getVideoMode() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_VIDEO_MODE_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int getUSBVersion() {
		if (mService != null) {
			try {
				if (mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_USB_TYPE_STATUS) <= 0
						|| mService
								.getMethod_IV(GlobalMethodID.METHOD_ID_USB_TYPE_STATUS) > 2) {
					return 1;
				} else {
					return mService
							.getMethod_IV(GlobalMethodID.METHOD_ID_USB_TYPE_STATUS);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	public int getFmBandStatus() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_FM_BAND_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public String getFmFrequencyStatus() {
		if (mService != null) {
			try {
				return mService
						.getMethod_SV(GlobalMethodID.METHOD_ID_FM_FREQUENCY_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public String getBtMusicNameStatus() {
		if (mService != null) {
			try {
				return mService
						.getMethod_SV(GlobalMethodID.METHOD_ID_BT_MUSIC_NAME_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public int getCanBusAmpVolume() {
		if (mService != null) {
			try {
				return mService
						.getMethod_IV(GlobalMethodID.METHOD_ID_CANBUSAMP_VOLUME_STATUS);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void muteDelay() {
		if (DEBUG) {
			Log.d(TAG, "muteDelay");
		}
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_MCU_MUTE_DELAY, 1);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void volumeAdd() {
		if (mService != null) {
			try {
				mService.method_VV(GlobalMethodID.METHOD_ID_VOLUME_ADD);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void volumeDesc() {
		if (mService != null) {
			try {
				mService.method_VV(GlobalMethodID.METHOD_ID_VOLUME_DESC);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void analogTVSetSignal(int image, int sound) {
		if (mService != null) {
			byte[] buffer = { 0x41, 0x0F, 0x0C, (byte) image, (byte) sound };
			try {
				mService.sendMcuDate(buffer, buffer.length);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void openVideo(View playView, View touchView, int mcu_channel) {
		if (DEBUG) {
			Log.d(TAG, "tools openVideo");
		}
		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_REQ_OPEN_MCU_VIDEO,
						mcu_channel);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeVideo(View playView, View touchView, int mcu_channel) {
		if (DEBUG) {
			Log.d(TAG, "tools closeVideo");
		}
		if (mService != null) {
			try {
				mService.method_VI(
						GlobalMethodID.METHOD_ID_REQ_CLOSE_MCU_VIDEO,
						mcu_channel);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void showFloatAirWindow(boolean flag) {
		if (DEBUG) {
			Log.d(TAG, "tools showFloatAirWindow");
		}
		if (mService != null) {
			try {
				mService.method_VB(GlobalMethodID.METHOD_CANBUS_FLAOTVIEW_FLAG,
						flag);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public static final int MSG_SETBGCOLOR = 31;
	public static final int MSG_SETBGCOLOR_TIME = 900;
	public static int MSG_SETBGCOLOR_MARK = 0;

	public void sendsetBGColorMessage(View playView, View touchView, int data) {
		if (MSG_SETBGCOLOR_MARK != data) {
			MSG_SETBGCOLOR_MARK = data;
			MSG_SETBGCOLOR_MARK = data;
			View[] v = { playView, touchView };
			Message msg = new Message();
			msg.what = MSG_SETBGCOLOR;
			msg.obj = v;
			msg.arg1 = MSG_SETBGCOLOR_MARK;
			setBGColorHandler.removeMessages(MSG_SETBGCOLOR);
			if (msg.arg1 == 1) {
				setBGColorHandler.sendMessageDelayed(msg, MSG_SETBGCOLOR_TIME);
			} else {
				setBGColorHandler.sendMessage(msg);
			}
		}
	}

	public static Handler setBGColorHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MSG_SETBGCOLOR) {
				View[] v = (View[]) msg.obj;
				if (msg.arg1 == 1) {
					v[1].setVisibility(View.GONE);
					v[0].setBackgroundColor(Color
							.rgb(play[0], play[1], play[2]));
				} else {
					v[0].setBackgroundColor(Color.rgb(black[0], black[1],
							black[2]));
					v[1].setVisibility(View.VISIBLE);
				}
			}
		}
	};

	private static final int MSG_UPDATAUI = 32131;
	private static final int MSG_UPDATAUI_TIME = 1000;
	private static boolean MSG_UPDATAUI_EXIT = false;

	public interface McuSendCallback {
		public void onUpDataUI();
	}

	private static McuSendCallback mCallback = null;

	public static Handler mUpdataUIHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MSG_UPDATAUI) {
				if (mCallback != null) {
					Log.d("MSG_UPDATAUI", "MSG_UPDATAUI");
					mCallback.onUpDataUI();
				}
				if (!MSG_UPDATAUI_EXIT) {
					mUpdataUIHandler.sendEmptyMessageDelayed(MSG_UPDATAUI,
							MSG_UPDATAUI_TIME);
				}
			}
		}
	};

	public void loopUpDataUI(McuSendCallback callback) {
		MSG_UPDATAUI_EXIT = false;
		mCallback = callback;
		mUpdataUIHandler.removeMessages(MSG_UPDATAUI);
		mUpdataUIHandler.sendEmptyMessageDelayed(MSG_UPDATAUI,
				MSG_UPDATAUI_TIME);
	}

	public void stopUpDataUI() {
		mCallback = null;
		mUpdataUIHandler.removeMessages(MSG_UPDATAUI);
		MSG_UPDATAUI_EXIT = true;
	}

	private static final int MSG_SWITCH_PAGEINFO = 32132;
	private static final int MSG_SWITCH_PAGEINFO_TIME = 1000;

	public Handler mSwitchPageInfoHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == MSG_SWITCH_PAGEINFO) {
				switchPageInfo(msg.arg1);
			}
			super.handleMessage(msg);
		}
	};

	public void switchPageInfoHandler(int pageInfo) {
		Message msg = new Message();
		msg.what = MSG_SWITCH_PAGEINFO;
		msg.arg1 = pageInfo;
		mSwitchPageInfoHandler.removeMessages(MSG_SWITCH_PAGEINFO);
		mSwitchPageInfoHandler
				.sendMessageDelayed(msg, MSG_SWITCH_PAGEINFO_TIME);
	}

	public void removeSwitchPageInfoHandler() {
		mSwitchPageInfoHandler.removeMessages(MSG_SWITCH_PAGEINFO);
	}

}
