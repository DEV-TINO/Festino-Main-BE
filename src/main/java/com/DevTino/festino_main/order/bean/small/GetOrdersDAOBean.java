package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.domain.GameOrderDAO;
import com.DevTino.festino_main.order.domain.NanoOrderDAO;
import com.DevTino.festino_main.order.domain.NewMaterialOrderDAO;
import com.DevTino.festino_main.order.repository.ComputerOrderRepositoryJPA;
import com.DevTino.festino_main.order.repository.GameOrderRepositoryJPA;
import com.DevTino.festino_main.order.repository.NanoOrderRepositoryJPA;
import com.DevTino.festino_main.order.repository.NewMaterialOrderRepositoryJPA;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOrdersDAOBean {
    ComputerOrderRepositoryJPA computerOrderRepositoryJPA;
    GameOrderRepositoryJPA gameOrderRepositoryJPA;
    NanoOrderRepositoryJPA nanoOrderRepositoryJPA;
    NewMaterialOrderRepositoryJPA newMaterialOrderRepositoryJPA;

    public GetOrdersDAOBean(ComputerOrderRepositoryJPA computerOrderRepositoryJPA, GameOrderRepositoryJPA gameOrderRepositoryJPA, NanoOrderRepositoryJPA nanoOrderRepositoryJPA, NewMaterialOrderRepositoryJPA newMaterialOrderRepositoryJPA) {
        this.computerOrderRepositoryJPA = computerOrderRepositoryJPA;
        this.gameOrderRepositoryJPA = gameOrderRepositoryJPA;
        this.nanoOrderRepositoryJPA = nanoOrderRepositoryJPA;
        this.newMaterialOrderRepositoryJPA = newMaterialOrderRepositoryJPA;
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

        // 나노반도체공학과에서 조회
        List<NanoOrderDAO> nanoOrderDAOList = nanoOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for(NanoOrderDAO nanoOrderDAO : nanoOrderDAOList) {
            orderDTOList.add(OrderDTO.fromNanoOrderDAO(nanoOrderDAO));
        }

        // 신소재공학과에서 조회
        List<NewMaterialOrderDAO> newMaterialOrderDAOList = newMaterialOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for(NewMaterialOrderDAO newMaterialOrderDAO : newMaterialOrderDAOList) {
            orderDTOList.add(OrderDTO.fromNewMaterialOrderDAO(newMaterialOrderDAO));
        }

        return orderDTOList;
    }
}
