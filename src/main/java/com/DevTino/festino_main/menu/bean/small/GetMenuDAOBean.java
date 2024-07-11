package com.DevTino.festino_main.menu.bean.small;

import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import com.DevTino.festino_main.menu.repository.MenuRepositoryJPA;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetMenuDAOBean {

    MenuRepositoryJPA menuRepositoryJPA;

    @Autowired
    public GetMenuDAOBean(MenuRepositoryJPA menuRepositoryJPA){
        this.menuRepositoryJPA = menuRepositoryJPA;
    }

    // 메뉴 전체 리스트 반환
    public List<MenuDAO> exec(){
        return menuRepositoryJPA.findAll();
    }
}
