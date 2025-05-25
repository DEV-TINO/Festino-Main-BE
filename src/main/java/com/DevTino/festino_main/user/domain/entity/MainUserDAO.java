package com.DevTino.festino_main.user.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MainUserDAO {
    @Id
    private UUID mainUserId;

    private String mainUserName;
    private String phoneNum;
    private String studentNum;
    private String authorizationCode;

    private boolean isAuthenticated;

    LocalDateTime createAt;
}
