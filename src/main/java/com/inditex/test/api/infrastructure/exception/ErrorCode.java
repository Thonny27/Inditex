package com.inditex.test.api.infrastructure.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

        PRICE_NOT_FOUND("404", "Not Found", "No price found for the given input."),
        BAD_REQUEST("400", "Bad Request", "Invalid input provided."),
        INTERNAL_ERROR("500", "Internal Server Error", "An unexpected error occurred.");

        private final String code;
        private final String title;
        private final String defaultMessage;

        ErrorCode(String code, String title, String defaultMessage) {
                this.code = code;
                this.title = title;
                this.defaultMessage = defaultMessage;
        }
}
