package com.DevTino.festino_main.group_order.domain.ENUM;

public enum TopicMessageType {
    // 서버 -> 클라이언트
    INIT,           // 초기 정보
    MENUUPDATE,     // 메뉴 수 변경
    MEMBERUPDATE,   // 멤버 수 변경
    UNSUB,          // 구독취소
    STARTORDER,     // 주문시작
    TIMEUPDATE,     // 세션 1분마다 업데이트
    PRESESSIONEND,  // 세션 종료 전
    SESSIONEND,      // 세션 종료
    ERROR,           // 에러 메시지
    ORDERDONE,      // 주문 완료
    ORDERCANCEL     // 주문 중 취소
}
