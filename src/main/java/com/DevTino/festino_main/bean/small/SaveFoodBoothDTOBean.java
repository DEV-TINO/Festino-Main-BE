package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class SaveFoodBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseFoodBoothDTO exec(FoodBoothDAO foodBoothDAO){
        ResponseFoodBoothDTO responseFoodBoothDTO = new ResponseFoodBoothDTO();

        responseFoodBoothDTO.setBoothId(foodBoothDAO.getBoothId());
        responseFoodBoothDTO.setBoothName(foodBoothDAO.getBoothName());
        responseFoodBoothDTO.setAdminCategory(foodBoothDAO.getAdminCategory());
        responseFoodBoothDTO.setAdminName(foodBoothDAO.getAdminName());
        responseFoodBoothDTO.setOpenTime(foodBoothDAO.getOpenTime());
        responseFoodBoothDTO.setCloseTime(foodBoothDAO.getCloseTime());
        responseFoodBoothDTO.setBoothIntro(foodBoothDAO.getBoothIntro());
        responseFoodBoothDTO.setBoothImage(foodBoothDAO.getBoothImage());
        responseFoodBoothDTO.setLocation(foodBoothDAO.getLocation());
        responseFoodBoothDTO.setIsOpen(foodBoothDAO.getIsOpen());

        return responseFoodBoothDTO;
    }
}