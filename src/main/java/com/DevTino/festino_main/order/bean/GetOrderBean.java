package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.order.bean.small.CreateResponseGetDTOBean;
import com.DevTino.festino_main.order.bean.small.GetOrdersDAOBean;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderBean {
    GetOrdersDAOBean getOrdersDAOBean;
    CreateResponseGetDTOBean createResponseGetDTOBean;

    @Autowired
    public GetOrderBean(GetOrdersDAOBean getOrdersDAOBean, CreateResponseGetDTOBean createResponseGetDTOBean) {
        this.getOrdersDAOBean = getOrdersDAOBean;
        this.createResponseGetDTOBean = createResponseGetDTOBean;
    }

    // 주문 내역 조회
    public List<ResponseOrderGetDTO> exec(String userName, String phoneNum) {
        List<OrderDAO> orderDAOList = getOrdersDAOBean.exec(userName, phoneNum);

        return createResponseGetDTOBean.exec(orderDAOList);
    }
}
