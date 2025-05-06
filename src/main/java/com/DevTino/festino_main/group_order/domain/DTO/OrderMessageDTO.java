package com.DevTino.festino_main.group_order.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessageDTO {
    Object type;
    UUID boothId;
    Integer tableNum;

    // 메시지 타입에 따라 포함되는 정보 객체
    InitInfo initInfo;       // 초기화 정보 (INIT 타입)
    MenuInfo menuInfo;       // 메뉴 정보 (MENUUPDATE 타입)
    MemberInfo memberInfo;   // 멤버 정보 (MEMBERUPDATE 타입)
}
