package com.DevTino.festino_main.show.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.List;
import java.util.Map;

@Convert
public class StringListConvert implements AttributeConverter<List<Map<String, Object>>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Map<String, Object>> dateList){
        try {
            return objectMapper.writeValueAsString(dateList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> convertToEntityAttribute(String data){
        try {
            return objectMapper.readValue(data, List.class);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
