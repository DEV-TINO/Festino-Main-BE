package com.DevTino.festino_main.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DayBoothDAO {
    @Id
    UUID boothId;

    String boothName;
    String adminCategory;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    String location;
    String descripteImage;
    String description;

    Boolean isOpen;

}