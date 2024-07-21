package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
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

    public List<ResponseAllDayBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList){

        List<ResponseAllDayBoothDTO> responseAllDayBoothDTOList = new ArrayList<>();

        List<ResponseAllDayBoothDTO> responseOpenAllDayBoothDTOList = new ArrayList<>();
        List<ResponseAllDayBoothDTO> responseCloseAllDayBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseAllDayBoothDTO responseAllDayBoothDTO = createAllDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen())
                responseOpenAllDayBoothDTOList.add(responseAllDayBoothDTO);
            else
                responseCloseAllDayBoothDTOList.add(responseAllDayBoothDTO);

        }

        responseAllDayBoothDTOList.addAll(responseOpenAllDayBoothDTOList);
        responseAllDayBoothDTOList.addAll(responseCloseAllDayBoothDTOList);

        return responseAllDayBoothDTOList;
    }
}
