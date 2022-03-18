package com.example.csvReader.config;

import com.example.csvReader.exception.FolderException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
   //other exception handlers
  
   @ExceptionHandler(FolderException.class)
   @ResponseBody
   @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
   protected ResponseEntity<Error> handleEntityNotFound(FolderException ex) {
      final Error error = new Error(ex.getMessage());
      return new ResponseEntity<Error>(error, HttpStatus.PRECONDITION_FAILED);
   }

   @Data
   @AllArgsConstructor
   class Error {
      private String message;
   }
}