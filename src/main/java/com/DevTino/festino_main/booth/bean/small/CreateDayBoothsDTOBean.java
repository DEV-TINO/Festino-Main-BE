package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateDayBoothsDTOBean {

    CreateAllDayBoothDTOBean createAllDayBoothDTOBean;

    @Autowired
    public CreateDayBoothsDTOBean(CreateAllDayBoothDTOBean createAllDayBoothDTOBean) {
        this.createAllDayBoothDTOBean = createAllDayBoothDTOBean;
    }

    public List<ResponseDayBoothsGetDTO> exec(List<DayBoothDAO> dayBoothDAOList){

        List<ResponseDayBoothsGetDTO> responseDayBoothsGetDTOList = new ArrayList<>();

        List<ResponseDayBoothsGetDTO> responseOpenAllDayBoothDTOList = new ArrayList<>();
        List<ResponseDayBoothsGetDTO> responseCloseAllDayBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseDayBoothsGetDTO responseDayBoothsGetDTO = createAllDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen())
                responseOpenAllDayBoothDTOList.add(responseDayBoothsGetDTO);
            else
                responseCloseAllDayBoothDTOList.add(responseDayBoothsGetDTO);

        }

        responseDayBoothsGetDTOList.addAll(responseOpenAllDayBoothDTOList);
        responseDayBoothsGetDTOList.addAll(responseCloseAllDayBoothDTOList);

        return responseDayBoothsGetDTOList;
    }
}
