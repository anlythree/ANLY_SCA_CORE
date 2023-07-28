package com.anlythree.common.exception;


/**
 * 获取锁处理异常
 *
 * @author anlythree
 */
public class RedissonLockException extends AnlyScaException {

    private static final long serialVersionUID = -6422212844622271825L;

    public RedissonLockException(String message) {
        super(message);
    }
}
