package com.DevTino.festino_main.message.bean;

import com.DevTino.festino_main.message.bean.small.CheckMessageStatusBean;
import com.DevTino.festino_main.message.bean.small.GetAccessTokenBean;
import com.DevTino.festino_main.message.bean.small.SendMessageContentBean;
import com.DevTino.festino_main.message.domain.DTO.RequestMessageSendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class SendMessageBean {

    GetAccessTokenBean getAccessTokenBean;
    SendMessageContentBean sendMessageContentBean;
    CheckMessageStatusBean checkMessageStatusBean;

    @Autowired
    public SendMessageBean(GetAccessTokenBean getAccessTokenBean, SendMessageContentBean sendMessageContentBean, CheckMessageStatusBean checkMessageStatusBean) {
        this.getAccessTokenBean = getAccessTokenBean;
        this.sendMessageContentBean = sendMessageContentBean;
        this.checkMessageStatusBean = checkMessageStatusBean;
    }

    // 메세지 전송
    /* 반환값
        "SEND_FAIL" -> 메세지 전송 실패
        "SEND_SUCCESS" -> 메세지 전송 성공
    * */
    public String exec(RequestMessageSendDTO requestMessageSendDTO) throws IOException {

        String adminName = requestMessageSendDTO.getAdminName();
        String userName = requestMessageSendDTO.getUserName();
        String phoneNum = requestMessageSendDTO.getPhoneNum();
        String message = requestMessageSendDTO.getMessage();

        String accessToken = getAccessTokenBean.exec();
        if (accessToken == null) return "ACCESS_TOKEN_FAIL";

        String refKey = UUID.randomUUID().toString();
        // String message = userName + "님 " + adminName + " 예약이 완료되었습니다.";
        // String message = userName + "님 곧 입장이 가능합니다. 부스로 바로 와주세요.";
        // String message = userName + "님 " + adminName + " 예약이 취소되었습니다.";
        // String message = userName + "님 " + adminName + "에서 즐거운 시간 보내시기 바랍니다.";


        String messageStatus = sendMessageContentBean.exec(phoneNum, accessToken, refKey, message);
        if (messageStatus.equals("SEND_FAIL")) return messageStatus;

        return checkMessageStatusBean.exec(refKey, accessToken);
    }
}
