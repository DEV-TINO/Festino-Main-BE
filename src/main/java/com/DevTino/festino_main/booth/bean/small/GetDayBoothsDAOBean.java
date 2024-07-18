package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.repository.DayBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDayBoothsDAOBean {

    DayBoothRepositoryJPA dayBoothRepositoryJPA;

    @Autowired
    public GetDayBoothsDAOBean(DayBoothRepositoryJPA dayBoothRepositoryJPA){
        this.dayBoothRepositoryJPA = dayBoothRepositoryJPA;
    }

    // 주간 부스 리스트 가져오기
    public List<DayBoothDAO> exec(){
        return dayBoothRepositoryJPA.findAllByOrderByIsOpenDesc();
    }
}