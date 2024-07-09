package com.DevTino.festino_main.order.domain.DTO;

import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class RequestOrderSaveDTO {
    UUID boothId;

    Integer tableNum;
    String userName;
    String phoneNum;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    Integer totalPrice;
    Boolean isCoupon;
}
