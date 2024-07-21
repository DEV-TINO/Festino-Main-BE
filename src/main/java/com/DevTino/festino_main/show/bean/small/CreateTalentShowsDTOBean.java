package com.DevTino.festino_main.show.bean.small;

import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import com.DevTino.festino_main.show.domain.entity.TalentShowDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateTalentShowsDTOBean {
    public static final String zeroSec = ":00";
    public static final int dayIndex = 2;

    public ResponseTalentShowsGetDTO exec(TalentShowDAO talentShowDAO){

        // 공연 시작 날짜와 시간을 LocalDate으로 변경
        LocalDateTime showStart = LocalDateTime.parse(talentShowDAO.getShowDate() + talentShowDAO.getShowStartTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        // 공연 종료 날짜와 시간을 LocalDate으로 변경
        LocalDateTime showEnd = LocalDateTime.parse(talentShowDAO.getShowDate() + talentShowDAO.getShowEndTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        // 공연 시작과 종료 시간 사이에 true로 반환
        Boolean isShowing = LocalDateTime.now().isAfter(showStart) && LocalDateTime.now().isBefore(showEnd);

        return ResponseTalentShowsGetDTO.builder()
                .talentId(talentShowDAO.getTalentId())
                .performer(talentShowDAO.getTalentName())
                .showDate(talentShowDAO.getShowDate())
                .showStartTime(talentShowDAO.getShowStartTime())
                .showEndTime(talentShowDAO.getShowEndTime())
                .talentImage(talentShowDAO.getTalentImage())
                .isShowing(isShowing)
                .musicList(talentShowDAO.getMusicList())
                .build();
    }

    // 날짜 별 연예인  타임 테이블 전체 리스트로 반환
    public List<ResponseTalentShowsGetDTO> exec(List<TalentShowDAO> talentShowDAOList, int day){

        List<ResponseTalentShowsGetDTO> responseTalentShowsGetDTOList = new ArrayList<>();

        if (day == 1) {
            day = 11;
        } else if (day == 2){
            day = 12;
        } else if (day == 3) {
            day = 13;
        } else
            return null;

        // 날짜 별 동아리 타임 테이블 전체 리스트로 가져오기
        for (TalentShowDAO talentShowDAO : talentShowDAOList){

            String[] split = talentShowDAO.getShowDate().split("/");
            int getShowDay = Integer.parseInt(split[dayIndex]);

            if (day == getShowDay){
                ResponseTalentShowsGetDTO responseTalentShowsGetDTO = exec(talentShowDAO);

                responseTalentShowsGetDTOList.add(responseTalentShowsGetDTO);
            }
        }
        if (responseTalentShowsGetDTOList.isEmpty()){
            return null;
        }

        return responseTalentShowsGetDTOList;
    }
}
