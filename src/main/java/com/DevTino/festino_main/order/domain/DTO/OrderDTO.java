package com.DevTino.festino_main.order.domain.DTO;

import com.DevTino.festino_main.order.domain.OrderType;
import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderNum;

    UUID orderId;
    UUID boothId;
    OrderType orderType;
    Integer tableNum;
    Integer date;
    String userName;
    String phoneNum;
    Integer totalPrice;
    LocalDateTime createAt;
    Boolean isCoupon;
    Boolean isDeposit;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;
}
