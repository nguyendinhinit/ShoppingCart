package com.rookies.nashtech.ShoppingCart.exception;

import com.rookies.nashtech.ShoppingCart.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex, HttpServletRequest request) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    ErrorDTO notFound = new ErrorDTO(httpStatus.toString(), ex.getMessage(), request.getRequestURI());
    return new ResponseEntity<>(notFound, httpStatus);
  }
}
