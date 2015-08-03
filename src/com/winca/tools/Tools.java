package com.winca.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class Tools {

	public void execCommand(String command) throws IOException {

		// start the ls command running
		//String[] args = new String[]{"sh", "-c", command};
		Runtime runtime = Runtime.getRuntime();
		Log.d("hua", "command = " + command);
		Process proc = runtime.exec(command); //��仰����shell��߼����Լ�ĵ���
		//����в����Ļ�����������һ�������ص�exec����

		//ʵ��������ִ��ʱ������һ���ӽ���,��û�и����̵Ŀ���̨
		//Ҳ�Ϳ��������,����������Ҫ����������õ�shellִ�к�����
		InputStream inputstream = proc.getInputStream();
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

		// read the ls output

		String line = "";
		StringBuilder sb = new StringBuilder(line);
		while ((line = bufferedreader.readLine()) != null) {
			//System.out.println(line);
			sb.append(line);
			sb.append('\n');
		}

		//tv.setText(sb.toString());

		//ʹ��execִ�в����ִ�гɹ��Ժ�ŷ���,������������
		//������ĳЩ������Ǻ�Ҫ����(���縴���ļ���ʱ��)
		//ʹ��wairFor()���Եȴ�����ִ������Ժ�ŷ���
		try {
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	/** 
	 * ִ��һ��shell����������ַ���ֵ 
	 * 
	 * @param cmd 
	 * ��������&������ɵ����飨���磺{"/system/bin/cat", "/proc/version"}�� 
	 * @param workdirectory 
	 * ����ִ��·�������磺"system/bin/"�� 
	 * @return ִ�н����ɵ��ַ��� 
	 * @throws IOException 
	 */
	public static synchronized String run(String cmd, String workdirectory)
			throws IOException {
		StringBuffer result = new StringBuffer();
		try {
			// ��������ϵͳ���̣�Ҳ������Runtime.exec()������ 
			// Runtime runtime = Runtime.getRuntime(); 
			// Process proc = runtime.exec(cmd); 
			// InputStream inputstream = proc.getInputStream(); 
			ProcessBuilder builder = new ProcessBuilder(cmd);

			InputStream in = null;
			// ����һ��·��������·���˾Ͳ�һ����Ҫ�� 
			if (workdirectory != null) {
				// ���ù���Ŀ¼��ͬ�ϣ� 
				builder.directory(new File(workdirectory));
				// �ϲ���׼����ͱ�׼��� 
				builder.redirectErrorStream(true);
				// ����һ���½��� 
				Process process = builder.start();

				// ��ȡ���̱�׼����� 
				in = process.getInputStream();
				byte[] re = new byte[1024];
				while (in.read(re) != -1) {
					result = result.append(new String(re));
				}
			}
			// �ر������� 
			if (in != null) {
				in.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}
}
