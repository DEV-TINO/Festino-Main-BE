package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetAllDayBoothDAOBean;
import com.DevTino.festino_main.bean.small.GetAllFoodBoothDAOBean;
import com.DevTino.festino_main.bean.small.GetAllNightBoothDAOBean;
import com.DevTino.festino_main.bean.small.SaveAllBoothDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GetAllBoothBean {

    GetAllDayBoothDAOBean getAllDayBoothDAOBean;
    GetAllNightBoothDAOBean getAllNightBoothDAOBean;
    GetAllFoodBoothDAOBean getAllFoodBoothDAOBean;
    SaveAllBoothDTOBean saveAllBoothDTOBean;

    @Autowired
    public GetAllBoothBean(GetAllDayBoothDAOBean getAllDayBoothDAOBean, GetAllNightBoothDAOBean getAllNightBoothDAOBean, GetAllFoodBoothDAOBean getAllFoodBoothDAOBean, SaveAllBoothDTOBean saveAllBoothDTOBean) {
        this.getAllDayBoothDAOBean = getAllDayBoothDAOBean;
        this.getAllNightBoothDAOBean = getAllNightBoothDAOBean;
        this.getAllFoodBoothDAOBean = getAllFoodBoothDAOBean;
        this.saveAllBoothDTOBean = saveAllBoothDTOBean;
    }

    public Map<String, List<ResponseAllBoothDTO>> exec(){

        List<DayBoothDAO> dayBoothDAOList = getAllDayBoothDAOBean.exec();
        List<NightBoothDAO> nightBoothDAOList = getAllNightBoothDAOBean.exec();
        List<FoodBoothDAO> foodBoothDAOList = getAllFoodBoothDAOBean.exec();

        return saveAllBoothDTOBean.exec(dayBoothDAOList, nightBoothDAOList, foodBoothDAOList);
    }
}
