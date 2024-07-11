package com.DevTino.festino_main.reservation.repository;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepositoryJPA extends JpaRepository<ReservationDAO, UUID> {
    ReservationDAO findByUserNameAndPhoneNumAndIsDeleted(String userName, String phoneNum, Boolean isDeleted);

    List<ReservationDAO> findAllByBoothIdAndIsDeletedAndCreateAtLessThan(UUID boothId, Boolean isDeleted, LocalDateTime createAt);
}
