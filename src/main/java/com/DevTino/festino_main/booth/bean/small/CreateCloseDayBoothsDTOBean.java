package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateCloseDayBoothsDTOBean {

    CreateAllDayBoothDTOBean createAllDayBoothDTOBean;

    public CreateCloseDayBoothsDTOBean(CreateAllDayBoothDTOBean createAllDayBoothDTOBean) {
        this.createAllDayBoothDTOBean = createAllDayBoothDTOBean;
    }

    public List<ResponseAllDayBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList){

        List<ResponseAllDayBoothDTO> responseAllDayBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseAllDayBoothDTO responseAllDayBoothDTO = createAllDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen() != true) {
                responseAllDayBoothDAOList.add(responseAllDayBoothDTO);
            }
        }
        return responseAllDayBoothDAOList;
    }
}
