package com.DevTino.festino_main.user.bean;

import com.DevTino.festino_main.user.bean.small.GetMainUserDAOBean;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetMainUserBean {

    GetMainUserDAOBean getMainUserDAOBean;

    @Autowired
    public GetMainUserBean(GetMainUserDAOBean getMainUserDAOBean) {
        this.getMainUserDAOBean = getMainUserDAOBean;
    }

    // 유저 로그인
    public UUID exec(String phoneNum, String mainUserName) {

        // 유저 조회
        MainUserDAO mainUserDAO = getMainUserDAOBean.exec(phoneNum, mainUserName);

        // 유저가 존재하지 않으면 null 리턴
        if (mainUserDAO == null) return null;

        return mainUserDAO.getMainUserId();
    }
}
