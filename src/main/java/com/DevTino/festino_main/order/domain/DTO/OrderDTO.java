package com.DevTino.festino_main.order.domain.DTO;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import com.DevTino.festino_main.order.domain.GameOrderDAO;
import com.DevTino.festino_main.order.domain.OrderType;
import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {
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

    public static OrderDTO fromComputerOrderDAO(ComputerOrderDAO computerOrderDAO) {
        return OrderDTO.builder()
                .orderId(computerOrderDAO.getOrderId())
                .boothId(computerOrderDAO.getBoothId())
                .userName(computerOrderDAO.getUserName())
                .phoneNum(computerOrderDAO.getPhoneNum())
                .totalPrice(computerOrderDAO.getTotalPrice())
                .createAt(computerOrderDAO.getCreateAt())
                .isCoupon(computerOrderDAO.getIsCoupon())
                .isDeposit(computerOrderDAO.getIsDeposit())
                .menuInfo(computerOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromGameOrderDAO(GameOrderDAO gameOrderDAO) {
        return OrderDTO.builder()
                .orderId(gameOrderDAO.getOrderId())
                .boothId(gameOrderDAO.getBoothId())
                .userName(gameOrderDAO.getUserName())
                .phoneNum(gameOrderDAO.getPhoneNum())
                .totalPrice(gameOrderDAO.getTotalPrice())
                .createAt(gameOrderDAO.getCreateAt())
                .isCoupon(gameOrderDAO.getIsCoupon())
                .isDeposit(gameOrderDAO.getIsDeposit())
                .menuInfo(gameOrderDAO.getMenuInfo())
                .build();
    }
}
