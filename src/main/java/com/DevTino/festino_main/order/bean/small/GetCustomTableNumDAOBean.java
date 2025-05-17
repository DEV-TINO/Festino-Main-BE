package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        TableNumDAO dao = tableNumRepositoryJPA.findByTableNumIndexAndBoothId(tableNumIndex, boothId);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
