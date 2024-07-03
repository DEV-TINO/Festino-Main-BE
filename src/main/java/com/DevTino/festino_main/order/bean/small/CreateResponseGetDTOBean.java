package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.model.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.model.OrderDAO;
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
                            .menuInfoDTOList(orderDAO.getMenuList())
                            .totalPrice(orderDAO.getTotalPrice())
                            .build());
        }

        return responseOrderGetDTOList;
    }
}
