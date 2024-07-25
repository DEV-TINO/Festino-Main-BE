package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
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
    CreateBoothsByFacilityDTOBean createBoothsByFacilityDTOBean;

    @Autowired
    public CreateBoothsDTOBean(CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean, CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean, CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean, CreateBoothsByFacilityDTOBean createBoothsByFacilityDTOBean) {
        this.createBoothsByNightBoothDTOBean = createBoothsByNightBoothDTOBean;
        this.createBoothsByDayBoothDTOBean = createBoothsByDayBoothDTOBean;
        this.createBoothsByFoodBoothDTOBean = createBoothsByFoodBoothDTOBean;
        this.createBoothsByFacilityDTOBean = createBoothsByFacilityDTOBean;
    }

    // 전체 부스 DTO 생성
    public List<ResponseBoothsGetDTO> exec(List<NightBoothDAO> nightBoothDAOList, List<DayBoothDAO> dayBoothDAOList, List<FoodBoothDAO> foodBoothDAOList, List<FacilityDAO> facilityDAOList){

        // 전체
        List<ResponseBoothsGetDTO> responseBoothsGetDTOList = new ArrayList<>();

        // 운영중, 운영안함
        List<ResponseBoothsGetDTO> responseOpenBoothsDTOList = new ArrayList<>();
        List<ResponseBoothsGetDTO> responseCloseBoothsDTOList = new ArrayList<>();

        // 야간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseBoothsGetDTO responseBoothsGetDTO = createBoothsByNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseBoothsGetDTO);
            else
                responseCloseBoothsDTOList.add(responseBoothsGetDTO);
        }

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseBoothsGetDTO responseBoothsGetDTO = createBoothsByDayBoothDTOBean.exec(dayBoothDAO);

            if (dayBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseBoothsGetDTO);
            else
                responseCloseBoothsDTOList.add(responseBoothsGetDTO);
        }

        // 푸드 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseBoothsGetDTO responseBoothsGetDTO = createBoothsByFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseBoothsGetDTO);
            else
                responseCloseBoothsDTOList.add(responseBoothsGetDTO);
        }

        // 편의시설 전체 리스트로 가져오기
        for (FacilityDAO facilityDAO : facilityDAOList) {

            ResponseBoothsGetDTO responseBoothsGetDTO = createBoothsByFacilityDTOBean.exec(facilityDAO);

            if (facilityDAO.getIsOpen())
                responseOpenBoothsDTOList.add(responseBoothsGetDTO);
            else
                responseCloseBoothsDTOList.add(responseBoothsGetDTO);
        }

        // 하나의 리스트로 합치기
        responseBoothsGetDTOList.addAll(responseOpenBoothsDTOList);
        responseBoothsGetDTOList.addAll(responseCloseBoothsDTOList);

        //전체 리스트 반환 반환
        return responseBoothsGetDTOList;
    }
}
