package com.example.demo.Exceptins;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handlevalidapiexpcetion( MethodArgumentNotValidException ex) {
		Map<String, String> errorsMap= new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(errors ->{
			errorsMap.put(errors.getField(),errors.getDefaultMessage());
		});
		
		Map<String, Object> responceMap= new HashMap<String, Object>();
		
		responceMap.put("result","sucess");
		responceMap.put("errors", errorsMap);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responceMap);
		
		
		
	}
	@ExceptionHandler(Exception.class)
	public Object handleexceptionhandler(Exception ex) {
		Map<String, Object> responcemap= new HashMap<String, Object>();
		responcemap.put("result","sucess");
		responcemap.put("errors", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responcemap);
		
		
	}
	
	

}
