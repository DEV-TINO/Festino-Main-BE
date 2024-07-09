package com.DevTino.festino_main.notice.repository;

import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoticeRepositoryJPA extends JpaRepository<NoticeDAO, UUID> {
    //
    List<NoticeDAO> findByOrderByIsPinDescUpdateAtDesc();
}
