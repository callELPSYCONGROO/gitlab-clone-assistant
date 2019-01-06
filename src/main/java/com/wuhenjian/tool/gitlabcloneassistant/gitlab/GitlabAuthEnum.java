package com.wuhenjian.tool.gitlabcloneassistant.gitlab;

import lombok.Getter;

import java.util.Objects;

/**
 * Gitlab认证类型
 * @author 無痕剑
 * @date 2019/1/6 1:08
 */
@Getter
public enum GitlabAuthEnum {

	/** ssh token */
	TOKEN("1"),
	/** 账号密码 */
	USERNAME_PASSWORD("2"),
	;

	private String code;

	GitlabAuthEnum(String code) {
		this.code = code;
	}

	public static GitlabAuthEnum getEnumByCode(String code) {
		for (GitlabAuthEnum gitlabAuthEnum : GitlabAuthEnum.values()) {
			if (Objects.equals(gitlabAuthEnum.getCode(), code)) {
				return gitlabAuthEnum;
			}
		}
		return null;
	}
}
