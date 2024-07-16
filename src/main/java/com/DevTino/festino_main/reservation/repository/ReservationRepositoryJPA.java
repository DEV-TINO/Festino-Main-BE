package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepositoryJPA extends JpaRepository<ReservationDAO, UUID> {
    ReservationDAO findByUserNameAndPhoneNumAndIsCancel(String userName, String phoneNum, Boolean isCancel);

    List<ReservationDAO> findAllByBoothIdAndIsCancelAndCreateAtLessThan(UUID boothId, Boolean isCancel, LocalDateTime createAt);

    ReservationDAO findFirstByDateOrderByReservationNumDesc(Integer date);
}
