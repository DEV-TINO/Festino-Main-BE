package com.DevTino.festino_main.user.bean.small;

import com.DevTino.festino_main.message.bean.small.CheckMessageStatusBean;
import com.DevTino.festino_main.message.bean.small.GetAccessTokenBean;
import com.DevTino.festino_main.message.bean.small.GetCustomMessageDAOBean;
import com.DevTino.festino_main.message.bean.small.SendMessageContentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SendMessageBean {

    GetAccessTokenBean getAccessTokenBean;
    SendMessageContentBean sendMessageContentBean;
    CheckMessageStatusBean checkMessageStatusBean;
    GetCustomMessageDAOBean getCustomMessageDAOBean;

    @Autowired
    public SendMessageBean(GetAccessTokenBean getAccessTokenBean, SendMessageContentBean sendMessageContentBean, CheckMessageStatusBean checkMessageStatusBean, GetCustomMessageDAOBean getCustomMessageDAOBean) {
        this.getAccessTokenBean = getAccessTokenBean;
        this.sendMessageContentBean = sendMessageContentBean;
        this.checkMessageStatusBean = checkMessageStatusBean;
        this.getCustomMessageDAOBean = getCustomMessageDAOBean;
    }

    public String exec(String phoneNum, String authorizationCode) {

        String accessToken = getAccessTokenBean.exec();
        if (accessToken == null) return "SEND_FAIL";

        String refKey = UUID.randomUUID().toString();
        String message = "Festino 인증번호 : " + authorizationCode;
        System.out.println("message = " + message);

        String messageStatus = sendMessageContentBean.exec(phoneNum, accessToken, refKey, message);
        if (messageStatus.equals("SEND_FAIL")) return messageStatus;

        return checkMessageStatusBean.exec(refKey, accessToken);
    }
}
