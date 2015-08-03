package com.winca.tools;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class ADSetHandler extends DefaultHandler {
	private static final String TAG = "reset";

	
	private static final int ADSET_START = 4;
	private static final int AD_1_NUM = 16;
	private static final int AD_2_NUM = 14;
	private static final int ADSET_MUM = ADSET_START + AD_1_NUM*2 + AD_2_NUM*2;
	private byte[] mADsetBuffer = new byte[ADSET_MUM];

	public boolean isParse = false;
	private int count = 0;
//	private HashMap<String, Integer> mRemHashMap;
	
	public int KeyColorType = 0;//°´¼üµÆÀà±ð£º==0 ÆÕÍ¨µ¥É«°´¼üµÆ£»==1 Ë«É«µÆ£»==2 ÍòÄÜ°´¼üµÆ
	public int openLogo = 0;
	public String deviceType = "";
	public String id = "0";

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		count++;
		String theString = new String(ch, start, length);
		Log.i(TAG, "characters: " + theString);
		Log.i(TAG, "characters: " + "count = " + count);
	}

	@Override
	public void endDocument() throws SAXException {
		count++;
		isParse = false;
		Log.i(TAG, "endDocument: count = " + count);
		for(int i = 0 ; i< mADsetBuffer.length; i++){
			int data = mADsetBuffer[i] & 0xff;
			Log.i(TAG, "endElement: mADsetBuffer["+ i + "] = " + data);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		count++;
		Log.i(TAG, "endElement: count = " + count);
		Log.i(TAG, "endElement: uri = " + uri + "  localName = " + localName
				+ "  qName = " + qName);
		
		
	}

	@Override
	public void startDocument() throws SAXException {
		count++;
		Log.i(TAG, "startDocument: count = " + count);
		isParse = true;
		mADsetBuffer[0] = 0x61;//0X61 »úÐÍÅäÖÃ
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		count++;
		Log.i(TAG, "startElement: count = " + count);
		Log.i(TAG, "startElement: uri = " + uri + "   localName = " + localName
				+ "   qName = " + qName);
		for (int i = 0; i < attributes.getLength(); i++) {
			Log.i(TAG, "startElement: QName = " + attributes.getQName(i));
			Log.i(TAG, "startElement: Value = " + attributes.getValue(i));
		}

		if (localName.equalsIgnoreCase("define")) {
			id = attributes.getValue("id");
			if(attributes.getValue("KeyColorType") != null){
				KeyColorType = Integer.parseInt(attributes.getValue("KeyColorType"));
			}
			
			deviceType = attributes.getValue("name");
			
			
			String nameNum = "";
			if (deviceType != null && !"".equals(deviceType)) {
				for (int i = 0; i < deviceType.length(); i++) {
					if (deviceType.charAt(i) >= 48 && deviceType.charAt(i) <= 57) {
						nameNum += deviceType.charAt(i);
					}
				}
			}
			
			int deviceVersion = Integer.parseInt(nameNum);
			int higt = (deviceVersion >> 8) & 0xff;
			int low = deviceVersion & 0xff;
//			Log.i(TAG, "startElement: higt = " + higt); 
//			Log.i(TAG, "startElement: low = " + low); 
			mADsetBuffer[1] = (byte)low;
			mADsetBuffer[2] = (byte)higt;
			
		}
		if (localName.equalsIgnoreCase("openLogo")) {
			openLogo = Integer.parseInt(attributes.getValue("id"));
		}
		
		if (localName.equalsIgnoreCase("LCD")) {
			mADsetBuffer[3] = (byte)Integer.parseInt(attributes.getValue("id"));
			Log.i(TAG, "startElement: mADsetBuffer[3] = " + mADsetBuffer[3]); 
		}
//		if (localName.equalsIgnoreCase("ADFunc1")) {
//			for (int i = 0; i < attributes.getLength() && attributes.getQName(0).contains("v") ; i++) {
////				Log.i(TAG, "startElement: Value = " + attributes.getValue(i));
//				int value = 255;
//				if(mRemHashMap.get(attributes.getValue(i)) == null){
//					value = 255;
//				}else{
//					value = mRemHashMap.get(attributes.getValue(i));
//				}
//				mADsetBuffer[ADSET_START + i] = (byte)value;
//			} 
//		}
		if (localName.equalsIgnoreCase("ADValue1")) {
			for (int i = 0; i < attributes.getLength()&& attributes.getQName(0).contains("v"); i++) {
//				Log.i(TAG, "startElement: Value = " + attributes.getValue(i));
				mADsetBuffer[ADSET_START + AD_1_NUM + i] = (byte)Integer.parseInt(attributes.getValue(i));
			} 
		}
		
//		if (localName.equalsIgnoreCase("ADFunc2")) {
//			for (int i = 0; i < attributes.getLength()&& attributes.getQName(0).contains("v"); i++) {
////				Log.i(TAG, "startElement: Value = " + attributes.getValue(i));
//				int value = 255;
//				if(mRemHashMap.get(attributes.getValue(i)) == null){
//					value = 255;
//				}else{
//					value = mRemHashMap.get(attributes.getValue(i));
//				}
//				mADsetBuffer[ADSET_START + AD_1_NUM*2 + i] = (byte)value;
//			} 
//		}
		if (localName.equalsIgnoreCase("ADValue2")) {
			for (int i = 0; i < attributes.getLength()&& attributes.getQName(0).contains("v"); i++) {
//				Log.i(TAG, "startElement: Value = " + attributes.getValue(i));
				mADsetBuffer[ADSET_START + AD_1_NUM*2 + AD_2_NUM + i] = (byte)Integer.parseInt(attributes.getValue(i));
			} 
		}
		

	}
	
//	public void setRemHashMap(HashMap<String, Integer> map){
//		mRemHashMap = map;
//	}
	public byte[] getADsetBuffer(){
		return mADsetBuffer;
	}

}
