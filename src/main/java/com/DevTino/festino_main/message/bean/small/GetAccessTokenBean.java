package com.DevTino.festino_main.message.bean.small;

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
public class GetAccessTokenBean {

    SmsConfig smsConfig;

    @Autowired
    public GetAccessTokenBean(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public String exec() throws IOException {
        String authValue = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", smsConfig.getSmsId(), smsConfig.getApiKey()).getBytes(StandardCharsets.UTF_8));

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "client_credentials")
                .build();

        Request request = new Request.Builder()
                .url(SMS_OAUTH_TOKEN_URL)
                .post(requestBody)
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Authorization", "Basic " + authValue)
                .addHeader("cache-control", CACHE_CONTROL)
                .build();

        Response response = client.newCall(request).execute();
        HashMap<String, String> result = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), HashMap.class);

        return result.get("access_token"); // ACCESS TOKEN

    }
}
