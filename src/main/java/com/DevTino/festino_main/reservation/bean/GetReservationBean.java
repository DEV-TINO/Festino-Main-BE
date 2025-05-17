package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CreateReservationDTOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByBoothIdDAOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReservationBean {

    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;
    GetReservationByBoothIdDAOBean getReservationByBoothIdDAOBean;
    CreateReservationDTOBean createReservationDTOBean;

    @Autowired
    public GetReservationBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, GetReservationByBoothIdDAOBean getReservationByBoothIdDAOBean, CreateReservationDTOBean createReservationDTOBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.getReservationByBoothIdDAOBean = getReservationByBoothIdDAOBean;
        this.createReservationDTOBean = createReservationDTOBean;
    }


    // 예약 조회
    public ResponseReservationGetDTO exec(String userName, String phoneNum) {

        // 예약된 내역이 있는지 조회
        ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(userName, phoneNum);

        // 부스에 해당하는 내역 전체 조회 후 인덱싱 매핑
        List<ReservationDAO> reservationDAOList = getReservationByBoothIdDAOBean.exec(reservationDAO.getBoothId());

        // 데이터가 없을 경우 null, 있을 경우 ResponseReservationGetDTO로 변환해서 반환
        return createReservationDTOBean.exec(reservationDAO, reservationDAOList);
    }
}