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
public class NewMaterialOrderDAO {
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
    String note;
    Integer totalPrice;
    LocalDateTime createAt;
    Boolean isCoupon;
    Boolean isDeposit;
    Boolean isService;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    public static NewMaterialOrderDAO fromOrderDTO(OrderDTO orderDTO) {
        return NewMaterialOrderDAO.builder()
                .orderId(orderDTO.getOrderId())
                .boothId(orderDTO.getBoothId())
                .orderType(orderDTO.getOrderType())
                .tableNum(orderDTO.getTableNum())
                .date(orderDTO.getDate())
                .userName(orderDTO.getUserName())
                .phoneNum(orderDTO.getPhoneNum())
                .note(orderDTO.getNote())
                .totalPrice(orderDTO.getTotalPrice())
                .createAt(orderDTO.getCreateAt())
                .isCoupon(orderDTO.getIsCoupon())
                .isDeposit(orderDTO.getIsDeposit())
                .isService(orderDTO.getIsService())
                .menuInfo(orderDTO.getMenuInfo())
                .build();
    }
}