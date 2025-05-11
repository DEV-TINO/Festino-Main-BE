package com.DevTino.festino_main.user.domain.dto;

import lombok.Data;

@Data
public class RequestMainUserSaveDTO {
    private String mainUserName;
    private String phoneNum;
    private String studentNum;
    private String authorizationCode;
}
