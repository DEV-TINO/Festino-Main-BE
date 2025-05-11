package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateRealTimeQuestionDAOBean {

    RealTimeRepositoryJPA realTimeRepositoryJPA;

    @Autowired
    public CreateRealTimeQuestionDAOBean(RealTimeRepositoryJPA realTimeRepositoryJPA) {
        this.realTimeRepositoryJPA = realTimeRepositoryJPA;
    }

    public RealTimeQuestionDAO exec(RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){
        long count  = realTimeRepositoryJPA.count();


        // 실전용
//        LocalDate baseDate = LocalDate.of(2025, 5, 26);
//        LocalDate targetDate = baseDate.plusDays(count);

//        LocalDateTime startTime = targetDate.atTime(18, 0);
//        LocalDateTime endTime = targetDate.atTime(18, 10);

        // 테스트용
        LocalDate baseDate = LocalDate.of(2025, 5, 11);
        LocalDate targetDate = baseDate.plusDays(count);

        LocalDateTime startTime = targetDate.atTime(0, 1);
        LocalDateTime endTime = targetDate.atTime(23, 59);

        Boolean isOpen =
                DateTimeUtils.nowZone().isAfter(startTime) && DateTimeUtils.nowZone().isBefore(endTime);


        return RealTimeQuestionDAO.builder()
                .realTimeQuestionId(UUID.randomUUID())
                .question(requestRealTimeQuestionSaveDTO.getQuestion())
                .isOpen(isOpen)
                .startTime(startTime)
                .endTime(endTime)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
