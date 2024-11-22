package com.kuit.kuit4serverauth.exception;

public class ErrorResponse {            //에러 응답을 표준화하기 위한 클래스
    private int status;         //HTTP 상태 코드
    private String message;     //오류 메시지
    private long timestamp;     //오류 발생 시각

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
