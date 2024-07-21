package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateCloseBoothsByNightBoothDTOBean {

    CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean;

    @Autowired
    public CreateCloseBoothsByNightBoothDTOBean(CreateBoothsByNightBoothDTOBean createBoothsByNightBoothDTOBean){
        this.createBoothsByNightBoothDTOBean = createBoothsByNightBoothDTOBean;
    }

    public List<ResponseAllBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseAllBoothDTO> responseNightBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen() != true) {
                responseNightBoothDAOList.add(responseAllBoothDTO);
            }
        }
        return responseNightBoothDAOList;
    }
}
