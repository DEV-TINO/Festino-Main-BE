package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NightBoothRepositoryJPA extends JpaRepository<NightBoothDAO, UUID> {

    // 야간 부스 오픈 여부에 따른 전체 가져오기
    List<NightBoothDAO> findAllByOrderByCreateAtAsc();

    // 예약가능한 야간부스 전체 가져오기 - isOpen 이 true 인 값 위로 올리기
    List<NightBoothDAO> findByIsReservation(Boolean isReservation);

    // 부스가 운영중인지, 예약 가능한지 여부 확인
    NightBoothDAO findByBoothIdAndIsOpenAndIsReservation(UUID boothId, Boolean isOpen, Boolean isReservation);
}