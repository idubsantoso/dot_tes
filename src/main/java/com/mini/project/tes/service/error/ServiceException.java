package com.mini.project.tes.service.error;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
