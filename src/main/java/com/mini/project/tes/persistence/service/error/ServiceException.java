package com.mini.project.tes.persistence.service.error;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
