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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveOrderDAOBean {
    ComputerOrderRepositoryJPA computerOrderRepositoryJPA;
    GameOrderRepositoryJPA gameOrderRepositoryJPA;
    NanoOrderRepositoryJPA nanoOrderRepositoryJPA;
    NewMaterialOrderRepositoryJPA newMaterialOrderRepositoryJPA;

    @Autowired
    public SaveOrderDAOBean(ComputerOrderRepositoryJPA computerOrderRepositoryJPA, GameOrderRepositoryJPA gameOrderRepositoryJPA, NanoOrderRepositoryJPA nanoOrderRepositoryJPA, NewMaterialOrderRepositoryJPA newMaterialOrderRepositoryJPA) {
        this.computerOrderRepositoryJPA = computerOrderRepositoryJPA;
        this.gameOrderRepositoryJPA = gameOrderRepositoryJPA;
        this.nanoOrderRepositoryJPA = nanoOrderRepositoryJPA;
        this.newMaterialOrderRepositoryJPA = newMaterialOrderRepositoryJPA;
    }

    // 주문 등록
    public void exec(String adminName, OrderDTO orderDTO) {
        switch (adminName) {
            case "컴퓨터공학부":
                ComputerOrderDAO computerOrderDAO = ComputerOrderDAO.fromOrderDTO(orderDTO);
                computerOrderRepositoryJPA.save(computerOrderDAO);
                break;
            case "게임공학과":
                GameOrderDAO gameOrderDAO = GameOrderDAO.fromOrderDTO(orderDTO);
                gameOrderRepositoryJPA.save(gameOrderDAO);
                break;
            case "신소재공학과":
                NewMaterialOrderDAO newMaterialOrderDAO = NewMaterialOrderDAO.fromOrderDTO(orderDTO);
                newMaterialOrderRepositoryJPA.save(newMaterialOrderDAO);
                break;
            case "기계공학과":
                break;
            case "에너지전기공학과":
                break;
            case "전자공학부":
                break;
            case "나노반도체공학과":
                NanoOrderDAO nanoOrderDAO = NanoOrderDAO.fromOrderDTO(orderDTO);
                nanoOrderRepositoryJPA.save(nanoOrderDAO);
                break;
            case "메카트로닉스공학부":
                break;
            case "생명화학공학과":
                break;
            case "기계설계공학부":
                break;
            case "경영학부":
                break;
            case "디자인공학부":
                break;
            default:
                break;
        }
    }
}
