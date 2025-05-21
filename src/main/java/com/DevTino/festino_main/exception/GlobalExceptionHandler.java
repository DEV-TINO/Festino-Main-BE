package com.DevTino.festino_main.exception;

import com.DevTino.festino_main.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex, HttpServletRequest req, HttpServletResponse res) {


        System.out.println("----------------- Error occured in [ServiceException.class] -----------------");

        System.out.println("Request URI              : " + req.getRequestURI());
        System.out.println("Error Message            : " + ex.getMessage());
        System.out.println("Exception Class          : " + ex.getClass().getName());
        System.out.println("Timestamp                : " + System.currentTimeMillis());
        System.out.println("HTTP Method              : " + req.getMethod());

        System.out.println("Request Header [Auth]    : " + req.getHeader("Authorization"));
        System.out.println("Request Parameters       : " + req.getParameterMap());
        System.out.println("Request Attribute [body] : " + req.getAttribute("body"));
        System.out.println("Client IP Address        : " + req.getRemoteAddr());
        System.out.println("Request Host             : " + req.getHeader("Host"));

        System.out.println("Response Status Code     : " + res.getStatus());
        System.out.println("Response Header Names    : " + res.getHeaderNames());
        System.out.println("Response Buffer Size     : " + res.getBufferSize());
        System.out.println("Response Status Message  : " + res.getStatus());

        System.out.println("-----------------------------------------------------------------");

        if (res.isCommitted()) {
            // 응답이 이미 커밋된 경우, 새로운 응답을 작성할 수 없음
            System.out.println("The response has already been committed.");
            System.out.println("--------------------------------------------------");
            return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(null);
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        // 응답 리턴
        return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(response);

    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex, HttpServletRequest req, HttpServletResponse res) {


        System.out.println("----------------- Error occured in [RuntimeException.class] -----------------");

        System.out.println("Request URI              : " + req.getRequestURI());
        System.out.println("Error Message            : " + ex.getMessage());
        System.out.println("Exception Class          : " + ex.getClass().getName());
        System.out.println("Timestamp                : " + System.currentTimeMillis());
        System.out.println("HTTP Method              : " + req.getMethod());

        System.out.println("Request Header [Auth]    : " + req.getHeader("Authorization"));
        System.out.println("Request Parameters       : " + req.getParameterMagip());
        System.out.println("Request Attribute [body] : " + req.getAttribute("body"));
        System.out.println("Client IP Address        : " + req.getRemoteAddr());
        System.out.println("Request Host             : " + req.getHeader("Host"));

        System.out.println("Response Status Code     : " + res.getStatus());
        System.out.println("Response Header Names    : " + res.getHeaderNames());
        System.out.println("Response Buffer Size     : " + res.getBufferSize());
        System.out.println("Response Status Message  : " + res.getStatus());

        System.out.println("-----------------------------------------------------------------");
        
        if (res.isCommitted()) {
            // 응답이 이미 커밋된 경우, 새로운 응답을 작성할 수 없음
            System.out.println("The response has already been committed.");
            System.out.println("--------------------------------------------------");
            return ResponseEntity.status(ExceptionEnum.INTERNAL_ERROR.getHttpStatus()).body(null);
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ExceptionEnum.INTERNAL_ERROR.getMessage(),null);


        // 응답 리턴
        return ResponseEntity.status(ExceptionEnum.INTERNAL_ERROR.getHttpStatus()).body(response);

    }

}