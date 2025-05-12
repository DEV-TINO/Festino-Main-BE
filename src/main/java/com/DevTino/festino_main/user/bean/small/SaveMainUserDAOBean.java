package com.DevTino.festino_main.user.bean.small;

import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import com.DevTino.festino_main.user.repository.MainUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveMainUserDAOBean {

    MainUserRepositoryJPA mainUserRepositoryJPA;

    @Autowired
    public SaveMainUserDAOBean(MainUserRepositoryJPA mainUserRepositoryJPA) {
        this.mainUserRepositoryJPA = mainUserRepositoryJPA;
    }

    // 사용자 정보 저장
    public void exec(MainUserDAO mainUserDAO) {
        mainUserRepositoryJPA.save(mainUserDAO);
    }
}
