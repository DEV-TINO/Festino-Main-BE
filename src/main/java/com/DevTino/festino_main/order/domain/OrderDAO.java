package com.DevTino.festino_main.order.domain;

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
public class OrderDAO {
    @Id
    UUID orderId;

    UUID boothId;

    Integer tableNum;
    String userName;
    String phoneNum;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    Integer totalPrice;

    LocalDateTime createAt;

    Boolean isCoupon;
    Boolean isDeleted;
}
