/**
 *
 */
package com.kenm.spring.farmservice.exception;

/**
 * @author User
 *
 */
public class FarmServiceException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FarmServiceException(String message) {
        super(message);
    }

    public FarmServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
