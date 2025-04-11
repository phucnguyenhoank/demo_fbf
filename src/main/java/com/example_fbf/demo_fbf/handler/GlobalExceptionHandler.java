package com.example_fbf.demo_fbf.handler;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("exception", ex.getClass().getSimpleName());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("timestamp", new Date());
        errorDetails.put("path", request.getRequestURI());

        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, "Invalid input", errorDetails));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleGeneral(Exception ex, HttpServletRequest request) {
        ex.printStackTrace(); // ✅ In ra lỗi chi tiết trong log

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("exception", ex.getClass().getSimpleName());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("timestamp", new Date());
        errorDetails.put("path", request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Internal server error", errorDetails));
    }
}

