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

        LocalDateTime showStart = LocalDateTime.parse(talentShowDAO.getShowDate() + talentShowDAO.getShowStartTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        LocalDateTime showEnd = LocalDateTime.parse(talentShowDAO.getShowDate() + talentShowDAO.getShowEndTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        Boolean isShowing = LocalDateTime.now().isAfter(showStart) && LocalDateTime.now().isBefore(showEnd);

        return ResponseTalentShowsGetDTO.builder()
                .talentId(talentShowDAO.getTalentId())
                .talentName(talentShowDAO.getTalentName())
                .showDate(talentShowDAO.getShowDate())
                .showStartTime(talentShowDAO.getTalentImage())
                .showEndTime(talentShowDAO.getShowEndTime())
                .talentImage(talentShowDAO.getTalentImage())
                .isShowing(isShowing)
                .msuicList(talentShowDAO.getMusicList())
                .build();
    }

    public List<ResponseTalentShowsGetDTO> exec(List<TalentShowDAO> talentShowDAOList, int day){

        List<ResponseTalentShowsGetDTO> responseTalentShowsGetDTOList = new ArrayList<>();

        for (TalentShowDAO talentShowDAO : talentShowDAOList){

            String[] split = talentShowDAO.getShowDate().split("/");
            int getShowDay = Integer.parseInt(split[dayIndex]);

            if (day == getShowDay){
                ResponseTalentShowsGetDTO responseTalentShowsGetDTO = exec(talentShowDAO);

                responseTalentShowsGetDTOList.add(responseTalentShowsGetDTO);
            }
        }
        return responseTalentShowsGetDTOList;
    }
}
