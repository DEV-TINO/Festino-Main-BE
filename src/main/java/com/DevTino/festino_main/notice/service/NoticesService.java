package com.DevTino.festino_main.notice.service;

import com.DevTino.festino_main.notice.bean.GetNoticesBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticesService {

    GetNoticesBean getNoticesBean;

    @Autowired
    public NoticesService(GetNoticesBean getNoticesBean){
        this.getNoticesBean = getNoticesBean;
    }

    public List<ResponseNoticesGetDTO> getNotices(){
        return getNoticesBean.exec();
    }
}
