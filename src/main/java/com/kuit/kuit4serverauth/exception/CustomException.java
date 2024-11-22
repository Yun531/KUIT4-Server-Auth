package com.kuit.kuit4serverauth.exception;

public class CustomException extends RuntimeException {             //사용자 정의 예외를 처리하기 위한 클래스.
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return errorCode.getStatus();
    }

    public String getErrorMessage() {
        return errorCode.getMessage();
    }
}