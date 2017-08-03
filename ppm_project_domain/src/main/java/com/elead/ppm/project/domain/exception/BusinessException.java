package com.elead.ppm.project.domain.exception;

/**
 * 业务异常.
 *
 * @author wangxz
 */
public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}