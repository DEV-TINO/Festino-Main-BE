package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
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
            responseOrderGetDTOList.add(ResponseOrderGetDTO.builder()
                            .adminName(getNightBoothDAOBean.exec(orderDAO.getBoothId()).getAdminName())
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
