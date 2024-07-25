package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateFoodBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseFoodBoothGetDTO exec(FoodBoothDAO foodBoothDAO){
        return ResponseFoodBoothGetDTO.builder()
                .boothId(foodBoothDAO.getBoothId())
                .boothName(foodBoothDAO.getBoothName())
                .adminCategory(foodBoothDAO.getAdminCategory())
                .adminName(foodBoothDAO.getAdminName())
                .openTime(foodBoothDAO.getOpenTime())
                .closeTime(foodBoothDAO.getCloseTime())
                .boothIntro(foodBoothDAO.getBoothIntro())
                .boothImage(foodBoothDAO.getBoothImage())
                .markerNum(foodBoothDAO.getMarkerNum())
                .location(foodBoothDAO.getLocation())
                .isOpen(foodBoothDAO.getIsOpen())
                .build();
    }
}