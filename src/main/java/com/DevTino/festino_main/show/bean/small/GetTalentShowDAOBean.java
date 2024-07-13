package com.DevTino.festino_main.show.bean.small;

import com.DevTino.festino_main.show.domain.entity.TalentShowDAO;
import com.DevTino.festino_main.show.repository.TalentShowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetTalentShowDAOBean {

    TalentShowRepositoryJPA talentShowRepositoryJPA;

    @Autowired
    public GetTalentShowDAOBean(TalentShowRepositoryJPA talentShowRepositoryJPA){
        this.talentShowRepositoryJPA = talentShowRepositoryJPA;
    }

    // 연예인 전체 리스트로 가져오기
    public List<TalentShowDAO> exec(){
        return talentShowRepositoryJPA.findAll();
    }
}
