package com.DevTino.festino_main.group_order.repository;

import com.DevTino.festino_main.group_order.domain.GroupOrderMenuDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupOrderMenuRepositoryJPA extends JpaRepository<GroupOrderMenuDAO, Long> {
}
