package com.winca.tools;

import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.wincamcu.IWincaMcu;

public class McuSendData2 {

	private static final boolean DEBUG = true;
	private static final String TAG = "McuSendData";

	public static final int DRIVINGPOSITIONLEFT = 0;
	public static final int DRIVINGPOSITIONRIGHT = 1;

	private static IWincaMcu mService;
	private static McuSendData2 mMcuSendData2;

	public McuSendData2() {
		super();
		mService = IWincaMcu.Stub.asInterface(ServiceManager
				.getService("winca_mcu"));
	}

	public static McuSendData2 getMcuSendData() {
		if (mMcuSendData2 == null) {
			mMcuSendData2 = new McuSendData2();
		}
		return mMcuSendData2;
	}

	public void setDrivingPosition(int drivingPosition) {
		Log.d("hua", "drivingPosition = " + drivingPosition);

		if (mService != null) {
			try {
				mService.method_VI(GlobalMethodID.METHOD_ID_DRIVING_SEAT,
						drivingPosition);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
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
		return 0;
	}
}
