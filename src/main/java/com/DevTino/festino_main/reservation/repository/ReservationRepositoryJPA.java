package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepositoryJPA extends JpaRepository<ReservationDAO, UUID> {
    ReservationDAO findByUserNameAndPhoneNum(String userName, String phoneNum);
}
