package com.DevTino.festino_main.booth.domain.entity;

import com.DevTino.festino_main.booth.others.StringConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NightBoothDAO {
    @Id
    UUID boothId;

    Integer boothNum;

    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;

    @Convert(converter = StringConverter.class)
    List<String> boothImage;
    String location;

    Boolean isOpen;
    Boolean isOrder;
    Boolean isReservation;

    Integer totalReservationNum;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}