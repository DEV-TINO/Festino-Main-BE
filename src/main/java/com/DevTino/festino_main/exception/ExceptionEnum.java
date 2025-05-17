package com.DevTino.festino_main.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    // 커스텀 예외
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E001", "해당 데이터를 찾을 수 없습니다."),
    EMPTY_LIST(HttpStatus.NO_CONTENT, "E002", "조건에 해당하는 항목이 없습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E003", "입력값이 올바르지 않습니다."),
//    STATUS_MISMATCH(HttpStatus.CONFLICT, "E004", "상태 정보가 일치하지 않습니다."),
    ALREADY_PROCESSED(HttpStatus.CONFLICT, "E005", "이미 처리된 요청입니다."),
    BOOTH_CLOSED(HttpStatus.FORBIDDEN, "E006", "부스가 닫혀 있습니다."),
    ORDER_DISABLED(HttpStatus.FORBIDDEN, "E007", "주문이 비활성화된 상태입니다."),
//    RESERVATION_ALREADY_EXIST(HttpStatus.CONFLICT, "E008", "이미 예약이 존재합니다."),
//    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "E009", "비밀번호가 일치하지 않습니다."),
    TOSSPAY_DISABLED(HttpStatus.FORBIDDEN, "E010", "토스페이가 비활성화된 상태입니다."),
    KAKAOPAY_DISABLED(HttpStatus.FORBIDDEN, "E011", "카카오페이가 비활성화된 상태입니다."),
    NO_COOKIES_PRESENT(HttpStatus.BAD_REQUEST, "E012", "요청에 쿠키가 포함되어 있지 않습니다."),
    COOKIE_NOT_FOUND_BY_NAME(HttpStatus.BAD_REQUEST, "E013", "요청에 해당 이름의 쿠키가 존재하지 않습니다."),
    MAIN_USERID_MISMATCH(HttpStatus.UNAUTHORIZED, "E014", "유저아이디가 일치하지 않습니다."),
    ORDER_RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "E015", "요청 한도를 초과했습니다. 잠시 후 다시 시도해 주세요."),
    CSRF_TOKEN_MISSING(HttpStatus.BAD_REQUEST, "E016", "CSRF 토큰이 요청에 포함되어 있지 않습니다."),
    CSRF_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "E017", "CSRF 토큰이 만료되었습니다."),
    RESERVATION_DISABLED(HttpStatus.FORBIDDEN, "E018", "예약이 비활성화된 상태입니다."),
    AUTHCODE_MISMATCH(HttpStatus.UNAUTHORIZED, "E019", "인증코드가 일치하지 않습니다."),

    // 공통 예외
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E999", "서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ExceptionEnum(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}
