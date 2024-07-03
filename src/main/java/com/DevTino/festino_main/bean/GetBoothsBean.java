package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetDayBoothsDAOBean;
import com.DevTino.festino_main.bean.small.GetFoodBoothsDAOBean;
import com.DevTino.festino_main.bean.small.GetNightBoothsDAOBean;
import com.DevTino.festino_main.bean.small.CreateBoothsDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GetBoothsBean {

    GetDayBoothsDAOBean getDayBoothsDAOBean;
    GetNightBoothsDAOBean getNightBoothsDAOBean;
    GetFoodBoothsDAOBean getFoodBoothsDAOBean;
    CreateBoothsDTOBean createBoothsDTOBean;

    @Autowired
    public GetBoothsBean(GetDayBoothsDAOBean getDayBoothsDAOBean, GetNightBoothsDAOBean getNightBoothsDAOBean, GetFoodBoothsDAOBean getFoodBoothsDAOBean, CreateBoothsDTOBean createBoothsDTOBean) {
        this.getDayBoothsDAOBean = getDayBoothsDAOBean;
        this.getNightBoothsDAOBean = getNightBoothsDAOBean;
        this.getFoodBoothsDAOBean = getFoodBoothsDAOBean;
        this.createBoothsDTOBean = createBoothsDTOBean;
    }

    // 주간, 야간, 푸드트럭 전체 리스트 가져온 다음 map으로 반환
    public Map<String, List<ResponseAllBoothDTO>> exec(){

        // 주간, 야간, 푸드트럭 전체 dao 리스트 가져오기
        List<DayBoothDAO> dayBoothDAOList = getDayBoothsDAOBean.exec();
        List<NightBoothDAO> nightBoothDAOList = getNightBoothsDAOBean.exec();
        List<FoodBoothDAO> foodBoothDAOList = getFoodBoothsDAOBean.exec();

        // 가져온 전체 dao 리스트를 전체 dto 리스트로 변환 후 맵에 추가
        return createBoothsDTOBean.exec(dayBoothDAOList, nightBoothDAOList, foodBoothDAOList);
    }
}
