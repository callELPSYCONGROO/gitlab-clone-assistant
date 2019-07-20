package com.wuhenjian.tool.gitlabcloneassistant.service;

import com.wuhenjian.tool.gitlabcloneassistant.cmd.CmdUtil;
import com.wuhenjian.tool.gitlabcloneassistant.util.CommonUtil;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
		CmdUtil.createDir(folderName);

		CountDownLatch countDownLatch = new CountDownLatch(gitUrlList.size());

		// 执行线程
		gitUrlList.forEach(url -> CommonUtil.DOWNLOAD_THREAD_POOL.execute(() -> {
			try {
				CmdUtil.downloadGitlabRepository(folderName, url);
			} catch (Exception e) {
				System.out.println("下载" + url + "失败：" + e.getMessage());
			} finally {
				countDownLatch.countDown();
			}
		}));

		try {
			countDownLatch.await(9, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("这么久都没下载完成，检查一下链接吧");
		}

		CommonUtil.DOWNLOAD_THREAD_POOL.shutdown();
	}
}
