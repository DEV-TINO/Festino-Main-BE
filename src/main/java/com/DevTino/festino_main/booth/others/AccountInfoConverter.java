package com.DevTino.festino_main.booth.others;

import com.DevTino.festino_main.booth.domain.DTO.AccountInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AccountInfoConverter implements AttributeConverter<AccountInfo, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AccountInfo accountInfo) {
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
    public AccountInfo convertToEntityAttribute(String data) {
        if (data.isEmpty()) {
            return null;  // 예외 처리
        }
        try {
            return mapper.readValue(data, AccountInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
