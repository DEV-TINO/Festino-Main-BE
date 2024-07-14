package com.DevTino.festino_main.menu.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MenuDAO {
    @Id
    UUID menuId;
    UUID boothId;

    String menuName;
    String menuDescription;
    String menuImage;

    Integer menuPrice;

    Boolean isSoldOut;

    LocalDateTime createAt;
    LocalDateTime updateAt;

    MenuType menuType;
}
