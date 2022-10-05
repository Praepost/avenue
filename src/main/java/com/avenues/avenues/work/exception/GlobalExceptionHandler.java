package com.avenues.avenues.work.exception;

import com.avenues.avenues.work.exception.response.ErrorResponse;
import com.avenues.avenues.work.exception.response.ErrorResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private ErrorResponseBuilder errorResponseBuilder;

    //EXAMPLE
    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> fileStorageException(RuntimeException ex, WebRequest request) {
        log.error("can't save file", ex);
        ErrorResponse errorResponse = errorResponseBuilder.builder()
                .setErrorCode(CommonErrorCodes.FILE)
                .setDescription("file path error: " + ex.getMessage())
                .setMessage("file saving failed")
                .build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyFileNotFoundException.class)
    public ResponseEntity<ErrorResponse> myFileNotFoundException(RuntimeException ex, WebRequest request) {
        log.error("can't handle data", ex);
        ErrorResponse errorResponse = errorResponseBuilder.builder()
                .setErrorCode(CommonErrorCodes.FILE)
                .setDescription("file find error: " + ex.getMessage())
                .setMessage("upload file failed")
                .build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest request) {
        log.error("general error occurred", ex);
        ErrorResponse errorResponse = errorResponseBuilder.builder()
                .setErrorCode(CommonErrorCodes.GENERAL)
                .setDescription("general error: " + ex.getMessage())
                .setMessage("operation failed")
                .build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(Exception ex, WebRequest request) {
        log.error("duplicate error occurred", ex);
        ErrorResponse errorResponse = errorResponseBuilder.builder()
                .setErrorCode(CommonErrorCodes.DUPLICATE)
                .setDescription("duplicate error: " + ex.getMessage())
                .setMessage("operation failed")
                .build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}