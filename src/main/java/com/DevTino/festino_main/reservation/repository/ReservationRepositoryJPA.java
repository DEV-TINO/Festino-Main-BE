package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepositoryJPA extends JpaRepository<ReservationDAO, UUID> {
    ReservationDAO findByUserNameAndPhoneNumAndReservationType(String userName, String phoneNum, ReservationEnum reservationType);

    List<ReservationDAO> findAllByBoothIdAndReservationTypeAndCreateAtLessThan(UUID boothId, ReservationEnum reservationType, LocalDateTime createAt);

    ReservationDAO findFirstByDateOrderByReservationNumDesc(Integer date);
}
