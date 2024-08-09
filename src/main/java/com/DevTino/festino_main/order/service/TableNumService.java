package com.DevTino.festino_main.order.service;

import com.DevTino.festino_main.order.bean.GetCustomTableNumBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TableNumService {

    GetCustomTableNumBean getCustomTableNumBean;

    @Autowired
    public TableNumService(GetCustomTableNumBean getCustomTableNumBean) {
        this.getCustomTableNumBean = getCustomTableNumBean;
    }

    public String getCustomTableNum(Integer tableNumIndex, UUID boothId) {
        return getCustomTableNumBean.exec(tableNumIndex, boothId);
    }
}
