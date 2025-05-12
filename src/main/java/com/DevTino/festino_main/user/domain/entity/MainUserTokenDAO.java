package com.DevTino.festino_main.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainUserTokenDAO {
    @Id
    private UUID tokenId;
    private UUID mainUserId;

    @Column(length = 512)
    private String accessToken;

    @Column(length = 512)
    private String refreshToken;

    private LocalDateTime createAt;
}