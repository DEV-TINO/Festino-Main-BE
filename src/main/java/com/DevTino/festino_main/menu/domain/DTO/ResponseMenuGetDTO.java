package com.DevTino.festino_main.menu.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseMenuGetDTO {
    UUID menuId;
    String menuName;
    String menuDescription;
    String menuImage;
    Integer menuPrice;
    Boolean isSoldOut;
}
