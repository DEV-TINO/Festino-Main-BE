package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import com.DevTino.festino_main.order.domain.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateResponseGetDTOBean {
    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public CreateResponseGetDTOBean(GetNightBoothDAOBean getNightBoothDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    // List<OrderDAO> -> List<ResponseOrderGetDTO> 변경
    public List<ResponseOrderGetDTO> exec(List<OrderDAO> orderDAOList) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = new ArrayList<>();

        for(OrderDAO orderDAO : orderDAOList) {
            Integer orderType = orderDAO.getIsDeposit() ? 1 : 0;

            if(orderType == 1 && orderDAO.getOrderType() != OrderType.COOKING) {
                orderType = orderDAO.getOrderType() == OrderType.FINISH ? 2 : 3;
            }

            if(getNightBoothDAOBean.exec(orderDAO.getBoothId()) == null) {
                continue;
            }

            responseOrderGetDTOList.add(ResponseOrderGetDTO.builder()
                            .adminName(getNightBoothDAOBean.exec(orderDAO.getBoothId()).getAdminName())
                            .createAt(orderDAO.getCreateAt())
                            .tableNum(orderDAO.getTableNum())
                            .date(orderDAO.getDate())
                            .orderNum(orderDAO.getOrderNum())
                            .menuInfo(orderDAO.getMenuInfo())
                            .totalPrice(orderDAO.getTotalPrice())
                            .orderType(orderType)
                            .build());
        }

        return responseOrderGetDTOList;
    }
}
