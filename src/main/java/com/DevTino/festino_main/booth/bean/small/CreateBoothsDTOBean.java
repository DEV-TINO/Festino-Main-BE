package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateBoothsDTOBean {

    CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean;
    CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean;
    CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean;

    @Autowired
    public CreateBoothsDTOBean(CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean, CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean, CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean) {
        this.createBoothsByNightBoothDTOBean = createBoothsByNightBoothDTOBean;
        this.createBoothsByDayBoothDTOBean = createBoothsByDayBoothDTOBean;
        this.createBoothsByFoodBoothDTOBean = createBoothsByFoodBoothDTOBean;
    }

    // 전체 부스 DTO 생성
    public List<ResponseAllBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        // 전체
        List<ResponseAllBoothDTO> responseAllBoothDTOList = new ArrayList<>();

        // 운영중, 운영안함
        List<ResponseAllBoothDTO> responseOpenBoothsDTOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseCloseBoothsDTOList = new ArrayList<>();

        // 야간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseAllBoothDTO);
            else
                responseCloseBoothsDTOList.add(responseAllBoothDTO);
        }

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseAllBoothDTO);
            else
                responseCloseBoothsDTOList.add(responseAllBoothDTO);
        }

        // 푸드 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseAllBoothDTO);
            else
                responseCloseBoothsDTOList.add(responseAllBoothDTO);
        }

        // 하나의 리스트로 합치기
        responseAllBoothDTOList.addAll(responseOpenBoothsDTOList);
        responseAllBoothDTOList.addAll(responseCloseBoothsDTOList);

        //전체 리스트 반환 반환
        return responseAllBoothDTOList;
    }
}
