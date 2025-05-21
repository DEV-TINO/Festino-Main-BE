package com.DevTino.festino_main.exception;

import com.DevTino.festino_main.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex, HttpServletRequest req, HttpServletResponse res) {

        System.out.println("----------------- 에러 발생 [ServiceException.class] -----------------");
        System.out.println("에러 발생 경로 : " + req.getRequestURI());
        System.out.println("에러 발생 메세지 : " + ex.getMessage());
        System.out.println("에러 발생 코드 : " + ex.exceptionEnum.getCode());
        System.out.println("에러 발생 상태 : " + ex.exceptionEnum.getHttpStatus());
        System.out.println("에러 발생 클래스 : " + ex.getClass());
        System.out.println("에러 발생 시간 : " + System.currentTimeMillis());
        System.out.println("에러 발생 요청 메소드 : " + req.getMethod());
        System.out.println("에러 발생 요청 헤더 : " + req.getHeader("Authorization"));
        System.out.println("에러 발생 요청 파라미터 : " + req.getParameterMap());
        System.out.println("에러 발생 요청 바디 : " + req.getAttribute("body"));
        System.out.println("에러 발생 요청 IP : " + req.getRemoteAddr());
        System.out.println("에러 발생 요청 Host : " + req.getHeader("Host"));
        System.out.println("에러 발생 응답 상태 : " + res.getStatus());
        System.out.println("에러 발생 응답 헤더 : " + res.getHeaderNames());
        System.out.println("에러 발생 응답 바디 : " + res.getBufferSize());
        System.out.println("에러 발생 응답 메세지 : " + res.getStatus());
        System.out.println("-----------------------------------------------------------------");

        if (res.isCommitted()) {
            // 응답이 이미 커밋된 경우, 새로운 응답을 작성할 수 없음
            System.out.println("응답이 이미 커밋되었습니다.");
            return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(null);
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        // 응답 리턴
        return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(response);

    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException e, HttpServletRequest req, HttpServletResponse res) {

        System.out.println("----------------- 에러 발생 [RuntimeException.class] -----------------");
        System.out.println("에러 발생 경로 : " + req.getRequestURI());
        System.out.println("에러 발생 메세지 : " + ex.getMessage());
        System.out.println("에러 발생 코드 : " + ex.exceptionEnum.getCode());
        System.out.println("에러 발생 상태 : " + ex.exceptionEnum.getHttpStatus());
        System.out.println("에러 발생 클래스 : " + ex.getClass());
        System.out.println("에러 발생 시간 : " + System.currentTimeMillis());
        System.out.println("에러 발생 요청 메소드 : " + req.getMethod());
        System.out.println("에러 발생 요청 헤더 : " + req.getHeader("Authorization"));
        System.out.println("에러 발생 요청 파라미터 : " + req.getParameterMap());
        System.out.println("에러 발생 요청 바디 : " + req.getAttribute("body"));
        System.out.println("에러 발생 요청 IP : " + req.getRemoteAddr());
        System.out.println("에러 발생 요청 Host : " + req.getHeader("Host"));
        System.out.println("에러 발생 응답 상태 : " + res.getStatus());
        System.out.println("에러 발생 응답 헤더 : " + res.getHeaderNames());
        System.out.println("에러 발생 응답 바디 : " + res.getBufferSize());
        System.out.println("에러 발생 응답 메세지 : " + res.getStatus());
        System.out.println("-----------------------------------------------------------------");

        if (res.isCommitted()) {
            // 응답이 이미 커밋된 경우, 새로운 응답을 작성할 수 없음
            System.out.println("응답이 이미 커밋되었습니다.");
            return ResponseEntity.status(ex.exceptionEnum.getHttpStatus()).body(null);
        }

        // 성공 여부 실패로 설정, 예외 메시지 설정
        ApiResponse<Object> response = new ApiResponse<>(false, ExceptionEnum.INTERNAL_ERROR.getMessage(),null);


        // 응답 리턴
        return ResponseEntity.status(ExceptionEnum.INTERNAL_ERROR.getHttpStatus()).body(response);

    }

}