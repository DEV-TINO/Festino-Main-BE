package com.DevTino.festino_main.menu.service;

import com.DevTino.festino_main.menu.bean.GetMenusBean;
import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import com.DevTino.festino_main.menu.domain.entity.MenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService {

    GetMenusBean getMenusBean;

    @Autowired
    public MenuService(GetMenusBean getMenusBean){
        this.getMenusBean = getMenusBean;
    }

//    // 부스 별 전체 메뉴 조회
//    public List<ResponseMenuGetDTO> getMenus(UUID boothId){
//        return getMenusBean.exec(boothId);
//    }

    // 부스 별 전체 메뉴 조회
    public List<ResponseMenuGetDTO> getMenus(UUID boothId, String menuType){
        return getMenusBean.exec(boothId, menuType);
    }
}
