package ai.jamin.common;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ai.jamin.common.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException exception) {
		log.warn("IllegalArgumentException 발생 : {}", exception.getMessage(), exception);
		return ResponseEntity.badRequest()
			.body(ExceptionResponse.from(exception));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionResponse> noSuchElementException(NoSuchElementException exception) {
		log.warn("NoSuchElementException 발생 : {}", exception.getMessage(), exception);
		return ResponseEntity.badRequest().body(ExceptionResponse.from(exception));
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		StringBuilder stringBuilder = new StringBuilder();
		bindingResult.getAllErrors().forEach(error -> {
			FieldError fieldError = (FieldError)error;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			stringBuilder.append(fieldName).append(" : ").append(message).append(", ");
			log.error("MethodArgumentNotValidException 발생: {} \n{}", fieldName + ": " + message, error);
		});
		return ResponseEntity.badRequest().body(new ExceptionResponse(stringBuilder.toString()));
	}
}
