package com.wuhenjian.tool.gitlabcloneassistant.service;

import com.wuhenjian.tool.gitlabcloneassistant.gitlab.GitlabAuthEnum;
import com.wuhenjian.tool.gitlabcloneassistant.gitlab.UrlToRepoEnum;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author 無痕剑
 * @date 2019/1/6 11:55
 */
public class InputService {

	private Scanner scanner;

	public InputService() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * 获取用户输入的URL地址
	 */
	public String getGitlabUrl() {
		System.out.println("请输入Gitlab服务URL：");
		return scanner.nextLine();
	}

	/**
	 * 获取用户选择的认证类型
	 */
	public GitlabAuthEnum getAuthType() {
		System.out.println("请输入Gitlab连接类型，1-TOKEN认证，其他字符为账号密码认证（默认）：");
		String userInput = scanner.nextLine();
		GitlabAuthEnum gitlabAuthEnum;
		if (Objects.equals(userInput, GitlabAuthEnum.TOKEN.getCode())) {
			gitlabAuthEnum = GitlabAuthEnum.TOKEN;
		} else {
			gitlabAuthEnum = GitlabAuthEnum.USERNAME_PASSWORD;
		}
		return gitlabAuthEnum;
	}

	/**
	 * 获取认证参数
	 */
	public String[] getAuthParam() {
		System.out.println("请输入认证参数，账号密码使用空格分隔（必填）：");
		String params = scanner.nextLine();
		return params.split("[ ]+");
	}

	/**
	 * 获取命名空间筛选条件
	 */
	public String getNamespace() {
		System.out.println("请输入命名空间完整名称（必填）：（格式：firstName/secondName/thirdName...，不以\"/\"开头和结尾）");
		return scanner.nextLine();
	}

	/**
	 * 获取工程名称筛选条件
	 */
	public String getProjectName() {
		System.out.println("请输入项目名称筛选条件（默认不过滤）：");
		return scanner.nextLine();
	}

	/**
	 * 获取下载方式
	 */
	public UrlToRepoEnum getDownloadUrlType() {
		System.out.println("请输入下载方式，SSH，其他字符为HTTP链接下载（默认）：");
		String input = scanner.nextLine();
		if (UrlToRepoEnum.SSH.name().equalsIgnoreCase(input)) {
			return UrlToRepoEnum.SSH;
		} else {
			return UrlToRepoEnum.HTTP;
		}
	}
}
