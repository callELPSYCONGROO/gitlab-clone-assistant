package com.wuhenjian.tool.gitlabcloneassistant;

import com.wuhenjian.tool.gitlabcloneassistant.gitlab.GitlabApiUtil;
import com.wuhenjian.tool.gitlabcloneassistant.gitlab.GitlabAuthEnum;
import com.wuhenjian.tool.gitlabcloneassistant.gitlab.UrlToRepoEnum;
import com.wuhenjian.tool.gitlabcloneassistant.service.CmdService;
import com.wuhenjian.tool.gitlabcloneassistant.service.InputService;
import com.wuhenjian.tool.gitlabcloneassistant.util.CommonUtil;
import org.gitlab4j.api.GitLabApi;

import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/1/6 0:04
 */
public class StartMain {

	public static void main(String[] args) {
		System.out.println("欢迎使用Gitlab下载助手2.0！");
		System.out.println("【使用回车键完成输入】");
		// 输入业务对象
		InputService inputService = new InputService();
		// 获取连接URL，GITLAB_URL
		String gitlabUrl = inputService.getGitlabUrl();
		// 获取认证类型，AUTHTYPE
		GitlabAuthEnum authType = inputService.getAuthType();
		System.out.println("选择[" + authType.getMsg() + "]登录");
		// 获取认证参数，AUTH_PARAMS
		String[] authParam = inputService.getAuthParam();
		// 通过输入获取gitLabApi实例，GITLAB_API
		GitLabApi gitLabApi = GitlabApiUtil.connect(gitlabUrl, authType, authParam);
		// 获取项目过滤条件，SCREENING_CONDITIONS
		String namespace = inputService.getNamespace();
		System.out.println("命名空间名称：" + namespace);
		String projectName = inputService.getProjectName();
		System.out.println("项目过滤条件为：" + projectName);
		// 获取项目下载方式，DOWNLOAD_TYPE
		UrlToRepoEnum urlToRepoEnum = inputService.getDownloadUrlType();
		System.out.println("项目下载协议：" + urlToRepoEnum.name());
		// 获取下载链接，GIT_CLONE_HOLDER
		List<String> gitUrlList = GitlabApiUtil.getGitUrl(gitLabApi, namespace, projectName, urlToRepoEnum);
		// 没有符合条件的项目，则结束
		if (CommonUtil.collectionIsBlank(gitUrlList)) {
			System.out.println("没有对应的工程，结束进程");
			return;
		}

		// 命令业务对象
		CmdService cmdService = new CmdService();
		// 下载工程
		cmdService.download(gitUrlList, namespace);

		System.out.println("全部完成，进程结束...");
	}
}
