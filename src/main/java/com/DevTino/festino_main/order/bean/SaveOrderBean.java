package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.order.bean.small.CheckOrderDAODateFieldBean;
import com.DevTino.festino_main.order.bean.small.CreateOrderDAOBean;
import com.DevTino.festino_main.order.bean.small.SaveOrderDAOBean;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveOrderBean {

    GetNightBoothDAOBean getNightBoothDAOBean;
    CheckOrderDAODateFieldBean checkOrderDAODateFieldBean;
    CreateOrderDAOBean createOrderDAOBean;
    SaveOrderDAOBean saveOrderDAOBean;

    @Autowired
    public SaveOrderBean(GetNightBoothDAOBean getNightBoothDAOBean, CheckOrderDAODateFieldBean checkOrderDAODateFieldBean, CreateOrderDAOBean createOrderDAOBean, SaveOrderDAOBean saveOrderDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.checkOrderDAODateFieldBean = checkOrderDAODateFieldBean;
        this.createOrderDAOBean = createOrderDAOBean;
        this.saveOrderDAOBean = saveOrderDAOBean;
    }

    // 주문 등록
    public UUID exec(RequestOrderSaveDTO requestOrderSaveDTO) {

        // 부스 정보 조회
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestOrderSaveDTO.getBoothId());
        if (nightBoothDAO == null) return null;

        // 날짜 조회
        Integer date = checkOrderDAODateFieldBean.exec(nightBoothDAO);

        // 주문 정보 생성
        OrderDAO orderDAO = createOrderDAOBean.exec(date, requestOrderSaveDTO);

        // 주문 정보 저장
        saveOrderDAOBean.exec(orderDAO);

        return orderDAO.getOrderId();
    }
}
