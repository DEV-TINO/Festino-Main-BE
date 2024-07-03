package com.DevTino.festino_main.order.model;

import com.DevTino.festino_main.order.model.DTO.MenuInfoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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

    Integer tableNum;
    String userName;
    String phoneNum;

    @ElementCollection
    @CollectionTable(name = "order_menu", joinColumns = @JoinColumn(name = "order_id"))
    List<MenuInfoDTO> menuList;

    Integer totalPrice;

    LocalDateTime createAt;

    Boolean isDeposit;
    Boolean isCoupon;
    Boolean isDeleted;
}
