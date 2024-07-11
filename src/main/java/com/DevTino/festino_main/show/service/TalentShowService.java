package com.DevTino.festino_main.show.service;

import com.DevTino.festino_main.show.bean.GetTalentShowsBean;
import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentShowService {

    GetTalentShowsBean getTalentShowsBean;

    @Autowired
    public TalentShowService(GetTalentShowsBean getTalentShowsBean){
        this.getTalentShowsBean = getTalentShowsBean;
    }

    // 날짜 별 연예인 타임 테이블 조회
    public List<ResponseTalentShowsGetDTO> getShows(int day){
        return getTalentShowsBean.exec(day);
    }

}
