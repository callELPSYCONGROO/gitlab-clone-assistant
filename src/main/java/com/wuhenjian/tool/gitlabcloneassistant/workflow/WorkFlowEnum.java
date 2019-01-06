package com.wuhenjian.tool.gitlabcloneassistant.workflow;

/**
 * @author 無痕剑
 * @date 2019/1/6 13:01
 */
public enum WorkFlowEnum {

	/** 输入Gitlab的Url */
	GITLAB_URL,
	/** 输入认证类型 */
	AUTHTYPE,
	/** 输入认证参数 */
	AUTH_PARAMS,
	/** 获得GITLAB_API实例 */
	GITLAB_API,
	/** 输入项目、命名空间筛选条件 */
	SCREENING_CONDITIONS,
	/** 选择下载方式 */
	DOWNLOAD_TYPE,
	/** 获取下载连接 */
	GIT_CLONE_HOLDER,
	;
}
