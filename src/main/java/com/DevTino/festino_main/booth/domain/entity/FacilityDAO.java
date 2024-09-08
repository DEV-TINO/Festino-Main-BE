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
public class FacilityDAO {
    @Id
    UUID boothId;

    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    String instagram;

    String boothImage;

    Boolean isOpen;

    Integer markerNum;
    String location;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}