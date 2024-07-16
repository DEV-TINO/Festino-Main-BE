package com.DevTino.festino_main.reservation.domain;

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
public class ReservationDAO {
    @Id
    UUID reservationId;

    String userName;
    String phoneNum;
    UUID boothId;
    Integer personCount;
    Integer date;
    Integer reservationNum;

    LocalDateTime createAt;
    LocalDateTime updateAt;

    Boolean isCancel;
}
