package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateOpenNightBoothsDTOBean {

    CreateAllNightBoothDTOBean createAllNightBoothDTOBean;

    public CreateOpenNightBoothsDTOBean(CreateAllNightBoothDTOBean createAllNightBoothDTOBean) {
        this.createAllNightBoothDTOBean = createAllNightBoothDTOBean;
    }

    public List<ResponseAllNightBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseAllNightBoothDTO> responseAllNightBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseAllNightBoothDTO responseAllNightBoothDTO = createAllNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen() == true) {
                responseAllNightBoothDAOList.add(responseAllNightBoothDTO);
            }
        }
        return responseAllNightBoothDAOList;
    }
}