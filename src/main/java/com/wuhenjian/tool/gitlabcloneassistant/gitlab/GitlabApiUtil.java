package com.wuhenjian.tool.gitlabcloneassistant.gitlab;

import com.wuhenjian.tool.gitlabcloneassistant.workflow.exception.WorkFlowException;
import com.wuhenjian.tool.gitlabcloneassistant.util.CommonUtil;
import com.wuhenjian.tool.gitlabcloneassistant.workflow.WorkFlowEnum;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * gitlab-api工具
 * @author 無痕剑
 * @date 2019/1/6 1:04
 */
public class GitlabApiUtil {

	/**
	 * 连接Gitlab服务器
	 * @param url Gitlab的URL
	 * @param authEnum 认证类型
	 * @param auths 认证参数
	 * @return GitLabApi instance
	 */
	public static GitLabApi connect(String url, GitlabAuthEnum authEnum, String... auths) throws WorkFlowException {
		GitLabApi gitLabApi;
		switch (authEnum) {
			case TOKEN:
				if (auths == null || auths.length != 1) {
					throw WorkFlowException.build("认证参数错误，TOKEN认证时，认证参数只允许输入一个", WorkFlowEnum.AUTHTYPE);
				}
				gitLabApi = new GitLabApi(GitLabApi.ApiVersion.V3, url, auths[0]);
				break;
			case USERNAME_PASSWORD:
				if (auths == null || auths.length != 2) {
					throw WorkFlowException.build("认证参数错误，账号密码认证时，认证参数必须输入两个", WorkFlowEnum.AUTHTYPE);
				}
				try {
//					gitLabApi = GitLabApi.login(GitLabApi.ApiVersion.V3, url, auths[0], auths[1]);
					gitLabApi = GitLabApi.oauth2Login(url, auths[0], auths[1]);
				} catch (GitLabApiException e) {
					throw WorkFlowException.build(e.getMessage() + "\n" + "账号密码连接异常...", WorkFlowEnum.GITLAB_URL);
				}
				break;
			default:
				throw new RuntimeException("认证类型错误");
		}
		gitLabApi.setDefaultPerPage(100);
		return gitLabApi;
	}

	/**
	 * 筛选过后的git下载地址
	 * @param gitLabApi 连接对象
	 * @param projectName 项目名称
	 * @param namespace 命名空间
	 * @param urlToRepoEnum 下载方式
	 * @return 下载连接
	 */
	public static List<String> getGitUrl(GitLabApi gitLabApi, String namespace, String projectName, UrlToRepoEnum urlToRepoEnum) throws WorkFlowException {
		ProjectApi projectApi = gitLabApi.getProjectApi();
		List<Project> projectList;
		try {
			// 先使用项目名称过滤
			if (CommonUtil.stringIsBlank(projectName)) {
				projectList = projectApi.getProjects();
			} else {
				projectList = projectApi.getProjects(projectName);
			}
			return projectList.stream()
					// 使用命名空间过滤
					.filter(project -> Objects.equals(project.getNamespace().getName(), namespace))
					// 获取到每个工程的下载地址
					.map(project -> {
						switch (urlToRepoEnum) {
							case SSH:
								return project.getSshUrlToRepo();
							default:
								return project.getHttpUrlToRepo();
						}
					})
					.collect(Collectors.toList());
		} catch (GitLabApiException e) {
			throw WorkFlowException.build(e.getMessage() + "\n" + "获取工程项目信息发生异常", WorkFlowEnum.SCREENING_CONDITIONS);
		}
	}
}
