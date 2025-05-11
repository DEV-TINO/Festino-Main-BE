package com.DevTino.festino_main.user.bean;

import com.DevTino.festino_main.user.bean.small.GetMainUserDAOBean;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveMainUserBean {

    GetMainUserDAOBean getMainUserDAOBean;

    @Autowired
    public SaveMainUserBean(GetMainUserDAOBean getMainUserDAOBean) {
        this.getMainUserDAOBean = getMainUserDAOBean;
    }

    public UUID exec(RequestMainUserSaveDTO requestMainUserSaveDTO){

        // 사용자 정보가 있는지 조회
        MainUserDAO mainUserDAO = getMainUserDAOBean.exec(requestMainUserSaveDTO.getPhoneNum(), requestMainUserSaveDTO.getStudentNum());
        if (mainUserDAO == null) return null;

        // 인증코드 확인
        if (!mainUserDAO.getAuthorizationCode().equals(requestMainUserSaveDTO.getAuthorizationCode())) return null;

        // 인증코드 확인 후 인증 완료
        mainUserDAO.setAuthenticated(true);

        return mainUserDAO.getMainUserId();
    }
}
