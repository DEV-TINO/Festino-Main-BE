package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateDayBoothsDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetDayBoothsDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDayBoothsBean {

    GetDayBoothsDAOBean getDayBoothsDAOBean;
    CreateDayBoothsDTOBean createDayBoothsDTOBean;

    @Autowired
    public GetDayBoothsBean(GetDayBoothsDAOBean getDayBoothsDAOBean, CreateDayBoothsDTOBean createDayBoothsDTOBean){
        this.getDayBoothsDAOBean = getDayBoothsDAOBean;
        this.createDayBoothsDTOBean = createDayBoothsDTOBean;
    }

    public List<ResponseAllDayBoothDTO> exec(){
        List<DayBoothDAO> dayBoothDAOList = getDayBoothsDAOBean.exec();

        return createDayBoothsDTOBean.exec(dayBoothDAOList);
    }
}