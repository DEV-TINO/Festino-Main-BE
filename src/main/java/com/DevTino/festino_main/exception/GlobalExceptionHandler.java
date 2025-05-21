package com.DevTino.festino_main.exception;

import com.DevTino.festino_main.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex) {

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        // 응답 리턴
        return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(response);

    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException e, HttpServletResponse response1) {
        if(response1.isCommitted()){
            return null;
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ExceptionEnum.INTERNAL_ERROR.getMessage(),null);


        // 응답 리턴
        return ResponseEntity.status(ExceptionEnum.INTERNAL_ERROR.getHttpStatus()).body(response);

    }

}