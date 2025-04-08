package com.inditex.test.api.infrastructure.config;

import com.inditex.test.api.infrastructure.exception.ErrorCode;
import com.inditex.test.api.infrastructure.exception.PriceNotFoundException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

        private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

        @Test
        void handleNotFound_ShouldReturnNotFoundResponse() {
                PriceNotFoundException exception = new PriceNotFoundException(1L, 1L, "2023-01-01T00:00:00");

                ResponseEntity<?> response = handler.handleNotFound(exception);

                assertEquals(404, response.getStatusCodeValue());
                Map<?, ?> body = (Map<?, ?>) response.getBody();
                assertNotNull(body);
                assertEquals(ErrorCode.PRICE_NOT_FOUND.getTitle(), body.get("error"));
        }

        @Test
        void handleBadRequest_ShouldReturnBadRequestResponse() {
                IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

                ResponseEntity<?> response = handler.handleBadRequest(exception);

                assertEquals(400, response.getStatusCodeValue());
                Map<?, ?> body = (Map<?, ?>) response.getBody();
                assertNotNull(body);
                assertEquals(ErrorCode.BAD_REQUEST.getTitle(), body.get("error"));
        }

        @Test
        void handleInternalError_ShouldReturnInternalErrorResponse() {
                Exception exception = new Exception("Unexpected error");

                ResponseEntity<?> response = handler.handleInternalError(exception);

                assertEquals(500, response.getStatusCodeValue());
                Map<?, ?> body = (Map<?, ?>) response.getBody();
                assertNotNull(body);
                assertEquals(ErrorCode.INTERNAL_ERROR.getTitle(), body.get("error"));
        }

        @Test
        void handleMissingParams_ShouldReturnBadRequestResponse() {
                MissingServletRequestParameterException exception =
                    new MissingServletRequestParameterException("productId", "Long");

                ResponseEntity<?> response = handler.handleMissingParams(exception);

                assertEquals(400, response.getStatusCodeValue());
                Map<?, ?> body = (Map<?, ?>) response.getBody();
                assertNotNull(body);
                assertEquals(ErrorCode.BAD_REQUEST.getTitle(), body.get("error"));
                assertEquals("Missing required parameter: productId", body.get("message"));
                assertNotNull(body.get("timestamp"));
                assertEquals(ErrorCode.BAD_REQUEST.getCode(), body.get("code"));
        }
}