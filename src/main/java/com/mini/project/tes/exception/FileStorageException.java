package com.mini.project.tes.exception;

public class FileStorageException extends Throwable {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
