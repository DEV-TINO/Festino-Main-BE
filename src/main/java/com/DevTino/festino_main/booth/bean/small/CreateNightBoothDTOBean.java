package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.menu.bean.small.CreateMenusDTOBean;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateNightBoothDTOBean {

    CreateMenusDTOBean createMenusDTOBean;

    @Autowired
    public CreateNightBoothDTOBean(CreateMenusDTOBean createMenusDTOBean){
        this.createMenusDTOBean = createMenusDTOBean;
    }

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseNightBoothGetDTO exec(NightBoothDAO nightBoothDAO, List<MenuDAO> menuDAOList) {

        return ResponseNightBoothGetDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .boothName(nightBoothDAO.getBoothName())
                .adminCategory(nightBoothDAO.getAdminCategory())
                .adminName(nightBoothDAO.getAdminName())
                .openTime(nightBoothDAO.getOpenTime())
                .closeTime(nightBoothDAO.getCloseTime())
                .boothIntro(nightBoothDAO.getBoothIntro())
                .boothImage(nightBoothDAO.getBoothImage())
                .isOpen(nightBoothDAO.getIsOpen())
                .isOrder(nightBoothDAO.getIsOrder())
                .isReservation(nightBoothDAO.getIsReservation())
                .totalReservationNum(nightBoothDAO.getTotalReservationNum())
                .menuList(createMenusDTOBean.exec(menuDAOList))
                .markerNum(nightBoothDAO.getMarkerNum())
                .location(nightBoothDAO.getLocation())
                .accountInfo(nightBoothDAO.getAccountInfo())
                .build();
    }
}