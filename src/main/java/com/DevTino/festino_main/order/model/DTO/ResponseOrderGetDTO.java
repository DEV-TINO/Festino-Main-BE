package com.DevTino.festino_main.order.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ResponseOrderGetDTO {
    Integer tableNum;
    LocalDateTime createAt;
    List<MenuInfoDTO> menuInfoDTOList;
    Integer totalPrice;
}
