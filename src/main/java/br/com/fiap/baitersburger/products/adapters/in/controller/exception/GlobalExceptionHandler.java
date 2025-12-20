package br.com.fiap.baitersburger.products.adapters.in.controller.exception;

import br.com.fiap.baitersburger.products.core.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex){
        Map<String, Object> errorBodyObject = new HashMap<>();
        errorBodyObject.put("timestamp", LocalDateTime.now());
        errorBodyObject.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBodyObject);
    }
}
