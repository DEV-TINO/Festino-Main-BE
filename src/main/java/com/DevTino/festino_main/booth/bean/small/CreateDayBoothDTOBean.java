package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.menu.bean.small.CreateMenusDTOBean;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateDayBoothDTOBean {

    CreateMenusDTOBean createMenusDTOBean;

    @Autowired
    public CreateDayBoothDTOBean(CreateMenusDTOBean createMenusDTOBean){
        this.createMenusDTOBean = createMenusDTOBean;
    }

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseDayBoothGetDTO exec(DayBoothDAO dayBoothDAO, List<MenuDAO> menuDAOList){
        return ResponseDayBoothGetDTO.builder()
                .boothId(dayBoothDAO.getBoothId())
                .boothName(dayBoothDAO.getBoothName())
                .adminCategory(dayBoothDAO.getAdminCategory())
                .adminName(dayBoothDAO.getAdminName())
                .openTime(dayBoothDAO.getOpenTime())
                .closeTime(dayBoothDAO.getCloseTime())
                .boothIntro(dayBoothDAO.getBoothIntro())
                .boothImage(dayBoothDAO.getBoothImage())
                .menuList(createMenusDTOBean.exec(menuDAOList))
                .markerNum(dayBoothDAO.getMarkerNum())
                .location(dayBoothDAO.getLocation())
                .isOpen(dayBoothDAO.getIsOpen())
                .build();
    }
}