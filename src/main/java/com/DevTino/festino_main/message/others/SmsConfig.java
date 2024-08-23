package com.DevTino.festino_main.message.others;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SmsConfig {

    @Value("${gavia.key}")
    private String apiKey;
    @Value("${gavia.smsId}")
    private String smsId;
    @Value("${gavia.callback}")
    private String callBack;

    public static final String SMS_OAUTH_TOKEN_URL = "https://sms.gabia.com/oauth/token";
    public static final String SMS_SEND_URL = "https://sms.gabia.com/api/send/sms";
    public static final String RESULT_LOG_URL = "https://sms.gabia.com/api/result_log/byRefkey?refkey=";
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String CACHE_CONTROL = "no-cache";
}
