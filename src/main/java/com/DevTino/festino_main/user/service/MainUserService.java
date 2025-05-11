package com.DevTino.festino_main.user.service;

import com.DevTino.festino_main.user.bean.SaveMainUserBean;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MainUserService {

    SaveMainUserBean saveMainUserBean;

    @Autowired
    public MainUserService(SaveMainUserBean saveMainUserBean) {
        this.saveMainUserBean = saveMainUserBean;
    }

    // 사용자 정보 저장
    public UUID saveMainUser(RequestMainUserSaveDTO requestMainUserSaveDTO) {
        return saveMainUserBean.exec(requestMainUserSaveDTO);
    }
}
