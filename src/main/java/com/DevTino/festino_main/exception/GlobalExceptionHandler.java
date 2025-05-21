package com.DevTino.festino_main.exception;

import com.DevTino.festino_main.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex, HttpServletRequest request, HttpServletResponse response1) {
        System.out.println("-------------------------------------------------GlobalError111111111111111111111111111111");

        System.out.println("request.getPathInfo() = " + request.getPathInfo());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("response1.getHeaderNames() = " + response1.getHeaderNames());


        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        // 응답 리턴
        return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(response);

    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException e, HttpServletRequest request, HttpServletResponse response1) {

        System.out.println("-------------------------------------------------GlobalError");

        System.out.println("request.getPathInfo() = " + request.getPathInfo());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("response1.getHeaderNames() = " + response1.getHeaderNames());


        if(response1.isCommitted()){
            return null;
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ExceptionEnum.INTERNAL_ERROR.getMessage(),null);


        // 응답 리턴
        return ResponseEntity.status(ExceptionEnum.INTERNAL_ERROR.getHttpStatus()).body(response);

    }

}