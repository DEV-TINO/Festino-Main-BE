package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateNightBoothsDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothsDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNightBoothsBean {

    GetNightBoothsDAOBean getNightBoothsDAOBean;
    CreateNightBoothsDTOBean createNightBoothsDTOBean;

    @Autowired
    public GetNightBoothsBean(GetNightBoothsDAOBean getNightBoothsDAOBean, CreateNightBoothsDTOBean createNightBoothsDTOBean){
        this.getNightBoothsDAOBean = getNightBoothsDAOBean;
        this.createNightBoothsDTOBean = createNightBoothsDTOBean;
    }

    public List<ResponseNightBoothsGetDTO> exec(){
        List<NightBoothDAO> NightBoothDAOList = getNightBoothsDAOBean.exec();

        return createNightBoothsDTOBean.exec(NightBoothDAOList);
    }
}
