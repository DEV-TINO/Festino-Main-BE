package com.DevTino.festino_main.user.bean.small;

import com.DevTino.festino_main.user.repository.MainUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckMainUserDAOBean {

    MainUserRepositoryJPA mainUserRepositoryJPA;

    @Autowired
    public CheckMainUserDAOBean(MainUserRepositoryJPA mainUserRepositoryJPA) {
        this.mainUserRepositoryJPA = mainUserRepositoryJPA;
    }



    // 번호&이름으로 검색해 유저 존재 여부 반환
    public boolean exec(String phoneNum, String mainUserName) {
        return mainUserRepositoryJPA.existsByPhoneNumAndMainUserName(phoneNum, mainUserName);
    }
}
