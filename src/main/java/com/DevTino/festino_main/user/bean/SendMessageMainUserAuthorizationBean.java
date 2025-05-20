package com.DevTino.festino_main.user.bean;

import com.DevTino.festino_main.user.bean.small.CreateMainUserDAOBean;
import com.DevTino.festino_main.user.bean.small.GetMainUserDAOBean;
import com.DevTino.festino_main.user.bean.small.SaveMainUserDAOBean;
import com.DevTino.festino_main.user.bean.small.SendMessageBean;
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

    @Autowired
    public SendMessageMainUserAuthorizationBean(CreateMainUserDAOBean createMainUserDAOBean,
                                                 SaveMainUserDAOBean saveMainUserDAOBean,
                                                 SendMessageBean sendMessageBean, GetMainUserDAOBean getMainUserDAOBean) {
        this.createMainUserDAOBean = createMainUserDAOBean;
        this.saveMainUserDAOBean = saveMainUserDAOBean;
        this.sendMessageBean = sendMessageBean;
        this.getMainUserDAOBean = getMainUserDAOBean;
    }

    // 인증코드 전송
    public UUID exec(RequestMainUserSaveDTO requestMainUserSaveDTO) {

        // 인증코드 생성
        String authorizationCode = String.valueOf(100000 + secureRandom.nextInt(900000));

        // 재요청
        MainUserDAO oldMainUserDAO = getMainUserDAOBean.exec(requestMainUserSaveDTO.getPhoneNum(), requestMainUserSaveDTO.getMainUserName());
        if (oldMainUserDAO != null) {

            // 인증코드가 인증 안 된 경우
            if (!oldMainUserDAO.isAuthenticated()){
                System.out.println("oldMainUserDAO = " + oldMainUserDAO);
                // 인증코드 변경
                oldMainUserDAO.setAuthorizationCode(authorizationCode);
                saveMainUserDAOBean.exec(oldMainUserDAO);

                // 인증코드 전송
                String messageStatus = sendMessageBean.exec(requestMainUserSaveDTO.getPhoneNum(), authorizationCode);
                if (messageStatus.equals("SEND_FAIL")) return null;


                return oldMainUserDAO.getMainUserId();
            }
            else {
                // 인증코드가 인증 된 경우
                return null;
            }
        }
        else {
            // 유저 저장
            MainUserDAO mainUserDAO = createMainUserDAOBean.exec(requestMainUserSaveDTO, authorizationCode);

            // DB에 저장
            saveMainUserDAOBean.exec(mainUserDAO);

            // 인증코드 전송
            String messageStatus = sendMessageBean.exec(requestMainUserSaveDTO.getPhoneNum(), authorizationCode);
            if (messageStatus.equals("SEND_FAIL")) return null;

            return mainUserDAO.getMainUserId();
        }
    }
}
