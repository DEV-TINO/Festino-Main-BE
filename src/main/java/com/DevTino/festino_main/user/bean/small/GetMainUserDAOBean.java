package com.DevTino.festino_main.user.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import com.DevTino.festino_main.user.repository.MainUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetMainUserDAOBean {

    MainUserRepositoryJPA mainUserRepositoryJPA;

    @Autowired
    public GetMainUserDAOBean(MainUserRepositoryJPA mainUserRepositoryJPA) {
        this.mainUserRepositoryJPA = mainUserRepositoryJPA;
    }

    public MainUserDAO exec(UUID mainUserId) {

        MainUserDAO dao = mainUserRepositoryJPA.findByMainUserId(mainUserId);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }

    // 사용자 정보 조회
    public MainUserDAO exec(String phoneNum, String mainUserName) {

        MainUserDAO dao = mainUserRepositoryJPA.findByPhoneNumAndMainUserName(phoneNum, mainUserName);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
