package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SaveAllBoothDTOBean {

    GetAllDayBoothDAOBean getAllDayBoothDAOBean;
    GetAllNightBoothDAOBean getAllNightBoothDAOBean;
    GetAllFoodBoothDAOBean getAllFoodBoothDAOBean;

    @Autowired
    public SaveAllBoothDTOBean(GetAllDayBoothDAOBean getAllDayBoothDAOBean, GetAllNightBoothDAOBean getAllNightBoothDAOBean, GetAllFoodBoothDAOBean getAllFoodBoothDAOBean){
        this.getAllDayBoothDAOBean = getAllDayBoothDAOBean;
        this.getAllNightBoothDAOBean = getAllNightBoothDAOBean;
        this.getAllFoodBoothDAOBean = getAllFoodBoothDAOBean;
    }

    public Map<String, List<ResponseAllBoothDTO>> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        Map<String, List<ResponseAllBoothDTO>> newMap = new HashMap<>();

        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseNightBoothDAOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseFoodBoothDAOList = new ArrayList<>();

        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {
            ResponseAllBoothDTO responseAllBoothDTO = new ResponseAllBoothDTO();

            responseAllBoothDTO.setBoothId(dayBoothDAO.getBoothId());
            responseAllBoothDTO.setBoothName(dayBoothDAO.getBoothName());
            responseAllBoothDTO.setAdminCategory(dayBoothDAO.getAdminCategory());
            responseAllBoothDTO.setAdminName(dayBoothDAO.getAdminName());
            responseAllBoothDTO.setOpenTime(dayBoothDAO.getOpenTime());
            responseAllBoothDTO.setCloseTime(dayBoothDAO.getCloseTime());
            responseAllBoothDTO.setBoothIntro(dayBoothDAO.getBoothIntro());
            responseAllBoothDTO.setBoothImage(dayBoothDAO.getBoothImage());
            responseAllBoothDTO.setLocation(dayBoothDAO.getLocation());
            responseAllBoothDTO.setIsOpen(dayBoothDAO.getIsOpen());

            responseDayBoothDAOList.add(responseAllBoothDTO);
        }
        newMap.put("dayBoothInfo", responseDayBoothDAOList);
        // [1]
        // day``: [1]

        // [1, 2]
        // day``: [1]
        // dayno : [1,2]

        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {
            ResponseAllBoothDTO responseAllBoothDTO = new ResponseAllBoothDTO();

            responseAllBoothDTO.setBoothId(nightBoothDAO.getBoothId());
            responseAllBoothDTO.setBoothName(nightBoothDAO.getBoothName());
            responseAllBoothDTO.setAdminCategory(nightBoothDAO.getAdminCategory());
            responseAllBoothDTO.setAdminName(nightBoothDAO.getAdminName());
            responseAllBoothDTO.setOpenTime(nightBoothDAO.getOpenTime());
            responseAllBoothDTO.setCloseTime(nightBoothDAO.getCloseTime());
            responseAllBoothDTO.setBoothIntro(nightBoothDAO.getBoothIntro());
            responseAllBoothDTO.setBoothImage(nightBoothDAO.getBoothImage());
            responseAllBoothDTO.setLocation(nightBoothDAO.getLocation());
            responseAllBoothDTO.setIsOpen(nightBoothDAO.getIsOpen());

            responseNightBoothDAOList.add(responseAllBoothDTO);
        }
        newMap.put("nightBoothInfo", responseNightBoothDAOList);

        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {
            ResponseAllBoothDTO responseAllBoothDTO = new ResponseAllBoothDTO();

            responseAllBoothDTO.setBoothId(foodBoothDAO.getBoothId());
            responseAllBoothDTO.setBoothName(foodBoothDAO.getBoothName());
            responseAllBoothDTO.setAdminCategory(foodBoothDAO.getAdminCategory());
            responseAllBoothDTO.setAdminName(foodBoothDAO.getAdminName());
            responseAllBoothDTO.setOpenTime(foodBoothDAO.getOpenTime());
            responseAllBoothDTO.setCloseTime(foodBoothDAO.getCloseTime());
            responseAllBoothDTO.setBoothIntro(foodBoothDAO.getBoothIntro());
            responseAllBoothDTO.setBoothImage(foodBoothDAO.getBoothImage());
            responseAllBoothDTO.setLocation(foodBoothDAO.getLocation());
            responseAllBoothDTO.setIsOpen(foodBoothDAO.getIsOpen());

            responseFoodBoothDAOList.add(responseAllBoothDTO);
        }

        newMap.put("foodBoothList", responseFoodBoothDAOList);

        return newMap;
    }
}
