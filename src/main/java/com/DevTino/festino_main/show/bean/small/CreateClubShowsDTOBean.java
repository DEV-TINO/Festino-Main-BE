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

        // 공연 시작 날짜와 시간을 LocalDate으로 변경
        LocalDateTime showStart = LocalDateTime.parse(clubShowDAO.getShowDate() + clubShowDAO.getShowStartTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        // 공연 종료 날짜와 시간을 LocalDate으로 변경
        LocalDateTime showEnd = LocalDateTime.parse(clubShowDAO.getShowDate() + clubShowDAO.getShowEndTime() + zeroSec, DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss"));

        // 공연 시작과 종료 시간 사이에 true로 반환
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

    // 날짜 별 동아리 타임 테이블 전체 리스트로 반환
    public List<ResponseClubShowsGetDTO> exec(List<ClubShowDAO> clubShowDAOList, int day) {

        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = new ArrayList<>();

        // 날짜 별 동아리 타임 테이블 전체 리스트로 가져오기
        for (ClubShowDAO clubShowDAO : clubShowDAOList) {

            String[] split = clubShowDAO.getShowDate().split("/");
            int getShowDay = Integer.parseInt(split[dayIndex]);

            if (day == getShowDay) {
                ResponseClubShowsGetDTO responseClubShowsGetDTO = exec(clubShowDAO);

                responseClubShowsGetDTOList.add(responseClubShowsGetDTO);
            }

            if (responseClubShowsGetDTOList.isEmpty()){
                return null;
            }
        }
        return responseClubShowsGetDTOList;
    }
}
