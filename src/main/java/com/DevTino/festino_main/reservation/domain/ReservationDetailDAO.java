package com.DevTino.festino_main.reservation.domain;

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
public class ReservationDetailDAO {
    @Id
    UUID reservationId;
    String boothId;
    String phoneNum;
}
