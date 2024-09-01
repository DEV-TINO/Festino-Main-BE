package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.*;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.repository.*;
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
    DesignOrderRepositoryJPA designOrderRepositoryJPA;
    MachineOrderRepositoryJPA machineOrderRepositoryJPA;
    ElectronicsOrderRepositoryJPA electronicsOrderRepositoryJPA;
    EnergyOrderRepositoryJPA energyOrderRepositoryJPA;

    public GetOrdersDAOBean(ComputerOrderRepositoryJPA computerOrderRepositoryJPA,
                            GameOrderRepositoryJPA gameOrderRepositoryJPA,
                            NanoOrderRepositoryJPA nanoOrderRepositoryJPA,
                            NewMaterialOrderRepositoryJPA newMaterialOrderRepositoryJPA,
                            DesignOrderRepositoryJPA designOrderRepositoryJPA,
                            MachineOrderRepositoryJPA machineOrderRepositoryJPA,
                            ElectronicsOrderRepositoryJPA electronicsOrderRepositoryJPA,
                            EnergyOrderRepositoryJPA energyOrderRepositoryJPA) {
        this.computerOrderRepositoryJPA = computerOrderRepositoryJPA;
        this.gameOrderRepositoryJPA = gameOrderRepositoryJPA;
        this.nanoOrderRepositoryJPA = nanoOrderRepositoryJPA;
        this.newMaterialOrderRepositoryJPA = newMaterialOrderRepositoryJPA;
        this.designOrderRepositoryJPA = designOrderRepositoryJPA;
        this.machineOrderRepositoryJPA = machineOrderRepositoryJPA;
        this.electronicsOrderRepositoryJPA = electronicsOrderRepositoryJPA;
        this.energyOrderRepositoryJPA = energyOrderRepositoryJPA;
    }

    // 유저의 핸드폰 번호로 입금 완료된 주문 내역들을 조회
    @Transactional(readOnly = true)
    public List<OrderDTO> exec(String userName, String phoneNum) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        // 컴퓨터공학부에서 조회
        List<ComputerOrderDAO> computerOrderDAOList = computerOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (ComputerOrderDAO computerOrderDAO : computerOrderDAOList) {
            orderDTOList.add(OrderDTO.fromComputerOrderDAO(computerOrderDAO));
        }

        // 게임공학과에서 조회
        List<GameOrderDAO> gameOrderDAOList = gameOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (GameOrderDAO gameOrderDAO : gameOrderDAOList) {
            orderDTOList.add(OrderDTO.fromGameOrderDAO(gameOrderDAO));
        }

        // 나노반도체공학과에서 조회
        List<NanoOrderDAO> nanoOrderDAOList = nanoOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (NanoOrderDAO nanoOrderDAO : nanoOrderDAOList) {
            orderDTOList.add(OrderDTO.fromNanoOrderDAO(nanoOrderDAO));
        }

        // 신소재공학과에서 조회
        List<NewMaterialOrderDAO> newMaterialOrderDAOList = newMaterialOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (NewMaterialOrderDAO newMaterialOrderDAO : newMaterialOrderDAOList) {
            orderDTOList.add(OrderDTO.fromNewMaterialOrderDAO(newMaterialOrderDAO));
        }

        // 디자인공학부에서 조회
        List<DesignOrderDAO> designOrderDAOList = designOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (DesignOrderDAO designOrderDAO : designOrderDAOList) {
            orderDTOList.add(OrderDTO.fromDesignOrderDAO(designOrderDAO));
        }

        // 기계공학과에서 조회
        List<MachineOrderDAO> machineOrderDAOList = machineOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (MachineOrderDAO machineOrderDAO : machineOrderDAOList) {
            orderDTOList.add(OrderDTO.fromMachineOrderDAO(machineOrderDAO));
        }

        // 전자공학부에서 조회
        List<ElectronicsOrderDAO> electronicsOrderDAOList = electronicsOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (ElectronicsOrderDAO electronicsOrderDAO : electronicsOrderDAOList) {
            orderDTOList.add(OrderDTO.fromElectronicsOrderDAO(electronicsOrderDAO));
        }

        // 에너지전기공학과에서 조회
        List<EnergyOrderDAO> energyOrderDAOList = energyOrderRepositoryJPA.findAllByUserNameAndPhoneNum(userName, phoneNum);
        for (EnergyOrderDAO energyOrderDAO : energyOrderDAOList) {
            orderDTOList.add(OrderDTO.fromEnergyOrderDAO(energyOrderDAO));
        }

        return orderDTOList;
    }
}
