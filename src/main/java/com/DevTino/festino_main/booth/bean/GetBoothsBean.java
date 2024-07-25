package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.*;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBoothsBean {

    GetDayBoothsDAOBean getDayBoothsDAOBean;
    GetNightBoothsDAOBean getNightBoothsDAOBean;
    GetFoodBoothsDAOBean getFoodBoothsDAOBean;
    CreateBoothsDTOBean createBoothsDTOBean;
    GetFacilitiesDAOBean getFacilitiesDAOBean;

    @Autowired
    public GetBoothsBean(GetDayBoothsDAOBean getDayBoothsDAOBean, GetNightBoothsDAOBean getNightBoothsDAOBean, GetFoodBoothsDAOBean getFoodBoothsDAOBean, CreateBoothsDTOBean createBoothsDTOBean, GetFacilitiesDAOBean getFacilitiesDAOBean) {
        this.getDayBoothsDAOBean = getDayBoothsDAOBean;
        this.getNightBoothsDAOBean = getNightBoothsDAOBean;
        this.getFoodBoothsDAOBean = getFoodBoothsDAOBean;
        this.createBoothsDTOBean = createBoothsDTOBean;
        this.getFacilitiesDAOBean = getFacilitiesDAOBean;
    }

    // 주간, 야간, 푸드트럭 전체 리스트 가져온 다음 map으로 반환
    public List<ResponseAllBoothDTO> exec(){

        // 주간, 야간, 푸드트럭 전체 dao 리스트 가져오기
        List<NightBoothDAO> nightBoothDAOList = getNightBoothsDAOBean.exec();
        List<DayBoothDAO> dayBoothDAOList = getDayBoothsDAOBean.exec();
        List<FoodBoothDAO> foodBoothDAOList = getFoodBoothsDAOBean.exec();
        List<FacilityDAO> facilityDAOList = getFacilitiesDAOBean.exec();

        // 가져온 전체 dao 리스트를 전체 dto 리스트로 변환 후 맵에 추가
        return createBoothsDTOBean.exec(nightBoothDAOList, dayBoothDAOList, foodBoothDAOList, facilityDAOList);
    }
}
