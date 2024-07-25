package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.domain.ReservationDetailDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationDetailRepositoryJPA extends JpaRepository<ReservationDetailDAO, UUID> {
    ReservationDetailDAO findByPhoneNum(String phoneNum);
}
