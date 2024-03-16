/**
 *
 */
package com.kenm.spring.farmleaseservice.exception;

/**
 * @author User
 *
 */
public class FarmLeaseServiceException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FarmLeaseServiceException(String message) {
        super(message);
    }

    public FarmLeaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
