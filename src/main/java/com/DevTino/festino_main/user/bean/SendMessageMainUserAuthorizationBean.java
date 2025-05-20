package com.DevTino.festino_main.user.bean;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.user.bean.small.*;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.UUID;

@Component
public class SendMessageMainUserAuthorizationBean {

    private static final SecureRandom secureRandom = new SecureRandom();
    CreateMainUserDAOBean createMainUserDAOBean;
    SaveMainUserDAOBean saveMainUserDAOBean;
    GetMainUserDAOBean getMainUserDAOBean;
    SendMessageBean sendMessageBean;
    CheckMainUserDAOBean checkMainUserDAOBean;

    @Autowired
    public SendMessageMainUserAuthorizationBean(CreateMainUserDAOBean createMainUserDAOBean, SaveMainUserDAOBean saveMainUserDAOBean, SendMessageBean sendMessageBean, GetMainUserDAOBean getMainUserDAOBean, CheckMainUserDAOBean checkMainUserDAOBean) {
        this.createMainUserDAOBean = createMainUserDAOBean;
        this.saveMainUserDAOBean = saveMainUserDAOBean;
        this.sendMessageBean = sendMessageBean;
        this.getMainUserDAOBean = getMainUserDAOBean;
        this.checkMainUserDAOBean = checkMainUserDAOBean;
    }

    // 인증코드 전송
    public UUID exec(RequestMainUserSaveDTO requestMainUserSaveDTO) {

        // 인증코드 생성
        String authorizationCode = String.valueOf(100000 + secureRandom.nextInt(900000));

        // 유저가 저장되어 있는 경우
        if (checkMainUserDAOBean.exec(requestMainUserSaveDTO.getPhoneNum(), requestMainUserSaveDTO.getMainUserName())) {

            // 유저 조회
            MainUserDAO oldMainUserDAO = getMainUserDAOBean.exec(requestMainUserSaveDTO.getPhoneNum(), requestMainUserSaveDTO.getMainUserName());

            // 이미 인증된 유저인 경우 예외 발생
            if (oldMainUserDAO.isAuthenticated()) throw new ServiceException(ExceptionEnum.ALREADY_PROCESSED);

            // 인증코드 변경
            oldMainUserDAO.setAuthorizationCode(authorizationCode);
            saveMainUserDAOBean.exec(oldMainUserDAO);

            // 인증코드 전송
            String messageStatus = sendMessageBean.exec(requestMainUserSaveDTO.getPhoneNum(), authorizationCode);

            return oldMainUserDAO.getMainUserId();

        }
        
        // 유저가 저장되어 있지 않은 경우
        else {
            // 유저 저장
            MainUserDAO mainUserDAO = createMainUserDAOBean.exec(requestMainUserSaveDTO, authorizationCode);

            // DB에 저장
            saveMainUserDAOBean.exec(mainUserDAO);

            // 인증코드 전송
            String messageStatus = sendMessageBean.exec(requestMainUserSaveDTO.getPhoneNum(), authorizationCode);

            return mainUserDAO.getMainUserId();
        }
    }
}
