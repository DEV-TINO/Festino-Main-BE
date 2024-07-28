package com.DevTino.festino_main.booth.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class AccountInfoConverter implements AttributeConverter<Map<String, String>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> accountInfo) {
        if (accountInfo.toString().isEmpty()) {
            return null;  // 예외 처리
        }
        try {
            return mapper.writeValueAsString(accountInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String data) {
        if (data.isEmpty()) {
            return null;  // 예외 처리
        }
        try {
            return mapper.readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
