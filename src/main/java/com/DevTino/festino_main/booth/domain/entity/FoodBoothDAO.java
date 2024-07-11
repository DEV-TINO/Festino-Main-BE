package com.DevTino.festino_main.booth.domain.entity;

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
public class FoodBoothDAO {
    @Id
    UUID boothId;

    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    String location;

    Boolean isOpen;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}