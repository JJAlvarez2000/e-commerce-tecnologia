package org.jalvarez.apiservlet.webapp.headers.exceptions;

public class ServiceJDBCException extends RuntimeException {
    public ServiceJDBCException(String message) {
        super(message);
    }

    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}

