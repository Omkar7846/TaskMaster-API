package com.TaskMaster.TaskMaster.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.TaskMaster.TaskMaster.Exceptions.ResourceNotFoundException;
import com.TaskMaster.TaskMaster.Model.DTO.ErrorDetailsDTO;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDateTime.now(), "Validation Failed", errors.toString(),
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetailsDTO> handleAccessDeniedException(AccessDeniedException exception,
			WebRequest webRequest) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDateTime.now(),
				"Access Denied: You do not have permission to perform this action.", webRequest.getDescription(false),
				HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ AuthenticationException.class, JwtException.class })
	public ResponseEntity<ErrorDetailsDTO> handleAuthenticationAndJwtException(RuntimeException exception,
			WebRequest webRequest) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDateTime.now(),
				"Authentication Failed: " + exception.getMessage(), webRequest.getDescription(false),
				HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetailsDTO> handleGlobalException(Exception exception, WebRequest webRequest) {

		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(LocalDateTime.now(),
				"An unexpected internal error occurred.", webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}