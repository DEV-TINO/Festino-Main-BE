package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateOpenBoothsByDayBoothDTOBean {

    CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean;

    @Autowired
    public CreateOpenBoothsByDayBoothDTOBean(CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean){
        this.createBoothsByDayBoothDTOBean = createBoothsByDayBoothDTOBean;
    }

    public List<ResponseAllBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList){

        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen() == true) {
                responseDayBoothDAOList.add(responseAllBoothDTO);
            }
        }
        return responseDayBoothDAOList;
    }
}
