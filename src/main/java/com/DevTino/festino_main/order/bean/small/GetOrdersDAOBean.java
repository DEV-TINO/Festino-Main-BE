package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.domain.GameOrderDAO;
import com.DevTino.festino_main.order.repository.ComputerOrderRepositoryJPA;
import com.DevTino.festino_main.order.repository.GameOrderRepositoryJPA;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOrdersDAOBean {
    ComputerOrderRepositoryJPA computerOrderRepositoryJPA;
    GameOrderRepositoryJPA gameOrderRepositoryJPA;

    public GetOrdersDAOBean(ComputerOrderRepositoryJPA computerOrderRepositoryJPA, GameOrderRepositoryJPA gameOrderRepositoryJPA) {
        this.computerOrderRepositoryJPA = computerOrderRepositoryJPA;
        this.gameOrderRepositoryJPA = gameOrderRepositoryJPA;
    }

    // 유저의 핸드폰 번호로 입금 완료된 주문 내역들을 조회
    @Transactional(readOnly = true)
    public List<OrderDTO> exec(String userName, String phoneNum) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        // 컴퓨터공학부에서 조회
        List<ComputerOrderDAO> computerOrderDAOList = computerOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for(ComputerOrderDAO computerOrderDAO : computerOrderDAOList) {
            orderDTOList.add(OrderDTO.fromComputerOrderDAO(computerOrderDAO));
        }

        // 게임공학과에서 조회
        List<GameOrderDAO> gameOrderDAOList = gameOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for(GameOrderDAO gameOrderDAO : gameOrderDAOList) {
            orderDTOList.add(OrderDTO.fromGameOrderDAO(gameOrderDAO));
        }

        return orderDTOList;
    }
}
