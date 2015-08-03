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
		Process proc = runtime.exec(command); //这句话就是shell与高级语言间的调用
		//如果有参数的话可以用另外一个被重载的exec方法

		//实际上这样执行时启动了一个子进程,它没有父进程的控制台
		//也就看不到输出,所以我们需要用输出流来得到shell执行后的输出
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

		//使用exec执行不会等执行成功以后才返回,它会立即返回
		//所以在某些情况下是很要命的(比如复制文件的时候)
		//使用wairFor()可以等待命令执行完成以后才返回
		try {
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	/** 
	 * 执行一个shell命令，并返回字符串值 
	 * 
	 * @param cmd 
	 * 命令名称&参数组成的数组（例如：{"/system/bin/cat", "/proc/version"}） 
	 * @param workdirectory 
	 * 命令执行路径（例如："system/bin/"） 
	 * @return 执行结果组成的字符串 
	 * @throws IOException 
	 */
	public static synchronized String run(String cmd, String workdirectory)
			throws IOException {
		StringBuffer result = new StringBuffer();
		try {
			// 创建操作系统进程（也可以由Runtime.exec()启动） 
			// Runtime runtime = Runtime.getRuntime(); 
			// Process proc = runtime.exec(cmd); 
			// InputStream inputstream = proc.getInputStream(); 
			ProcessBuilder builder = new ProcessBuilder(cmd);

			InputStream in = null;
			// 设置一个路径（绝对路径了就不一定需要） 
			if (workdirectory != null) {
				// 设置工作目录（同上） 
				builder.directory(new File(workdirectory));
				// 合并标准错误和标准输出 
				builder.redirectErrorStream(true);
				// 启动一个新进程 
				Process process = builder.start();

				// 读取进程标准输出流 
				in = process.getInputStream();
				byte[] re = new byte[1024];
				while (in.read(re) != -1) {
					result = result.append(new String(re));
				}
			}
			// 关闭输入流 
			if (in != null) {
				in.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}
}
