package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateFoodBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseFoodBoothDTO exec(FoodBoothDAO foodBoothDAO){
        return ResponseFoodBoothDTO.builder()
                .boothId(foodBoothDAO.getBoothId())
                .boothName(foodBoothDAO.getBoothName())
                .adminCategory(foodBoothDAO.getAdminCategory())
                .adminName(foodBoothDAO.getAdminName())
                .openTime(foodBoothDAO.getOpenTime())
                .closeTime(foodBoothDAO.getCloseTime())
                .boothIntro(foodBoothDAO.getBoothIntro())
                .boothImage(foodBoothDAO.getBoothImage())
                .location(foodBoothDAO.getLocation())
                .isOpen(foodBoothDAO.getIsOpen())
                .build();
    }
}