package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        // tableNumIndex는 url뒤의 값을 의미함 여기서만

        String url = "https://festino.dev-tino.com/order/" + boothId.toString() + "/" + tableNumIndex;


        TableNumDAO tableNumDAO = getCustomTableNumDAOBean.exec(url);
        if (tableNumDAO == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return tableNumDAO.getCustomTableNum();
    }
}
