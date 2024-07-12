package com.DevTino.festino_main.menu.bean;

import com.DevTino.festino_main.menu.bean.small.CreateMenusDTOBean;
import com.DevTino.festino_main.menu.bean.small.GetMenuDAOBean;
import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetMenusBean {

    GetMenuDAOBean getMenuDAOBean;
    CreateMenusDTOBean createMenusDTOBean;

    @Autowired
    public GetMenusBean(GetMenuDAOBean getMenuDAOBean, CreateMenusDTOBean createMenusDTOBean){
        this.getMenuDAOBean = getMenuDAOBean;
        this.createMenusDTOBean = createMenusDTOBean;
    }

    // 부스 별 메뉴 전체 조회
    public List<ResponseMenuGetDTO> exec(UUID boothId){
        // 메뉴 전체 가져오기
        List<MenuDAO> menuDAO = getMenuDAOBean.exec(boothId);
        if(menuDAO == null) return null;

        return createMenusDTOBean.exec(menuDAO);
    }
}
