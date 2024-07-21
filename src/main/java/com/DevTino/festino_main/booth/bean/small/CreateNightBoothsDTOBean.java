package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateNightBoothsDTOBean {

    CreateOpenNightBoothsDTOBean createOpenNightBoothsDTOBean;
    CreateCloseNightBoothsDTOBean createCloseNightBoothsDTOBean;

    @Autowired
    public CreateNightBoothsDTOBean(CreateOpenNightBoothsDTOBean createOpenNightBoothsDTOBean, CreateCloseNightBoothsDTOBean createCloseNightBoothsDTOBean){
        this.createOpenNightBoothsDTOBean = createOpenNightBoothsDTOBean;
        this.createCloseNightBoothsDTOBean = createCloseNightBoothsDTOBean;
    }
    public List<ResponseAllNightBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){
        List<ResponseAllNightBoothDTO> responseAllNightBoothDTOList = new ArrayList<>();

        List<ResponseAllNightBoothDTO> responseOpenAllNightBoothDTOList = createOpenNightBoothsDTOBean.exec(nightBoothDAOList);
        List<ResponseAllNightBoothDTO> responseCloseAllNightBoothDTOList = createCloseNightBoothsDTOBean.exec(nightBoothDAOList);

        responseAllNightBoothDTOList.addAll(responseOpenAllNightBoothDTOList);
        responseAllNightBoothDTOList.addAll(responseCloseAllNightBoothDTOList);

        return responseAllNightBoothDTOList;
    }
}
