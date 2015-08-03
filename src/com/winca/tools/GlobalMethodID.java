package com.winca.tools;

public interface GlobalMethodID {
	//use for status data 20000 ~ 201000
	public static final int METHOD_ID_CHANNEL_STATUS					= 200000;
	public static final int METHOD_ID_HANDBREAK_STATUS					= 200001;
	public static final int METHOD_ID_BACKCAR_STATUS					= 200002;
	public static final int METHOD_ID_NOSIGNAL_STATUS					= 200003;
	public static final int METHOD_ID_VIDEO_TYPE_STATUS					= 200004;
	public static final int METHOD_ID_NAVI_PKG_STATUS					= 200005;
	public static final int METHOD_ID_NAVI_CLS_STATUS					= 200006;
	public static final int METHOD_ID_DAY_NIGHT_STATUS					= 200007;
	public static final int METHOD_ID_MCU_VERSION_STATUS				= 200008;
	public static final int METHOD_ID_DVD_VERSION_STATUS				= 200009;
	public static final int METHOD_ID_FLASH_VERSION_STATUS				= 200010;
	public static final int METHOD_ID_IAP_VERSION_STATUS				= 200011;
	public static final int METHOD_ID_HARDWARE_VERSION_STATUS			= 200012;
	public static final int METHOD_ID_RADIO_SOFT_VERSION_STATUS			= 200013;
	public static final int METHOD_ID_DEVICE_TYPE_STATUS				= 200014;
	public static final int METHOD_ID_DSP_STATUS						= 200015;
	public static final int METHOD_ID_KEY_COLOR_TYPE_STATUS				= 200016;
	//Disaplay status
	public static final int METHOD_ID_DISAPLAY_BRIGHTNESS_DAY_STATUS	= 200017;
	public static final int METHOD_ID_DISAPLAY_BRIGHTNESS_NIGHT_STATUS	= 200018;
	public static final int METHOD_ID_VOLUME_STATUS						= 200019;
	public static final int METHOD_ID_DISC_EXIST_STATUS					= 200020;
	public static final int METHOD_ID_BALANCE_STATUS					= 200021;
	public static final int METHOD_ID_FADER_STATUS						= 200022;
	public static final int METHOD_ID_REARZONE_STATUS					= 200023;
	public static final int METHOD_ID_LOGO_FILE_STATUS					= 200024;
	public static final int METHOD_ID_TVMODE_STATUS						= 200025;
	public static final int METHOD_ID_CANBUS_SHOW_STATUS				= 200026;
	public static final int METHOD_ID_CANBUS_VERSION_STATUS				= 200027;//string
	public static final int METHOD_ID_CANBUS_CONNECT_STATUS				= 200028;//int
	public static final int METHOD_ID_VIDEO_MODE_STATUS					= 200029;
	public static final int METHOD_ID_USB_TYPE_STATUS					= 200030;
	public static final int METHOD_ID_LAST_CHANNEL_STATUS				= 200031;
	public static final int METHOD_ID_FM_BAND_STATUS					= 200032;
	public static final int METHOD_ID_FM_FREQUENCY_STATUS				= 200033;//string
	public static final int METHOD_ID_BT_MUSIC_NAME_STATUS				= 200034;//string
	public static final int METHOD_ID_BT_LOCAL_NAME_STATUS				= 200035;
	public static final int METHOD_ID_WIFI_MODEL_STATUS					= 200036;
	public static final int METHOD_ID_KEY_DISENBLE_STATUS				= 200037;//Boolean
	public static final int METHOD_ID_CANBUSAMP_VOLUME_STATUS			= 200038;
	public static final int METHOD_ID_IL_STATUS							= 200039;//boolean
	public static final int METHOD_ID_DRIVING_SEAT_STATUS				= 200040;//INT
	public static final int METHOD_ID_IS_CURRENT_NAVI_STATUS			= 200041;//BOOLEAN
	public static final int METHOD_ID_BT_MODEL_STATUS					= 200042;//STRING ["YW","WQ"]
	public static final int METHOD_ID_BT_PAIRED_DEVICES_NUM_STATUS		= 200043;//INT
	public static final int METHOD_ID_CANBUS_TYPE_STATUS				= 200044;//INT
	public static final int METHOD_ID_BT_ENABLE_STATUS					= 200045;//INT 0:disable 1:enable
	public static final int METHOD_ID_CODE_TYPE_AUDIO_STATUS			= 200046;//string
	public static final int METHOD_ID_CODE_TYPE_VEDIO_STATUS			= 200047;//string
	public static final int METHOD_ID_CODE_TYPE_BT_STATUS				= 200048;//string
	public static final int METHOD_ID_CODE_TYPE_ALL_STATUS				= 200049;//string
	public static final int METHOD_ID_VOLUME_DISPLAY_SYTLE_STATUS		= 200050;//INT ,0 MID ; 1 BOTTOM
	public static final int METHOD_ID_VIDEO_LAST_NAME_STATUS			= 200051;//string
	public static final int METHOD_ID_TV_ANALOG_SIGNAL_STATUS			= 200052;//boolean
	public static final int METHOD_ID_DVD_STATUS						= 200053;//INT

	//use for service handle
	public static final int METHOD_ID_REQ_CHANNEL					= 10000;
	public static final int METHOD_ID_SET_NAVI						= 10001;
	public static final int METHOD_ID_SET_MENU						= 10002;
	public static final int METHOD_ID_VOLUME_ADD					= 10003;
	public static final int METHOD_ID_VOLUME_DESC					= 10004;
	public static final int METHOD_ID_REQ_OPEN_MCU_VIDEO			= 10005;
	public static final int METHOD_ID_REQ_CLOSE_MCU_VIDEO			= 10006;
	public static final int METHOD_ID_BT_STATUS						= 10007;
	public static final int METHOD_ID_SHOW_VOLUME_VIEW_STATUS		= 10008;
	public static final int METHOD_ID_SET_LOGO_FILE					= 10009;
	public static final int METHOD_ID_SET_USB_TYPE					= 10010;
	public static final int METHOD_ID_MCU_MUTE_DELAY				= 10011;
	public static final int METHOD_CANBUS_FLAOTVIEW_FLAG			= 10012;//DONOT CHANGE method_VB
	public static final int METHOD_ID_RESET_CONFIG					= 10013;//
	public static final int METHOD_ID_ANDROID_KEYCODE				= 10014;// int 
	public static final int METHOD_ID_DRIVING_SEAT 			    	= 10015;// int 
	public static final int METHOD_ID_BT_PAIRED_DEVICES_NUM	    	= 10016;// int 
	public static final int METHOD_ID_SEND_TPMS_DATA		    	= 10017;// int 
	public static final int METHOD_ID_KEY_SOUND				    	= 10018;// VOID 
	public static final int METHOD_ID_CLOSE_CONFIG_INTERFACE    	= 10019;// VOID 
	public static final int METHOD_ID_SET_DISPLAY_STYLE       		= 10020;// int 
	
	public static final int METHOD_ID_SET_DISAPLAY_BRIGHTNESS_DAY   	= 10021;// int
	public static final int METHOD_ID_SET_DISAPLAY_BRIGHTNESS_NIGHT		= 10022;// int
	
	//use for mcu cmd
	public static final int METHOD_ID_TOUCH_DOMN 		= 15000;
	public static final int METHOD_ID_TOUCH_UP 			= 15001;
	public static final int METHOD_ID_TOUCH_MOVE		= 15002;
}
