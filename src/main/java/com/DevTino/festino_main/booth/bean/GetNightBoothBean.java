package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.CreateNightBoothDTOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.menu.bean.small.GetMenuDAOBean;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetNightBoothBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    CreateNightBoothDTOBean createNightBoothDTOBean;
    GetMenuDAOBean getMenuDAOBean;

    @Autowired
    public GetNightBoothBean(GetNightBoothDAOBean getNightBoothDAOBean, CreateNightBoothDTOBean createNightBoothDTOBean, GetMenuDAOBean getMenuDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.createNightBoothDTOBean = createNightBoothDTOBean;
        this.getMenuDAOBean = getMenuDAOBean;
    }

    // 야간 부스 조회
    public ResponseNightBoothGetDTO exec(UUID boothId){

        // 야간 부스 디테일 dao 가져오기
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);
        if(nightBoothDAO == null) return null;

        List<MenuDAO> menuDAOList = getMenuDAOBean.exec(boothId);

        // 가져온 dao를 dto로 변환
        return createNightBoothDTOBean.exec(nightBoothDAO, menuDAOList);
    }

}
