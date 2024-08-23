package com.DevTino.festino_main.message.bean.small;

import com.DevTino.festino_main.message.others.SmsConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.DevTino.festino_main.message.others.SmsConfig.*;

@Component
public class CheckMessageStatusBean {

    SmsConfig smsConfig;

    @Autowired
    public CheckMessageStatusBean(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public String exec(String refKey, String accessToken) throws IOException {
        String authValue = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", smsConfig.getSmsId(), accessToken).getBytes(StandardCharsets.UTF_8));

        Request request = new Request.Builder()
                .url(RESULT_LOG_URL + refKey)
                .get()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("Authorization", "Basic " + authValue)
                .addHeader("cache-control", CACHE_CONTROL)
                .build();

        while (true) {

            OkHttpClient client = new OkHttpClient();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Map<String, Object> result = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), Map.class);

                String dataJson = new Gson().toJson(result.get("data"));
                Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                List<Map<String, Object>> dataList = new Gson().fromJson(dataJson, listType);

                for (Map<String, Object> data : dataList) {
                    String code = (String) data.get("CODE");
                    if ("FAIL".equals(code)) {
                        return "PHONE_NUM_FAIL";
                    } else if ("SUCC".equals(code)) {
                        return "SEND_SUCCESS";
                    }
                }
            }
        }
    }
}
