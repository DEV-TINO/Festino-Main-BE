package com.DevTino.festino_main.menu.bean;

import com.DevTino.festino_main.menu.bean.small.CreateMenusDTOBean;
import com.DevTino.festino_main.menu.bean.small.GetMenuDAOBean;
import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import com.DevTino.festino_main.menu.domain.entity.MenuType;
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
        List<MenuDAO> menuDAOList = getMenuDAOBean.exec(boothId);
        if(menuDAOList.isEmpty()) return null;

        return createMenusDTOBean.exec(menuDAOList);
    }

    // 카테고리 별 부스 메뉴 전체 조회
    public List<ResponseMenuGetDTO> exec(UUID boothId, String menuType){

        List<MenuDAO> menuDAOList;

        // menuType에 맞춰 메뉴 가져오기
        if ("all".equals(menuType)){
            menuDAOList = getMenuDAOBean.exec(boothId);
        } else {
            MenuType type = MenuType.valueOf(menuType.toUpperCase());
            menuDAOList = getMenuDAOBean.exec(boothId,type);
        }
        if(menuDAOList.isEmpty()) return null;

        // 메뉴 DTO 리스트 반환
        return createMenusDTOBean.exec(menuDAOList);
    }
}
