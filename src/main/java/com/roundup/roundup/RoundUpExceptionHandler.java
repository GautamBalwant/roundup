package com.roundup.roundup;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RoundUpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={HttpClientErrorException.BadRequest.class})
    public ResponseEntity<String> sss(HttpClientErrorException.BadRequest ex, WebRequest request)
    {
      //return  ResponseEntity.ok().build();
        return new ResponseEntity<String>("Palak error", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
