package org.aguzman.apiservlet.webapp.headers.services;

public class ServiceJDBCException extends RuntimeException {
    public ServiceJDBCException(String message) {
        super(message);
    }

    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
