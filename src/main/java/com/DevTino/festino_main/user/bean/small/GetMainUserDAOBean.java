package com.DevTino.festino_main.user.bean.small;

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

    // 사용자 정보 조회
    public MainUserDAO exec(String phoneNum, String mainUserName) {
        // 사용자 정보가 있는지 조회
        return mainUserRepositoryJPA.findByPhoneNumAndMainUserName(phoneNum, mainUserName);
    }
}
