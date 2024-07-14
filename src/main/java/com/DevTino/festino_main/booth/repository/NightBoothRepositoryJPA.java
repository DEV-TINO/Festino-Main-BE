package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NightBoothRepositoryJPA extends JpaRepository<NightBoothDAO, UUID> {

    // 예약가능한 야간부스 전체 가져오기
    List<NightBoothDAO> findByIsReservation(Boolean isReservation);
}