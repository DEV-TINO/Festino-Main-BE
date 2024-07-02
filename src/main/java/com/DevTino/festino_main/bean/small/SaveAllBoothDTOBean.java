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

    // 맵 형태로 리스트 반환
    public Map<String, List<ResponseAllBoothDTO>> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        // map 생성
        Map<String, List<ResponseAllBoothDTO>> newMap = new HashMap<>();

        // 주간, 야간, 푸드트럭 빈 리스트 3개 생성
        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseNightBoothDAOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseFoodBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
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
        // 맵에 주간부스 전체 리스트 추가
        newMap.put("dayBoothInfo", responseDayBoothDAOList);

        // 야간 부스 전체 리스트로 가져오기
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
        // 맵에 야간부스 전체 리스트 추가
        newMap.put("nightBoothInfo", responseNightBoothDAOList);

        // 푸드트럭 부스 전체 리스트로 가져오기
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
        // 맵에 푸드트럭 전체 리스트 추가
        newMap.put("foodBoothList", responseFoodBoothDAOList);

        //맵 반환
        return newMap;
    }
}
