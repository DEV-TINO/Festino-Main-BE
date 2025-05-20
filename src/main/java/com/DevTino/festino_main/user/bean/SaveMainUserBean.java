package com.DevTino.festino_main.user.bean;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.user.bean.small.GetMainUserDAOBean;
import com.DevTino.festino_main.user.bean.small.SaveMainUserDAOBean;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveMainUserBean {

    GetMainUserDAOBean getMainUserDAOBean;
    SaveMainUserDAOBean saveMainUserDAOBean;

    @Autowired
    public SaveMainUserBean(GetMainUserDAOBean getMainUserDAOBean, SaveMainUserDAOBean saveMainUserDAOBean) {
        this.getMainUserDAOBean = getMainUserDAOBean;
        this.saveMainUserDAOBean = saveMainUserDAOBean;
    }

    public UUID exec(RequestMainUserSaveDTO requestMainUserSaveDTO){

        // 사용자 정보 조회
        MainUserDAO mainUserDAO = getMainUserDAOBean.exec(requestMainUserSaveDTO.getPhoneNum(), requestMainUserSaveDTO.getMainUserName());

        if (mainUserDAO.isAuthenticated()) throw new ServiceException(ExceptionEnum.ALREADY_PROCESSED);

        // 인증코드 확인, 다른 경우 예외 발생
        if (!mainUserDAO.getAuthorizationCode().equals(requestMainUserSaveDTO.getAuthorizationCode())) throw new ServiceException(ExceptionEnum.AUTHCODE_MISMATCH);

        // 3분이 지나면 인증코드 만료

        // 인증코드 확인 후 인증 완료
        mainUserDAO.setAuthenticated(true);

        saveMainUserDAOBean.exec(mainUserDAO);

        return mainUserDAO.getMainUserId();
    }
}
