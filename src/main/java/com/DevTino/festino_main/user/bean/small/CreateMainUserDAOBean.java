package com.DevTino.festino_main.user.bean.small;

import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateMainUserDAOBean {

    public MainUserDAO exec(RequestMainUserSaveDTO requestMainUserSaveDTO, String authorizationCode) {
        return MainUserDAO.builder()
                .mainUserId(UUID.randomUUID())
                .mainUserName(requestMainUserSaveDTO.getMainUserName())
                .phoneNum(requestMainUserSaveDTO.getPhoneNum())
                .studentNum(requestMainUserSaveDTO.getStudentNum())
                .authorizationCode(authorizationCode)
                .isAuthenticated(false)
                .build();
    }
}
