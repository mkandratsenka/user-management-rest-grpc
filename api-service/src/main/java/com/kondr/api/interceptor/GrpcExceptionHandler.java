package com.kondr.api.interceptor;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GrpcExceptionHandler {

  private static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error";

  @ExceptionHandler(StatusRuntimeException.class)
  public ResponseEntity<?> handleGrpcException(StatusRuntimeException e) {
    return Status.Code.NOT_FOUND == e.getStatus().getCode()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(UNEXPECTED_ERROR_MESSAGE);
  }

}