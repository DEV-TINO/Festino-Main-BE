package com.DevTino.festino_main.show.bean.small;

import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.domain.entity.ClubShowDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateClubShowsDTOBean {
    public static final int dayIndex = 2;
    public static final String zeroSec = ":00";

    public ResponseClubShowsGetDTO exec(ClubShowDAO clubShowDAO){

        LocalDateTime showStart = LocalDateTime.parse(clubShowDAO.getShowDate() + clubShowDAO.getShowStartTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        LocalDateTime showEnd = LocalDateTime.parse(clubShowDAO.getShowDate() + clubShowDAO.getShowEndTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        Boolean isShowing = LocalDateTime.now().isAfter(showStart) && LocalDateTime.now().isBefore(showEnd);

        return ResponseClubShowsGetDTO.builder()
                .clubId(clubShowDAO.getClubId())
                .clubName(clubShowDAO.getClubName())
                .showData(clubShowDAO.getShowDate())
                .showStartTime(clubShowDAO.getShowStartTime())
                .showEndTime(clubShowDAO.getShowEndTime())
                .clubImage(clubShowDAO.getClubImage())
                .clubDescription(clubShowDAO.getClubDescription())
                .isShowing(isShowing)
                .build();
    }

    public List<ResponseClubShowsGetDTO> exec(List<ClubShowDAO> clubShowDAOList, int day) {

        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = new ArrayList<>();

        for (ClubShowDAO clubShowDAO : clubShowDAOList) {

            String[] split = clubShowDAO.getShowDate().split("/");
            int getShowDay = Integer.parseInt(split[dayIndex]);

            if (day == getShowDay) {
                ResponseClubShowsGetDTO responseClubShowsGetDTO = exec(clubShowDAO);

                responseClubShowsGetDTOList.add(responseClubShowsGetDTO);
            }
        }
        return responseClubShowsGetDTOList;
    }
}
