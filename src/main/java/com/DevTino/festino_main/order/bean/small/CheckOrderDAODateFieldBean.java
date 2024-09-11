package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class CheckOrderDAODateFieldBean {

    // 부스의 오픈 시간을 활용해서 몇일차인지 구함
    public Integer exec(NightBoothDAO nightBoothDAO) {

        /*String openTime = nightBoothDAO.getOpenTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime open = LocalTime.parse(openTime, formatter).minusHours(2);

        LocalDateTime start11 = LocalDateTime.of(LocalDate.of(2024, 9, 11), open);
        LocalDateTime start12 = LocalDateTime.of(LocalDate.of(2024, 9, 12), open);
        LocalDateTime start13 = LocalDateTime.of(LocalDate.of(2024, 9, 13), open);

        LocalDateTime end11 = start11.plusHours(15);
        LocalDateTime end12 = start12.plusHours(15);

        LocalDateTime now = DateTimeUtils.nowZone();

        int date = 0;

        if(now.isAfter(start11) && now.isBefore(end11)) {
            date = 1;
        } else if(now.isAfter(start12) && now.isBefore(end12)) {
            date = 2;
        } else if(now.isAfter(start13)) {
            date = 3;
        }*/

        // 시작 시간
        LocalDateTime start11 = LocalDateTime.of(LocalDate.of(2024, 9, 11), LocalTime.of(15, 0, 0));
        LocalDateTime start12 = LocalDateTime.of(LocalDate.of(2024, 9, 12), LocalTime.of(15, 0, 0));
        LocalDateTime start13 = LocalDateTime.of(LocalDate.of(2024, 9, 13), LocalTime.of(15, 0, 0));

        // 종료 시간
        LocalDateTime end11 = start11.plusHours(24);
        LocalDateTime end12 = start12.plusHours(24);

        // 서버 시간 고려 9시간 더해줌
        LocalDateTime now = DateTimeUtils.nowZone();

        // 시간에 따라 date 설정
        int date = 0;

        if(now.isAfter(start11) && now.isBefore(end11)) {
            date = 1;
        } else if(now.isAfter(start12) && now.isBefore(end12)) {
            date = 2;
        } else if(now.isAfter(start13)) {
            date = 3;
        }

        return date;
    }
}
