package com.DevTino.festino_main.message.bean;

import com.DevTino.festino_main.message.bean.small.CheckMessageStatusBean;
import com.DevTino.festino_main.message.bean.small.GetAccessTokenBean;
import com.DevTino.festino_main.message.bean.small.GetCustomMessageDAOBean;
import com.DevTino.festino_main.message.bean.small.SendMessageContentBean;
import com.DevTino.festino_main.message.domain.ENUM.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class SaveReservationSendMessageBean {

    GetAccessTokenBean getAccessTokenBean;
    SendMessageContentBean sendMessageContentBean;
    CheckMessageStatusBean checkMessageStatusBean;
    GetCustomMessageDAOBean getCustomMessageDAOBean;

    @Autowired
    public SaveReservationSendMessageBean(GetAccessTokenBean getAccessTokenBean, SendMessageContentBean sendMessageContentBean, CheckMessageStatusBean checkMessageStatusBean, GetCustomMessageDAOBean getCustomMessageDAOBean) {
        this.getAccessTokenBean = getAccessTokenBean;
        this.sendMessageContentBean = sendMessageContentBean;
        this.checkMessageStatusBean = checkMessageStatusBean;
        this.getCustomMessageDAOBean = getCustomMessageDAOBean;
    }

    // 메세지 전송
    /* 반환값
        "SEND_FAIL" -> 메세지 전송 실패
        "SEND_SUCCESS" -> 메세지 전송 성공
    * */
    public String exec(UUID boothId, String phoneNum, String userName) throws IOException {

        String accessToken = getAccessTokenBean.exec();
        if (accessToken == null) return "SEND_FAIL";

        String refKey = UUID.randomUUID().toString();
        String message = userName + "님 " + getCustomMessageDAOBean.exec(boothId, MessageType.RESERVATION).getMessage();

        String messageStatus = sendMessageContentBean.exec(phoneNum, accessToken, refKey, message);
        if (messageStatus.equals("SEND_FAIL")) return messageStatus;

        return checkMessageStatusBean.exec(refKey, accessToken);
    }
}
