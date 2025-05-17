package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.domain.OrderType;
import com.DevTino.festino_main.order.domain.TableNumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateResponseGetDTOBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    GetCustomTableNumDAOBean getCustomTableNumDAOBean;

    @Autowired
    public CreateResponseGetDTOBean(GetNightBoothDAOBean getNightBoothDAOBean, GetCustomTableNumDAOBean getCustomTableNumDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.getCustomTableNumDAOBean = getCustomTableNumDAOBean;
    }

    // List<OrderDAO> -> List<ResponseOrderGetDTO> 변경
    public List<ResponseOrderGetDTO> exec(List<OrderDTO> orderDTOList) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = new ArrayList<>();

        // 0-입금대기, 1-조리중, 2-조리완료, 3-주문취소
        for(OrderDTO orderDTO : orderDTOList) {
            int orderType = orderDTO.getIsDeposit() ? 1 : 0;

            if(orderType == 0 && orderDTO.getOrderType() == OrderType.CANCEL) {
                orderType = 3;
            }

            if(orderType == 1 && orderDTO.getOrderType() == OrderType.FINISH) {
                orderType = 2;
            }

            NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(orderDTO.getBoothId());

            TableNumDAO tableNumDAO = getCustomTableNumDAOBean.exec(orderDTO.getTableNum(), orderDTO.getBoothId());
            String tableNum = tableNumDAO.getCustomTableNum();

            responseOrderGetDTOList.add(ResponseOrderGetDTO.builder()
                            .adminName(nightBoothDAO.getAdminName())
                            .createAt(orderDTO.getCreateAt())
                            .tableNum(tableNum)
                            .note(orderDTO.getNote())
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
