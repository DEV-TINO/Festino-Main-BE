package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateBoothsDTOBean {

    CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean;
    CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean;
    CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean;

    @Autowired
    public CreateBoothsDTOBean(CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean, CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean, CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean) {
        this.createBoothsByDayBoothDTOBean = createBoothsByDayBoothDTOBean;
        this.createBoothsByNightBoothDTOBean = createBoothsByNightBoothDTOBean;
        this.createBoothsByFoodBoothDTOBean = createBoothsByFoodBoothDTOBean;
    }

    // 전체 부스 DTO 생성
    public Map<String, List<ResponseAllBoothDTO>> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        // map 생성
        Map<String, List<ResponseAllBoothDTO>> newMap = new HashMap<>();

        // 주간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseDayBoothsDTOList = createBoothsByDayBoothDTOBean.exec(dayBoothDAOList);

        // 야간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseNightBoothsDTOList = createBoothsByNightBoothDTOBean.exec(nightBoothDAOList);

        // 푸드트럭 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseFoodBoothsDTOList = createBoothsByFoodBoothDTOBean.exec(foodBoothDAOList);


        // 맵에 주간부스, 야간부스, 푸드트럭 전체 리스트 추가
        newMap.put("dayBoothInfo", responseDayBoothsDTOList);
        newMap.put("nightBoothInfo", responseNightBoothsDTOList);
        newMap.put("foodBoothList", responseFoodBoothsDTOList);

        //맵 반환
        return newMap;
    }
}
