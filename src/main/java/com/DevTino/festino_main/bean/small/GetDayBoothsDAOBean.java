package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.repository.DayBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDayBoothsDAOBean {

    DayBoothRepositoryJPA jpaDayBoothRepository;

    @Autowired
    public GetDayBoothsDAOBean(DayBoothRepositoryJPA jpaDayBoothRepository){
        this.jpaDayBoothRepository = jpaDayBoothRepository;
    }

    // 주간 부스 리스트 가져오기
    public List<DayBoothDAO> exec(){
        return jpaDayBoothRepository.findAll();
    }
}