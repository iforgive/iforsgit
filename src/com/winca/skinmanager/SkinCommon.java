package com.winca.skinmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class SkinCommon {
	public static final String TAG = "SkinManager";
	public static final String SKIN_CHANGE_ACTION = "com.winca.style.SKIN_CHANGE_ACTION";
	public static final String SHAREDUSERID_PACKAGE = "com.winca.style";
	public static final String SKIN_PACKAGE_START = "com.winca.style";
	public static final String KEY_PREPERTIES_PACKAGE = "sys.skin.style";
	public static final String DEFAULT_PACKAGE = "com.winca.style.audiskin";
	public static final String _DEFAULT_CONTAINS = "default";
	public static final String _LAYOUT = "layout";
	public static final String _ID = "id";
	public static final String _DRAWABLE = "drawable";
	public static final String _STRING = "string";
	public static final String _ANIM = "anim";
	public static final String _COLOR = "color";
	public static final String _DIMEN = "dimen";
	public static final String _ARRAY = "array";
	public static final String _XML = "xml";
	private Context mContext;
	private Context mSkinContext;
	private String mSkinPackage;
	private List<String> mSkinPackageNames = new ArrayList<String>();

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (0 == action.compareTo(SKIN_CHANGE_ACTION)) {
				System.exit(0);
			}
		}
	};

	public SkinCommon(Context context) {
		mContext = context;
		mSkinPackage = SystemProperties.get(KEY_PREPERTIES_PACKAGE,
				DEFAULT_PACKAGE);
		mSkinPackage = "com.winca.style.audiskin";
		getSkinPackageNames();
		createSkinContext(mSkinPackage);
		registerStyleChange();
	}

	public String getSkinPackageName() {
		return mSkinPackage;
	}

	public Context getSkinContext() {
		if (mSkinContext == null) {
			createSkinContext(SystemProperties.get(KEY_PREPERTIES_PACKAGE,
					DEFAULT_PACKAGE));
		}
		return mSkinContext;
	}

	protected Context createSkinContext(String pkg) {
		boolean isPkgInstall = false;
		String defaultskinPkg = DEFAULT_PACKAGE;
		String installedPkg = DEFAULT_PACKAGE;
		for (String skinPkg : mSkinPackageNames) {
			if (pkg != null && skinPkg != null
					&& pkg.trim().equals(skinPkg.trim())) {
				installedPkg = skinPkg.trim();
				isPkgInstall = true;
			}
			if (skinPkg != null && skinPkg.contains(_DEFAULT_CONTAINS)) {
				defaultskinPkg = skinPkg.trim();
			}
		}
		if (!isPkgInstall) {
			installedPkg = defaultskinPkg;
		}

		try {
			mSkinContext = mContext.createPackageContext(installedPkg,
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return mSkinContext;
	}

	protected void getSkinPackageNames() {
		PackageManager packageManager = mContext.getPackageManager();
		List<PackageInfo> packageInfoList = packageManager
				.getInstalledPackages(0);
		int count = packageInfoList.size();
		PackageInfo pkgInfo = null;
		for (int i = 0; i < count; i++) {
			pkgInfo = packageInfoList.get(i);
			if (null != pkgInfo && null != pkgInfo.packageName
			/* && null != pkgInfo.sharedUserId */
			/* && pkgInfo.sharedUserId
			 * .compareToIgnoreCase(SHAREDUSERID_PACKAGE) == 0 */
			&& pkgInfo.packageName.startsWith(SKIN_PACKAGE_START)) {
				mSkinPackageNames.add(pkgInfo.packageName);
				Log.e(TAG, "mSkinPackageNames = " + pkgInfo.packageName);
				Log.e(TAG, "mSkinPackageNames = " + pkgInfo.packageName);
			}
		}
	}

	public int getSkinResLayoutId(String layoutName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(layoutName,
					_LAYOUT, mSkinPackage);
		}

		return -1;

	}

	public View getSkinResLayoutViewByID(int layoutID) {
		View view = null;
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			view = LayoutInflater.from(mSkinContext).inflate(layoutID, null);
		}

		return view;
	}

	public View getSkinResLayoutViewByName(String layoutName) {
		View view = null;
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			view = LayoutInflater.from(mSkinContext).inflate(
					getSkinResLayoutId(layoutName), null);
		}
		return view;
	}

	public int getSkinResWidgetViewID(String widgetName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(widgetName, _ID,
					mSkinPackage);
		}
		return -1;
	}

	public View getSkinResWidgetView(View layout, String widgetName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return layout.findViewById(getSkinResWidgetViewID(widgetName));
		}
		return null;
	}

	public int getSkinResDrawableId(String imgName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(imgName,
					_DRAWABLE, mSkinPackage);
		}
		return -1;
	}

	public Drawable getSkinResDrawable(String imgName) {
		int id = getSkinResDrawableId(imgName);
		if (id > 0) {
			return mSkinContext.getResources().getDrawable(id);
		}
		return null;
	}

	public int getSkinResStringId(String stringName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(stringName,
					_STRING, mSkinPackage);
		}
		return -1;
	}

	public String getSkinResString(String stringName) {
		int id = getSkinResStringId(stringName);
		if (id > 0) {
			return mSkinContext.getResources().getString(id);
		}
		return null;
	}

	public int getSkinResAnimId(String animationName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(animationName,
					_ANIM, mSkinPackage);
		}
		return -1;
	}

	public XmlPullParser getSkinResAnimXml(String animationName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getAnimation(
					getSkinResAnimId(animationName));
		}
		return null;
	}

	public Animation getSkinResAnim(String animationName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		Animation animation = null;
		XmlPullParser parser = getSkinResAnimXml(animationName);
		AttributeSet attrs = Xml.asAttributeSet(parser);
		try {
			animation = createAnimationFromXml(mSkinContext, parser, null,
					attrs);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return animation;
	}

	public int getSkinResColorId(String colorName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(colorName, _COLOR,
					mSkinPackage);
		}
		return -1;
	}

	public int getSkinResColor(String colorName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getColor(
					getSkinResColorId(colorName));
		}
		return -1;
	}

	public int getSkinResDimensId(String dimenName) {

		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(dimenName, _DIMEN,
					mSkinPackage);
		}
		return -1;
	}

	public float getSkinResDimens(String dimenName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getDimension(
					getSkinResDimensId(dimenName));
		}
		return -1;
	}

	public int getSkinResArrayId(String arrayName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(arrayName, _ARRAY,
					mSkinPackage);
		}
		return -1;
	}

	public String[] getSkinResArray(String arrayName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getStringArray(
					getSkinResArrayId(arrayName));
		}
		return null;
	}

	public int getSkinResXMLId(String xmlName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getIdentifier(xmlName, _XML,
					mSkinPackage);
		}
		return -1;
	}

	public XmlResourceParser getSkinResXML(String xmlName) {
		if (mSkinContext == null) {
			createSkinContext(mSkinPackage);
		}
		if (mSkinContext != null) {
			return mSkinContext.getResources().getXml(getSkinResXMLId(xmlName));
		}
		return null;
	}

	private Animation createAnimationFromXml(Context c, XmlPullParser parser,
			AnimationSet parent, AttributeSet attrs)
			throws XmlPullParserException, IOException {

		Animation anim = null;
		int type;
		int depth = parser.getDepth();
		while (((type = parser.next()) != XmlPullParser.END_TAG || parser
				.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {

			if (type != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("set")) {
				anim = new AnimationSet(c, attrs);
				createAnimationFromXml(c, parser, (AnimationSet) anim, attrs);
			} else if (name.equals("alpha")) {
				anim = new AlphaAnimation(c, attrs);
			} else if (name.equals("scale")) {
				anim = new ScaleAnimation(c, attrs);
			} else if (name.equals("rotate")) {
				anim = new RotateAnimation(c, attrs);
			} else if (name.equals("translate")) {
				anim = new TranslateAnimation(c, attrs);
			} else {
				throw new RuntimeException("Unknown animation name:"
						+ parser.getName());
			}
			if (parent != null) {
				parent.addAnimation(anim);
			}
		}
		return anim;
	}

	public void registerStyleChange() {
		//try {
		//	if (mContext != null) {
		//		IntentFilter filter = new IntentFilter(SKIN_CHANGE_ACTION);
		//		mContext.registerReceiver(mReceiver, filter);
		//	}
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
	}

	public void unregisterStyleChange() {
		//try {
		//	mContext.unregisterReceiver(mReceiver);
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
	}
}
