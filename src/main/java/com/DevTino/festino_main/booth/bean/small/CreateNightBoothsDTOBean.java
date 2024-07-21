package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateNightBoothsDTOBean {

    CreateAllNightBoothDTOBean createAllNightBoothDTOBean;

    @Autowired
    public CreateNightBoothsDTOBean(CreateAllNightBoothDTOBean createAllNightBoothDTOBean) {
        this.createAllNightBoothDTOBean = createAllNightBoothDTOBean;
    }

    public List<ResponseAllNightBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseAllNightBoothDTO> responseAllNightBoothDTOList = new ArrayList<>();

        List<ResponseAllNightBoothDTO> responseOpenAllNightBoothDTOList = new ArrayList<>();
        List<ResponseAllNightBoothDTO> responseCloseAllNightBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseAllNightBoothDTO responseAllNightBoothDTO = createAllNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen())
                responseOpenAllNightBoothDTOList.add(responseAllNightBoothDTO);
            else
                responseCloseAllNightBoothDTOList.add(responseAllNightBoothDTO);

        }

        responseAllNightBoothDTOList.addAll(responseOpenAllNightBoothDTOList);
        responseAllNightBoothDTOList.addAll(responseCloseAllNightBoothDTOList);

        return responseAllNightBoothDTOList;
    }
}
