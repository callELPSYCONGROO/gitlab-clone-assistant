package com.wuhenjian.tool.gitlabcloneassistant.cmd;

import com.wuhenjian.tool.gitlabcloneassistant.workflow.WorkFlowEnum;
import com.wuhenjian.tool.gitlabcloneassistant.workflow.exception.WorkFlowException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * bat脚本运行工具类
 * @author 無痕剑
 * @date 2019/1/6 0:12
 */
public class CmdUtil {

	private static String executeBatCommand(String command) throws IOException {
		Process process = Runtime.getRuntime().exec(command);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		process.destroy();
		return sb.toString();
	}

	/**
	 * 创建目录
	 * @param dir 目录地址
	 */
	public static void createDir(String dir) {
		String[] dirs = dir.split("/");
		// 获取当前目录绝对路径
		String fileDir = System.getProperty("user.dir") + File.separator;
		for (String d : dirs) {
			fileDir = fileDir + d + File.separator;
			createFolder(fileDir);
		}
	}

	/**
	 * 创建文件夹
	 */
	public static void createFolder(String folderName) {
		System.out.println("创建文件夹：" + folderName);
		String result;
		try {
			result = executeBatCommand("cmd.exe /c md " + folderName);
		} catch (IOException e) {
			throw WorkFlowException.build(e.getMessage() + "\n" + "创建命名空间文件夹发生异常", WorkFlowEnum.SCREENING_CONDITIONS);
		}
		System.out.println(result);
	}

	/**
	 * 下载Gitlab源工程
	 */
	public static void downloadGitlabRepository(String folder, String url) {
		System.out.println("开始下载" + url);
		long s = System.currentTimeMillis();
		try {
			String folderDir = System.getProperty("user.dir") + File.separator + String.join(File.separator, folder.split("/"));
			executeBatCommand("cmd.exe /c cd " + folderDir + " && git clone " + url);
		} catch (IOException e) {
			System.out.println("下载" + url + "发生异常：" + e.getMessage());
			return;
		}
		long e = System.currentTimeMillis();
		System.out.println(url + "下载完成，耗时：" + BigDecimal.valueOf(e - s).setScale(2, BigDecimal.ROUND_HALF_UP).divide(BigDecimal.valueOf(1000), BigDecimal.ROUND_HALF_UP) + "秒");
	}
}
