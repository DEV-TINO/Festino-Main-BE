package com.DevTino.festino_main.menu.repository;

import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import com.DevTino.festino_main.menu.domain.entity.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuRepositoryJPA extends JpaRepository<MenuDAO, UUID> {
    // 부스 별 메뉴 리스트 가져오기
    List<MenuDAO> findAllByBoothIdOrderByMenuIndexAsc(UUID boothId);

    // 부스, 카테고리 별 메뉴 리스트 가져오기
    List<MenuDAO> findAllByBoothIdAndMenuTypeOrderByMenuIndexAsc(UUID boothId, MenuType menuType);
}
