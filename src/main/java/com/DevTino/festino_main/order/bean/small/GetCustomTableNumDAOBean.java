package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.TableNumDAO;
import com.DevTino.festino_main.order.repository.TableNumRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCustomTableNumDAOBean {

    TableNumRepositoryJPA tableNumRepositoryJPA;

    @Autowired
    public GetCustomTableNumDAOBean(TableNumRepositoryJPA tableNumRepositoryJPA) {
        this.tableNumRepositoryJPA = tableNumRepositoryJPA;
    }

    public TableNumDAO exec(Integer tableNumIndex, UUID boothId) {
        return tableNumRepositoryJPA.findByTableNumIndexAndBoothId(tableNumIndex, boothId);
    }
}
