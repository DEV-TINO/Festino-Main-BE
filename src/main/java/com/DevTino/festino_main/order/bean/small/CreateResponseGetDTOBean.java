package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
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
    public List<ResponseOrderGetDTO> exec(List<OrderDTO> orderDTOList) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = new ArrayList<>();

        for(OrderDTO orderDTO : orderDTOList) {
            int orderType = orderDTO.getIsDeposit() ? 1 : 0;

            if(orderType == 1 && orderDTO.getOrderType() != OrderType.COOKING) {
                orderType = orderDTO.getOrderType() == OrderType.FINISH ? 2 : 3;
            }

            NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(orderDTO.getBoothId());
            if (nightBoothDAO == null) continue;

            responseOrderGetDTOList.add(ResponseOrderGetDTO.builder()
                            .adminName(nightBoothDAO.getAdminName())
                            .createAt(orderDTO.getCreateAt())
                            .tableNum(orderDTO.getTableNum())
                            .date(orderDTO.getDate())
                            .orderNum(orderDTO.getOrderNum())
                            .menuInfo(orderDTO.getMenuInfo())
                            .totalPrice(orderDTO.getTotalPrice())
                            .orderType(orderType)
                            .build());
        }

        return responseOrderGetDTOList;
    }
}
