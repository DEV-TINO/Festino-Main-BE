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

    public List<ResponseMenuGetDTO> exec(UUID boothId){
        List<MenuDAO> menuDAOList = getMenuDAOBean.exec();
        if(menuDAOList.isEmpty()) return null;

        return createMenusDTOBean.exec(menuDAOList, boothId);
    }
}
