package com.wuhenjian.tool.gitlabcloneassistant.util;

import java.util.Collection;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 無痕剑
 * @date 2019/1/6 12:48
 */
public class CommonUtil {

	public final static ThreadPoolExecutor DOWNLOAD_THREAD_POOL = new ThreadPoolExecutor(
			5,
			Integer.MAX_VALUE,
			10,
			TimeUnit.MINUTES,
			new SynchronousQueue<>());

	public static boolean stringIsBlank(String string) {
		return string == null || string.length() == 0 || string.matches("[ ]+");
	}

	public static boolean collectionIsBlank(Collection collection) {
		return collection == null || collection.isEmpty();
	}
}
