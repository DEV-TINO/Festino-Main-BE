package com.DevTino.festino_main.show.service;

import com.DevTino.festino_main.show.bean.GetClubShowsBean;
import com.DevTino.festino_main.show.bean.GetTalentShowsBean;
import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    GetClubShowsBean getClubShowsBean;
    GetTalentShowsBean getTalentShowsBean;

    @Autowired
    public ShowService(GetClubShowsBean getClubShowsBean, GetTalentShowsBean getTalentShowsBean){
        this.getClubShowsBean = getClubShowsBean;
        this.getTalentShowsBean = getTalentShowsBean;
    }

    // 날짜 별 동아리 타임 테이블 조회
    public List<ResponseClubShowsGetDTO> getClubShow(int day) {
        return getClubShowsBean.exec(day);
    }

    // 날짜 별 연예인 타임 테이블 조회
    public List<ResponseTalentShowsGetDTO> getTalentShow(int day){
        return getTalentShowsBean.exec(day);
    }
}
