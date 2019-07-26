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
	TOKEN("1", "Token认证"),
	/** 账号密码 */
	USERNAME_PASSWORD("2", "账号密码"),
	;

	private final String code;

	private final String msg;

	GitlabAuthEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
