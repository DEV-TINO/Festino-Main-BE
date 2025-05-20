package com.DevTino.festino_main.menu.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import com.DevTino.festino_main.menu.domain.entity.MenuType;
import com.DevTino.festino_main.menu.repository.MenuRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetMenuDAOBean {

    MenuRepositoryJPA menuRepositoryJPA;

    @Autowired
    public GetMenuDAOBean(MenuRepositoryJPA menuRepositoryJPA){
        this.menuRepositoryJPA = menuRepositoryJPA;
    }

    // 메뉴 전체 리스트 반환
    public List<MenuDAO> exec(UUID boothId){

        List<MenuDAO> daoList = menuRepositoryJPA.findAllByBoothIdOrderByMenuIndexAsc(boothId);
//        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }

    // 카테고리 별 메뉴 전체 리스트 반환
    public List<MenuDAO> exec(UUID boothId, MenuType menuType){

        List<MenuDAO> daoList = menuRepositoryJPA.findAllByBoothIdAndMenuTypeOrderByMenuIndexAsc(boothId, menuType);
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}
