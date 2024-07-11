package com.DevTino.festino_main.show.service;

import com.DevTino.festino_main.show.bean.GetClubShowsBean;
import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubShowService {

    GetClubShowsBean getClubShowsBean;

    @Autowired
    public ClubShowService(GetClubShowsBean getClubShowsBean){
        this.getClubShowsBean = getClubShowsBean;
    }

    // 날짜 별 동아리 타임 테이블 조회
    public List<ResponseClubShowsGetDTO> getShow(int day) {
        return getClubShowsBean.exec(day);
    }
}
