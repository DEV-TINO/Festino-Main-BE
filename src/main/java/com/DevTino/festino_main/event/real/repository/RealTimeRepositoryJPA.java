package com.DevTino.festino_main.event.real.repository;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RealTimeRepositoryJPA extends JpaRepository<RealTimeQuestionDAO, UUID> {

    // 현재 시간이 startTime ~ endTime 사이에 있는 문제만 조회
    RealTimeQuestionDAO findTop1ByStartTimeLessThanEqualAndEndTimeGreaterThanEqualOrderByStartTimeAsc(LocalDateTime now1, LocalDateTime now2);
}
