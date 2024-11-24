package com.promineotech.pet.store.controller.error;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;


import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice 
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String,String> NoSuchElementException(NoSuchElementException ex){
		log.error("Error Message {}", ex.toString());
		return Collections.singletonMap("message", ex.toString());
	}
}
