package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.booth.repository.NightBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveNightBoothDAOBean {
    NightBoothRepositoryJPA nightBoothRepositoryJPA;

    @Autowired
    public SaveNightBoothDAOBean(NightBoothRepositoryJPA nightBoothRepositoryJPA) {
        this.nightBoothRepositoryJPA = nightBoothRepositoryJPA;
    }

    public void exec(NightBoothDAO nightBoothDAO) {
        nightBoothRepositoryJPA.save(nightBoothDAO);
    }
}
