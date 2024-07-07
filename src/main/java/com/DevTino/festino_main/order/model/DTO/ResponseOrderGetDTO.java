package com.DevTino.festino_main.order.model.DTO;

import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResponseOrderGetDTO {
    Integer tableNum;
    LocalDateTime createAt;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    Integer totalPrice;
}
