package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateAllBoothDTOBean {

    CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean;
    CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean;
    CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean;

    @Autowired
    public CreateAllBoothDTOBean(CreateBoothsByDayBoothDTOBean createBoothsByDayBoothDTOBean, CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean, CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean) {
        this.createBoothsByDayBoothDTOBean = createBoothsByDayBoothDTOBean;
        this.createBoothsByNightBoothDTOBean = createBoothsByNightBoothDTOBean;
        this.createBoothsByFoodBoothDTOBean = createBoothsByFoodBoothDTOBean;
    }

    // 전체 부스 DTO 생성
    public Map<String, List<ResponseAllBoothDTO>> exec(List<DayBoothDAO> dayBoothDAOList, List<NightBoothDAO> nightBoothDAOList, List<FoodBoothDAO> foodBoothDAOList){

        // map 생성
        Map<String, List<ResponseAllBoothDTO>> newMap = new HashMap<>();

        // 주간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseAllDayBoothDTOS = createBoothsByDayBoothDTOBean.exec(dayBoothDAOList);

        // 야간 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseAllNightBoothDTOS = createBoothsByNightBoothDTOBean.exec(nightBoothDAOList);

        // 푸드트럭 부스 전체 리스트로 가져오기
        List<ResponseAllBoothDTO> responseAllFoodBoothDTOS = createBoothsByFoodBoothDTOBean.exec(foodBoothDAOList);


        // 맵에 주간부스, 야간부스, 푸드트럭 전체 리스트 추가
        newMap.put("dayBoothInfo", responseAllDayBoothDTOS);
        newMap.put("nightBoothInfo", responseAllNightBoothDTOS);
        newMap.put("foodBoothList", responseAllFoodBoothDTOS);

        //맵 반환
        return newMap;
    }
}
