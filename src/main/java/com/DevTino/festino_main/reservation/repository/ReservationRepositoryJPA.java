package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepositoryJPA extends JpaRepository<Reservation, UUID> {
    Reservation findByUserNameAndPhoneNum(String userName, String phoneNum);
}
