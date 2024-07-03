package com.DevTino.festino_main.order.model.DTO;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MenuInfoDTO {
    String menuName;
    Integer menuPrice;
    Integer menuCount;
}
