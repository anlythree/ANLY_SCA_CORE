package com.anlythree.common.exception;

import com.anlythree.common.api.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 通用异常
 *
 * @author pangu
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AnlyScaException extends RuntimeException {

	private static final long serialVersionUID = 5782968730281544562L;

	private ResultCode errorCode;

	private int status = INTERNAL_SERVER_ERROR.value();

	public AnlyScaException(String message) {
		super(message);
	}

	public AnlyScaException(ResultCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
