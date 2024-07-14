package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateBoothsByFoodBoothDTOBean {
    public ResponseAllBoothDTO exec(FoodBoothDAO foodBoothDAO){
        return ResponseAllBoothDTO.builder()
                .boothId(foodBoothDAO.getBoothId())
                .boothName(foodBoothDAO.getBoothName())
                .adminCategory(foodBoothDAO.getAdminCategory())
                .adminName(foodBoothDAO.getAdminName())
                .openTime(foodBoothDAO.getOpenTime())
                .closeTime(foodBoothDAO.getCloseTime())
                .boothIntro(foodBoothDAO.getBoothIntro())
                .boothImage(foodBoothDAO.getBoothImage().get(0))
                .location(foodBoothDAO.getLocation())
                .isOpen(false)
                .build();
    }

    public List<ResponseAllBoothDTO> exec(List<FoodBoothDAO> foodBoothDAOList){

        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = exec(foodBoothDAO);

            responseDayBoothDAOList.add(responseAllBoothDTO);
        }

        return responseDayBoothDAOList;
    }
}
