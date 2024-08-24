package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.order.bean.small.CreateResponseGetDTOBean;
import com.DevTino.festino_main.order.bean.small.GetOrdersDAOBean;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
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
        List<OrderDTO> orderDTOList = getOrdersDAOBean.exec(userName, phoneNum);

        // createAt을 기준으로 내림차순 정렬
        orderDTOList.sort((o1, o2) -> o2.getCreateAt().compareTo(o1.getCreateAt()));

        return createResponseGetDTOBean.exec(orderDTOList);
    }
}
