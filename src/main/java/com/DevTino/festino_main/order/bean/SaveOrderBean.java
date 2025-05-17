package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.order.bean.small.CheckOrderDAODateFieldBean;
import com.DevTino.festino_main.order.bean.small.CreateOrderDAOBean;
import com.DevTino.festino_main.order.bean.small.GetOrderBoothNameDAOBean;
import com.DevTino.festino_main.order.bean.small.SaveOrderDAOBean;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveOrderBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    CheckOrderDAODateFieldBean checkOrderDAODateFieldBean;
    CreateOrderDAOBean createOrderDAOBean;
    SaveOrderDAOBean saveOrderDAOBean;
    GetOrderBoothNameDAOBean getOrderBoothNameDAOBean;

    @Autowired
    public SaveOrderBean(GetNightBoothDAOBean getNightBoothDAOBean, CheckOrderDAODateFieldBean checkOrderDAODateFieldBean, CreateOrderDAOBean createOrderDAOBean, SaveOrderDAOBean saveOrderDAOBean, GetOrderBoothNameDAOBean getOrderBoothNameDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.checkOrderDAODateFieldBean = checkOrderDAODateFieldBean;
        this.createOrderDAOBean = createOrderDAOBean;
        this.saveOrderDAOBean = saveOrderDAOBean;
        this.getOrderBoothNameDAOBean = getOrderBoothNameDAOBean;
    }

    // 주문 등록
    public UUID exec(RequestOrderSaveDTO requestOrderSaveDTO) {

        // 부스 정보 조회
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestOrderSaveDTO.getBoothId());

        // 부스가 닫혀 있거나 주문 불가할 경우 예외 발생
        if (!nightBoothDAO.getIsOpen()) throw new ServiceException(ExceptionEnum.BOOTH_CLOSED);
        if (!nightBoothDAO.getIsOrder()) throw new ServiceException(ExceptionEnum.ORDER_DISABLED);

        // 날짜 조회
        Integer date = checkOrderDAODateFieldBean.exec(nightBoothDAO);

        // 주문한 학과 조회
        String adminName = getOrderBoothNameDAOBean.exec(requestOrderSaveDTO.getBoothId());
        if(adminName.isEmpty()) throw new ServiceException(ExceptionEnum.INVALID_INPUT_VALUE);

        // 주문 정보 생성
        OrderDTO orderDTO = createOrderDAOBean.exec(date, requestOrderSaveDTO);

        // 주문 정보 저장
        saveOrderDAOBean.exec(adminName, orderDTO);

        return orderDTO.getOrderId();
    }
}
