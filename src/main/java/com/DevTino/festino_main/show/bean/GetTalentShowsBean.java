package com.DevTino.festino_main.show.bean;

import com.DevTino.festino_main.show.bean.small.CreateTalentShowsDTOBean;
import com.DevTino.festino_main.show.bean.small.GetTalentShowDAOBean;
import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import com.DevTino.festino_main.show.domain.entity.TalentShowDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetTalentShowsBean {

    GetTalentShowDAOBean getTalentShowDAOBean;
    CreateTalentShowsDTOBean createTalentShowsDTOBean;

    @Autowired
    public GetTalentShowsBean(GetTalentShowDAOBean getTalentShowDAOBean, CreateTalentShowsDTOBean createTalentShowsDTOBean){
        this.getTalentShowDAOBean = getTalentShowDAOBean;
        this.createTalentShowsDTOBean = createTalentShowsDTOBean;
    }

    // 날짜 별 동아리 타임 테이블 전체 조회
    public List<ResponseTalentShowsGetDTO> exec(int day){
        // 동아리 타임 테이블 전체 리스트로 가져오기
        List<TalentShowDAO> talentShowDAOList = getTalentShowDAOBean.exec();
        if (talentShowDAOList.isEmpty()) return null;

        // 날짜 별 동아리 타임 테이블 전체 리스트로 반환
        return createTalentShowsDTOBean.exec(talentShowDAOList, day);
    }
}
