package com.DevTino.festino_main.message.bean.small;

import com.DevTino.festino_main.message.domain.CustomMessageDAO;
import com.DevTino.festino_main.message.domain.ENUM.MessageType;
import com.DevTino.festino_main.message.repository.CustomMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCustomMessageDAOBean {

    CustomMessageRepositoryJPA customMessageRepositoryJPA;

    @Autowired
    public GetCustomMessageDAOBean(CustomMessageRepositoryJPA customMessageRepositoryJPA) {
        this.customMessageRepositoryJPA = customMessageRepositoryJPA;
    }

    public CustomMessageDAO exec(UUID boothId, MessageType messageType) {
        return customMessageRepositoryJPA.findByBoothIdAndMessageType(boothId, messageType);
    }
}
