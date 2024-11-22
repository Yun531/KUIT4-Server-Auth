package com.kuit.kuit4serverauth.exception;

public enum ErrorCode {             //애플리케이션 전역에서 사용할 에러 코드 및 메시지를 정의
    INVALID_USERNAME_OR_PASSWORD(401, "Invalid username or password"),
    INVALID_TOKEN(401, "Invalid or expired token"),
    MISSING_AUTH_HEADER(401, "Missing or invalid Authorization header"),
    FORBIDDEN_ACCESS(403, "Access denied"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

