package com.DevTino.festino_main.order.domain.DTO;

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
    String adminName;
    Integer tableNum;
    LocalDateTime createAt;

    Integer date;
    Integer orderNum;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    Integer totalPrice;
}
