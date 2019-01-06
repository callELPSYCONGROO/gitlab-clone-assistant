package com.wuhenjian.tool.gitlabcloneassistant.workflow.exception;

import com.wuhenjian.tool.gitlabcloneassistant.workflow.WorkFlowEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务流程异常
 * @author 無痕剑
 * @date 2019/1/6 13:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkFlowException extends RuntimeException {

	/** 操作失败返回的流程节点 */
	private WorkFlowEnum fallbackPosition;

	/** 错误信息 */
	private String message;

	private WorkFlowException(String message, WorkFlowEnum fallbackPosition) {
		super(message);
		this.fallbackPosition = fallbackPosition;
		this.message = message;
	}

	public static WorkFlowException build(String message, WorkFlowEnum fallbackPosition) {
		return new WorkFlowException(message, fallbackPosition);
	}
}
