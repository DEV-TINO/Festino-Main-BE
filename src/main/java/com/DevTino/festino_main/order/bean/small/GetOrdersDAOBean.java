package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.repository.ComputerOrderRepositoryJPA;
import com.DevTino.festino_main.order.repository.OrderRepositoryJPA;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOrdersDAOBean {
    ComputerOrderRepositoryJPA computerOrderRepositoryJPA;

    public GetOrdersDAOBean(ComputerOrderRepositoryJPA computerOrderRepositoryJPA) {
        this.computerOrderRepositoryJPA = computerOrderRepositoryJPA;
    }

    // 유저의 핸드폰 번호로 입금 완료된 주문 내역들을 조회
    @Transactional(readOnly = true)
    public List<OrderDTO> exec(String userName, String phoneNum) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        List<ComputerOrderDAO> computerOrderDAOList = computerOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for(ComputerOrderDAO computerOrderDAO : computerOrderDAOList) {
            orderDTOList.add(OrderDTO.fromComputerOrderDAO(computerOrderDAO));
        }

        return orderDTOList;
    }
}
