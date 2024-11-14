package com.aczg.controller

class ErrorResponse {
    String message
    String errorCode

    ErrorResponse(String message, String errorCode) {
        this.message = message
        this.errorCode = errorCode
    }
}
