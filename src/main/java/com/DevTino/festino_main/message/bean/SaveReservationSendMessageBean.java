package com.DevTino.festino_main.message.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class SaveReservationSendMessageBean {

    @Value("${gavia.key}")
    private String apiKey;
    @Value("${gavia.smsId}")
    private String smsId;
    @Value("${gavia.callback}")
    private String callBack;

    public static final String SMS_OAUTH_TOKEN_URL = "https://sms.gabia.com/oauth/token";
    public static final String SMS_SEND_URL = "https://sms.gabia.com/api/send/sms";
    public static final String RESULT_LOG_URL = "https://sms.gabia.com/api/result_log/byRefkey?refkey=";

    public String exec(String phoneNum,String userName,String adminName) throws IOException {

        String authValue =
                Base64.getEncoder().encodeToString(String.format("%s:%s", smsId, apiKey).getBytes(StandardCharsets.UTF_8)); // Authorization Header 에 입력할 값입니다.

        // 사용자 인증 API 를 호출합니다.
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "client_credentials")
                .build();

        Request request = new Request.Builder()
                .url(SMS_OAUTH_TOKEN_URL)
                .post(requestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic " + authValue)
                .addHeader("cache-control", "no-cache")
                .build();

        // Response 를 key, value 로 확인하실 수 있습니다.
        Response response = client.newCall(request).execute();
        HashMap<String, String> result = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), HashMap.class);

        String accessToken = result.get("access_token"); // ACCESS TOKEN 을 입력해 주세요.







        String authValue1 =
                Base64.getEncoder().encodeToString(String.format("%s:%s", smsId,
                        accessToken).getBytes(StandardCharsets.UTF_8)); // Authorization Header 에 입력할 값입니다.

        String refKey = UUID.randomUUID().toString();

        // SMS 발송 API 를 호출합니다.
        OkHttpClient client1 = new OkHttpClient();

        RequestBody requestBody1 = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("phone", phoneNum)
                .addFormDataPart("callback", callBack) // 발신번호를 입력해 주세요.
                .addFormDataPart("message", userName + "님 " + adminName + " 예약이 완료되었습니다.") // SMS 내용을 입력해 주세요.
                .addFormDataPart("refkey", refKey) // 발송 결과 조회를 위한 임의의 랜덤 키 값을 입력해 주세요.
                .build();

        Request request1 = new Request.Builder()
                .url(SMS_SEND_URL)
                .post(requestBody1)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Basic " + authValue1)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response1 = client1.newCall(request1).execute();

        // Response 를 key, value 로 확인하실 수 있습니다.
        HashMap<String, String> result1 = new
                Gson().fromJson(Objects.requireNonNull(response1.body()).string(), HashMap.class);
        for(String key : result1.keySet()) {
            if (key.equals("code")){
                if (!result1.get(key).equals("200"))
                    return "SEND_FAIL";
            }
        }

        while (true){
            // 문자 발송 결과 조회 API 를 호출합니다.
            OkHttpClient client2 = new OkHttpClient();

            Request request2 = new Request.Builder()
                    .url(RESULT_LOG_URL + refKey)
                    .get()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Authorization", "Basic " + authValue1)
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response2 = client2.newCall(request2).execute();

            // Response 를 key, value 로 확인하실 수 있습니다.
            HashMap<String, String> result2 = new Gson().fromJson(Objects.requireNonNull(response2.body()).string(), HashMap.class);

            for (String key : result2.keySet()) {
                if ("data".equals(key)) {
                    String dataJson = new Gson().toJson(result2.get(key)); // Convert the data object to JSON string
                    Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                    List<Map<String, Object>> dataList = new Gson().fromJson(dataJson, listType);

                    for (Map<String, Object> data : dataList) {
                        if ("FAIL".equals(data.get("CODE"))){
                            return "SEND_FAIL";
                        } else if ("SUCC".equals(data.get("CODE"))){
                            return "SEND_SUCCESS";
                        }
                    }
                }
            }
        }
    }
}
