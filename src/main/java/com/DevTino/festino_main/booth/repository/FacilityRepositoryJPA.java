package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FacilityRepositoryJPA extends JpaRepository<FacilityDAO, UUID> {
    // 오픈 중, 만든 시간 순으로 모든 편의시설 정렬
    List<FacilityDAO> findAllByOrderByIsOpenDescCreateAtAsc();
}
