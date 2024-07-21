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

    CreateOpenBoothsByDayBoothDTOBean createOpenBoothsByDayBoothDTOBean;
    CreateCloseBoothsByDayBoothDTOBean createCloseBoothsByDayBoothDTOBean;
    CreateOpenBoothsByFoodBoothDTOBean createOpenBoothsByFoodBoothDTOBean;
    CreateCloseBoothsByFoodBoothDTOBean createCloseBoothsByFoodBoothDTOBean;
    CreateOpenBoothsByNightBoothDTOBean createOpenBoothsByNightBoothDTOBean;
    CreateCloseBoothsByNightBoothDTOBean createCloseBoothsByNightBoothDTOBean;

    @Autowired
    public CreateBoothsDTOBean(CreateOpenBoothsByDayBoothDTOBean createOpenBoothsByDayBoothDTOBean, CreateCloseBoothsByDayBoothDTOBean createCloseBoothsByDayBoothDTOBean, CreateOpenBoothsByFoodBoothDTOBean createOpenBoothsByFoodBoothDTOBean, CreateCloseBoothsByFoodBoothDTOBean createCloseBoothsByFoodBoothDTOBean, CreateOpenBoothsByNightBoothDTOBean createOpenBoothsByNightBoothDTOBean, CreateCloseBoothsByNightBoothDTOBean createCloseBoothsByNightBoothDTOBean) {
        this.createOpenBoothsByDayBoothDTOBean = createOpenBoothsByDayBoothDTOBean;
        this.createCloseBoothsByDayBoothDTOBean = createCloseBoothsByDayBoothDTOBean;
        this.createOpenBoothsByFoodBoothDTOBean = createOpenBoothsByFoodBoothDTOBean;
        this.createCloseBoothsByFoodBoothDTOBean = createCloseBoothsByFoodBoothDTOBean;
        this.createOpenBoothsByNightBoothDTOBean = createOpenBoothsByNightBoothDTOBean;
        this.createCloseBoothsByNightBoothDTOBean = createCloseBoothsByNightBoothDTOBean;
    }

    // 전체 부스 DTO 생성
    public List<ResponseAllBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        // map 생성
//        Map<String, List<ResponseAllBoothDTO>> newMap = new HashMap<>();

        List<ResponseAllBoothDTO> responseAllBoothDTOList = new ArrayList<>();

        // 오픈중인 주간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseOpenDayBoothsDTOList = createOpenBoothsByDayBoothDTOBean.exec(dayBoothDAOList);

        // 오픈이 아닌 주간 부스 전체 리스트 가져오기
        List<ResponseAllBoothDTO> responseCloseDayBoothsDTOList = createCloseBoothsByDayBoothDTOBean.exec(dayBoothDAOList);

        // 오픈중인 푸드트럭 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseOpenFoodBoothsDTOList = createOpenBoothsByFoodBoothDTOBean.exec(foodBoothDAOList);

        // 오픈이 아닌 푸드트럭 부스 전체 리스트 가져오기
        List<ResponseAllBoothDTO> responseCloseFoodBoothsDTOList = createCloseBoothsByFoodBoothDTOBean.exec(foodBoothDAOList);

        // 오픈중인 야간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseOpenNightBoothsDTOList = createOpenBoothsByNightBoothDTOBean.exec(nightBoothDAOList);

        // 오픈이 아닌 야간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseCloseNightBoothsDTOList = createCloseBoothsByNightBoothDTOBean.exec(nightBoothDAOList);


        // 맵에 주간부스, 야간부스, 푸드트럭 전체 리스트 추가
//        newMap.put("dayBoothInfo", responseDayBoothsDTOList);
//        newMap.put("nightBoothInfo", responseNightBoothsDTOList);
//        newMap.put("foodBoothInfo", responseFoodBoothsDTOList);

        // 하나의 리스트로 합치기
        responseAllBoothDTOList.addAll(responseOpenDayBoothsDTOList);
        responseAllBoothDTOList.addAll(responseOpenFoodBoothsDTOList);
        responseAllBoothDTOList.addAll(responseOpenNightBoothsDTOList);
        responseAllBoothDTOList.addAll(responseCloseDayBoothsDTOList);
        responseAllBoothDTOList.addAll(responseCloseFoodBoothsDTOList);
        responseAllBoothDTOList.addAll(responseCloseNightBoothsDTOList);

        //전체 리스트 반환 반환
        return responseAllBoothDTOList;
    }
}
