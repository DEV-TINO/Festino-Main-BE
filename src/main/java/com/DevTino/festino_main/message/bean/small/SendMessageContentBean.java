package com.DevTino.festino_main.message.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.message.others.SmsConfig;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

import static com.DevTino.festino_main.message.others.SmsConfig.*;

@Component
public class SendMessageContentBean {

    SmsConfig smsConfig;

    @Autowired
    public SendMessageContentBean(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public String exec(String phoneNum, String accessToken, String refKey, String message) {

        try {
            String authValue =
                    Base64.getEncoder().encodeToString(String.format("%s:%s", smsConfig.getSmsId(),
                            accessToken).getBytes(StandardCharsets.UTF_8)); // Authorization Header 에 입력할 값입니다.

            // SMS 발송 API 를 호출합니다.
            OkHttpClient client1 = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("phone", phoneNum)
                    .addFormDataPart("callback", smsConfig.getCallBack()) // 발신번호를 입력해 주세요.
                    .addFormDataPart("message", message) // SMS 내용을 입력해 주세요.
                    .addFormDataPart("refkey", refKey) // 발송 결과 조회를 위한 임의의 랜덤 키 값을 입력해 주세요.
                    .build();

            Request request1 = new Request.Builder()
                    .url(SMS_SEND_URL)
                    .post(requestBody)
                    .addHeader("Content-Type", CONTENT_TYPE)
                    .addHeader("Authorization", "Basic " + authValue)
                    .addHeader("cache-control", CACHE_CONTROL)
                    .build();

            Response response = client1.newCall(request1).execute();

            // Response 를 key, value 로 확인하실 수 있습니다.
            if (response.isSuccessful()) {
                HashMap<String, String> result = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), HashMap.class);
                if (result.get("code").equals("200"))
                    return "SEND_SUCCESS";
            }
            return "SEND_FAIL";
        } catch (IOException e) {
            throw new ServiceException(ExceptionEnum.INTERNAL_ERROR);
        }
    }
}
