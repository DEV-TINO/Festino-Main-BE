package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateResponseGetDTOBean {

    // List<OrderDAO> -> List<ResponseOrderGetDTO> 변경
    public List<ResponseOrderGetDTO> exec(List<OrderDAO> orderDAOList) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = new ArrayList<>();

        for(OrderDAO orderDAO : orderDAOList) {
            responseOrderGetDTOList.add(ResponseOrderGetDTO.builder()
                            .createAt(orderDAO.getCreateAt())
                            .tableNum(orderDAO.getTableNum())
                            .date(orderDAO.getDate())
                            .orderNum(orderDAO.getOrderNum())
                            .menuInfo(orderDAO.getMenuInfo())
                            .totalPrice(orderDAO.getTotalPrice())
                            .build());
        }

        return responseOrderGetDTOList;
    }
}
