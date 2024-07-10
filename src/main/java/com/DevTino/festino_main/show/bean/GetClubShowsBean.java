package com.DevTino.festino_main.show.bean;

import com.DevTino.festino_main.show.bean.small.CreateClubShowsDTOBean;
import com.DevTino.festino_main.show.bean.small.GetClubShowDAOBean;
import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.domain.entity.ClubShowDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetClubShowsBean {

    GetClubShowDAOBean getClubShowDAOBean;
    CreateClubShowsDTOBean createClubShowsDTOBean;

    @Autowired
    public GetClubShowsBean(GetClubShowDAOBean getClubShowDAOBean, CreateClubShowsDTOBean createClubShowsDTOBean){
        this.getClubShowDAOBean = getClubShowDAOBean;
        this.createClubShowsDTOBean = createClubShowsDTOBean;
    }

    public List<ResponseClubShowsGetDTO> exec(int day){
        List<ClubShowDAO> clubShowDAOList = getClubShowDAOBean.exec();

        return createClubShowsDTOBean.exec(clubShowDAOList, day);
    }
}
