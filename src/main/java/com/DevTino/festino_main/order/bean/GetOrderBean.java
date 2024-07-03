package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.order.bean.small.CreateResponseGetDTOBean;
import com.DevTino.festino_main.order.bean.small.GetOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean;
import com.DevTino.festino_main.order.model.DTO.RequestOrderGetDTO;
import com.DevTino.festino_main.order.model.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.model.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrderBean {
    GetOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean getOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean;
    CreateResponseGetDTOBean createResponseGetDTOBean;

    @Autowired
    public GetOrderBean(GetOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean getOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean, CreateResponseGetDTOBean createResponseGetDTOBean) {
        this.getOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean = getOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean;
        this.createResponseGetDTOBean = createResponseGetDTOBean;
    }

    // 주문 내역 조회
    public List<ResponseOrderGetDTO> exec(RequestOrderGetDTO requestOrderGetDTO) {
        List<OrderDAO> orderDAOList = getOrderByUserNameAndPhoneNumOrderByCreateAtDescDAOsBean.exec(requestOrderGetDTO.getUserName(), requestOrderGetDTO.getPhoneNum());

        return createResponseGetDTOBean.exec(orderDAOList);
    }
}
