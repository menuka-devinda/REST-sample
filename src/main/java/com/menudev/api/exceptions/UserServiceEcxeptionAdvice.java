package com.menudev.api.exceptions;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

@ControllerAdvice
//@Sl4j
public class UserServiceEcxeptionAdvice {

	public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
		return error(INTERNAL_SERVER_ERROR, e);
	}
	
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
  //      log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
