package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.order.bean.small.CreateOrderDAOBean;
import com.DevTino.festino_main.order.bean.small.SaveOrderDAOBean;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveOrderBean {
    CreateOrderDAOBean createOrderDAOBean;
    SaveOrderDAOBean saveOrderDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public SaveOrderBean(CreateOrderDAOBean createOrderDAOBean, SaveOrderDAOBean saveOrderDAOBean, GetNightBoothDAOBean getNightBoothDAOBean) {
        this.createOrderDAOBean = createOrderDAOBean;
        this.saveOrderDAOBean = saveOrderDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    // 주문 등록
    public UUID exec(RequestOrderSaveDTO requestOrderSaveDTO) {
        if(getNightBoothDAOBean.exec(requestOrderSaveDTO.getBoothId()) == null) {
            return null;
        }

        OrderDAO orderDAO = createOrderDAOBean.exec(requestOrderSaveDTO);
        saveOrderDAOBean.exec(orderDAO);

        return orderDAO.getOrderId();
    }
}
