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

    public List<ResponseTalentShowsGetDTO> exec(int day){
        List<TalentShowDAO> talentShowDAOList = getTalentShowDAOBean.exec();

        return createTalentShowsDTOBean.exec(talentShowDAOList, day);
    }
}
