package com.DevTino.festino_main.show.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.show.domain.entity.ClubShowDAO;
import com.DevTino.festino_main.show.repository.ClubShowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetClubShowDAOBean {

    ClubShowRepositoryJPA clubShowRepositoryJPA;

    @Autowired
    public GetClubShowDAOBean(ClubShowRepositoryJPA clubShowRepositoryJPA) {
        this.clubShowRepositoryJPA = clubShowRepositoryJPA;
    }

    // 동아리 전체 리스트로 가져오기
    public List<ClubShowDAO> exec() {

        List<ClubShowDAO> daoList = clubShowRepositoryJPA.findAllByOrderByShowStartTimeAsc();
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}