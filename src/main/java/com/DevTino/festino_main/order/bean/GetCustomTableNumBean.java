package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.order.bean.small.GetCustomTableNumDAOBean;
import com.DevTino.festino_main.order.domain.TableNumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCustomTableNumBean {

    GetCustomTableNumDAOBean getCustomTableNumDAOBean;

    @Autowired
    public GetCustomTableNumBean(GetCustomTableNumDAOBean getCustomTableNumDAOBean) {
        this.getCustomTableNumDAOBean = getCustomTableNumDAOBean;
    }

    public String exec(Integer tableNumIndex, UUID boothId) {

        TableNumDAO tableNumDAO = getCustomTableNumDAOBean.exec(tableNumIndex, boothId);

        return tableNumDAO.getCustomTableNum();
    }
}
