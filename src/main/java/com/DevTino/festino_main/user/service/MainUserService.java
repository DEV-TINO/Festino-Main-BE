package com.DevTino.festino_main.user.service;

import com.DevTino.festino_main.user.bean.GetMainUserBean;
import com.DevTino.festino_main.user.bean.SaveMainUserBean;
import com.DevTino.festino_main.user.bean.SendMessageMainUserAuthorizationBean;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MainUserService {

    GetMainUserBean getMainUserBean;
    SaveMainUserBean saveMainUserBean;
    SendMessageMainUserAuthorizationBean sendMessageMainUserAuthorizationBean;

    @Autowired
    public MainUserService(GetMainUserBean getMainUserBean, SaveMainUserBean saveMainUserBean, SendMessageMainUserAuthorizationBean sendMessageMainUserAuthorizationBean) {
        this.getMainUserBean = getMainUserBean;
        this.saveMainUserBean = saveMainUserBean;
        this.sendMessageMainUserAuthorizationBean = sendMessageMainUserAuthorizationBean;
    }

    // 유저 로그인
    public UUID getMainUser(String phoneNum, String mainUserName) {
        return getMainUserBean.exec(phoneNum, mainUserName);
    }

    // 사용자 정보 저장
    public UUID saveMainUser(RequestMainUserSaveDTO requestMainUserSaveDTO) {
        return saveMainUserBean.exec(requestMainUserSaveDTO);
    }

    // 사용자 인증 번호 전송
    public UUID sendMessageMainUserAuthorization(RequestMainUserSaveDTO requestMainUserSaveDTO) {
        return sendMessageMainUserAuthorizationBean.exec(requestMainUserSaveDTO);
    }
}
