package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateDayBoothsDTOBean {

    CreateOpenDayBoothsDTOBean createOpenDayBoothsDTOBean;
    CreateCloseDayBoothsDTOBean createCloseDayBoothsDTOBean;

    @Autowired
    public CreateDayBoothsDTOBean(CreateOpenDayBoothsDTOBean createOpenDayBoothsDTOBean, CreateCloseDayBoothsDTOBean createCloseDayBoothsDTOBean){
        this.createOpenDayBoothsDTOBean = createOpenDayBoothsDTOBean;
        this.createCloseDayBoothsDTOBean = createCloseDayBoothsDTOBean;
    }

    public List<ResponseAllDayBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList){
        List<ResponseAllDayBoothDTO> responseAllDayBoothDTOList = new ArrayList<>();

        List<ResponseAllDayBoothDTO> responseOpenAllDayBoothDTOList = createOpenDayBoothsDTOBean.exec(dayBoothDAOList);
        List<ResponseAllDayBoothDTO> responseCloseAllDayBoothDTOList = createCloseDayBoothsDTOBean.exec(dayBoothDAOList);

        responseAllDayBoothDTOList.addAll(responseOpenAllDayBoothDTOList);
        responseAllDayBoothDTOList.addAll(responseCloseAllDayBoothDTOList);

        return responseAllDayBoothDTOList;
    }
}
