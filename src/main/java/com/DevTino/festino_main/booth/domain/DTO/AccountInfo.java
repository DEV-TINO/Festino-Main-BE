package com.DevTino.festino_main.booth.domain.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountInfo {
    String account;
    String accountHolder;
    String bankName;
}