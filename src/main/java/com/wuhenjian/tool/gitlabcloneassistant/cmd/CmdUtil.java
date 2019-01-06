package com.wuhenjian.tool.gitlabcloneassistant.cmd;

import com.wuhenjian.tool.gitlabcloneassistant.workflow.WorkFlowEnum;
import com.wuhenjian.tool.gitlabcloneassistant.workflow.exception.WorkFlowException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * bat脚本运行工具类
 * @author 無痕剑
 * @date 2019/1/6 0:12
 */
public class CmdUtil {

	private static String excuteBatCommand(String command) throws IOException {
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
	 * 创建文件夹
	 */
	public static void createFolder(String folderName) {
		System.out.println("创建文件夹：" + folderName);
		String result;
		try {
			result = excuteBatCommand("cmd.exe /c md " + folderName);
		} catch (IOException e) {
			throw WorkFlowException.build(e.getMessage() + "\n" + "创建命名空间文件夹发生异常", WorkFlowEnum.SCREENING_CONDITIONS);
		}
		System.out.println(result);
	}

	/**
	 * 下载Gitlab源工程
	 */
	public static String downloadGitlabRepository(String folder, String url) {
		System.out.println("开始下载" + url);
		try {
			excuteBatCommand("cmd.exe /c cd " + folder + " && git clone " + url);
			return url + "下载完成";
		} catch (IOException e) {
			return url + "下载信息：\n" + e.getMessage() + "\n" + "下载文件发生异常";
		}
	}
}
