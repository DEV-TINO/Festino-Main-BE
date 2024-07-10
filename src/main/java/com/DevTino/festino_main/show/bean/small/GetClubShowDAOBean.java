package com.DevTino.festino_main.show.bean.small;

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

    public List<ClubShowDAO> exec() {
        return clubShowRepositoryJPA.findAll();
    }
}