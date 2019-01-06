package com.wuhenjian.tool.gitlabcloneassistant.service;

import com.wuhenjian.tool.gitlabcloneassistant.cmd.CmdUtil;
import com.wuhenjian.tool.gitlabcloneassistant.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 無痕剑
 * @date 2019/1/6 20:50
 */
public class CmdService {

	/**
	 * 下载项目
	 */
	public void download(List<String> gitUrlList, String folderName) {
		// 创建文件夹
		CmdUtil.createFolder(folderName);
		List<Callable<String>> callableList = new ArrayList<>();
		// 下载过程封装为线程对象
		gitUrlList.forEach(url -> callableList.add(() -> CmdUtil.downloadGitlabRepository(folderName, url)));
		List<Future<String>> futureList = new ArrayList<>();
		// 执行线程
		callableList.forEach(callable -> futureList.add(CommonUtil.DOWNLOAD_THREAD_POOL.submit(callable)));
		// 是否全部完成
		boolean hasNotDone = futureList.stream().anyMatch(future -> !future.isDone());
		while (hasNotDone) {
			hasNotDone = futureList.stream().anyMatch(future -> !future.isDone());
		}
		// 全部完成后，输出结果
		futureList.forEach(future -> {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});

		// 关闭线程池
		CommonUtil.DOWNLOAD_THREAD_POOL.shutdown();
	}
}
