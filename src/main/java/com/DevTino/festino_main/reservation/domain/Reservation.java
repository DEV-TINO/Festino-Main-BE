package com.DevTino.festino_main.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    UUID reservationId;

    String userName;
    UUID boothId;
    Integer personCount;
    String phoneNum;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}
