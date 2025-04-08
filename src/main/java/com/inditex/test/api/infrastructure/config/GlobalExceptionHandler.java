package com.inditex.test.api.infrastructure.config;

import com.inditex.test.api.infrastructure.exception.ErrorCode;
import com.inditex.test.api.infrastructure.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(PriceNotFoundException.class)
        public ResponseEntity<?> handleNotFound(PriceNotFoundException ex) {
                return buildResponse(ErrorCode.PRICE_NOT_FOUND, ex.getMessage());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleBadRequest(Exception ex) {
                return buildResponse(ErrorCode.BAD_REQUEST, ex.getMessage());
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
                return buildResponse(ErrorCode.BAD_REQUEST, "Missing required parameter: " + ex.getParameterName());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleInternalError(Exception ex) {
                return buildResponse(ErrorCode.INTERNAL_ERROR, ex.getMessage());
        }

        private ResponseEntity<?> buildResponse(ErrorCode error, String customMessage) {
                return ResponseEntity
                    .status(Integer.parseInt(error.getCode()))
                    .body(Map.of(
                        "error", error.getTitle(),
                        "message", customMessage != null ? customMessage : error.getDefaultMessage(),
                        "timestamp", LocalDateTime.now(),
                        "code", error.getCode()
                    ));
        }
}