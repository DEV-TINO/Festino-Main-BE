package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothsGetDTO;
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

    public List<ResponseNightBoothsGetDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseNightBoothsGetDTO> responseNightBoothsGetDTOList = new ArrayList<>();

        List<ResponseNightBoothsGetDTO> responseOpenAllNightBoothDTOList = new ArrayList<>();
        List<ResponseNightBoothsGetDTO> responseCloseAllNightBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseNightBoothsGetDTO responseNightBoothsGetDTO = createAllNightBoothDTOBean.exec(nightBoothDAO);

            if (nightBoothDAO.getIsOpen())
                responseOpenAllNightBoothDTOList.add(responseNightBoothsGetDTO);
            else
                responseCloseAllNightBoothDTOList.add(responseNightBoothsGetDTO);

        }

        responseNightBoothsGetDTOList.addAll(responseOpenAllNightBoothDTOList);
        responseNightBoothsGetDTOList.addAll(responseCloseAllNightBoothDTOList);

        return responseNightBoothsGetDTOList;
    }
}
