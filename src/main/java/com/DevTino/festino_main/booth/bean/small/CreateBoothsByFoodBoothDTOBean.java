package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

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
                .isOpen(foodBoothDAO.getIsOpen())
                .build();
    }
}
