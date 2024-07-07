package com.DevTino.festino_main.order.model.DTO;

import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestOrderSaveDTO {
    Integer tableNum;
    String userName;
    String phoneNum;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    Integer totalPrice;
    Boolean isCoupon;
}
