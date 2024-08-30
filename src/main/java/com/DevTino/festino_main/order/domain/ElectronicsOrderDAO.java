package com.DevTino.festino_main.order.domain;

import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
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
public class ElectronicsOrderDAO {
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
    Boolean isService;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    public static ElectronicsOrderDAO fromOrderDTO(OrderDTO orderDTO) {
        return ElectronicsOrderDAO.builder()
                .orderId(orderDTO.getOrderId())
                .boothId(orderDTO.getBoothId())
                .orderType(orderDTO.getOrderType())
                .tableNum(orderDTO.getTableNum())
                .date(orderDTO.getDate())
                .userName(orderDTO.getUserName())
                .phoneNum(orderDTO.getPhoneNum())
                .totalPrice(orderDTO.getTotalPrice())
                .createAt(orderDTO.getCreateAt())
                .isCoupon(orderDTO.getIsCoupon())
                .isDeposit(orderDTO.getIsDeposit())
                .isService(orderDTO.getIsService())
                .menuInfo(orderDTO.getMenuInfo())
                .build();
    }
}