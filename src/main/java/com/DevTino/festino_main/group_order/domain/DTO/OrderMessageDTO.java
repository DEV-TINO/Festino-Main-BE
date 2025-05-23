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
public class OrderMessageDTO<T> {
    String type;
    UUID boothId;
    Integer tableNum;
    String clientId;

    // 메시지 타입에 따라 저장되는 객체 변환
    T payload;
}
