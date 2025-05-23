package com.DevTino.festino_main.group_order.domain.ENUM;

public enum AppMessageType {
    // 클라이언트 -> 서버
    INIT,           //
    MENUADD,        // 메뉴 수량 증가
    MENUSUB,        // 메뉴 수량 감소
    STARTORDER,     // 주문 시작
    UNSUB,          // 구독 취소 요청
    ORDERDONE,       // 주문 완료
    ORDERCANCEL,     // 주문 중 취소
    TIMEUPDATE     // 헬스체크
}
