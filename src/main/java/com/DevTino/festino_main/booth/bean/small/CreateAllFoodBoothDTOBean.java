package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateAllFoodBoothDTOBean {
    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseAllFoodBoothDTO exec(FoodBoothDAO foodBoothDAO){
        return ResponseAllFoodBoothDTO.builder()
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
