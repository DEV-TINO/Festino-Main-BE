package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetBoothsBean;
import com.DevTino.festino_main.booth.bean.GetDayBoothsBean;
import com.DevTino.festino_main.booth.bean.GetFoodBoothsBean;
import com.DevTino.festino_main.booth.bean.GetNightBoothsBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseBoothsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoothService {

    GetBoothsBean getBoothsBean;
    GetDayBoothsBean getDayBoothsBean;
    GetFoodBoothsBean getFoodBoothsBean;
    GetNightBoothsBean getNightBoothsBean;

    @Autowired
    public BoothService(GetBoothsBean getBoothsBean, GetDayBoothsBean getDayBoothsBean, GetFoodBoothsBean getFoodBoothsBean, GetNightBoothsBean getNightBoothsBean) {
        this.getBoothsBean = getBoothsBean;
        this.getDayBoothsBean = getDayBoothsBean;
        this.getFoodBoothsBean = getFoodBoothsBean;
        this.getNightBoothsBean = getNightBoothsBean;
    }

    // 부스 전체 조회
    public List<ResponseBoothsGetDTO> getBooths(){
        return getBoothsBean.exec();
    }
}