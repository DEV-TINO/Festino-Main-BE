package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DayBoothRepositoryJPA extends JpaRepository<DayBoothDAO, UUID> {
    List<DayBoothDAO> findAllByOrderByIsOpenAsc();
}