package com.DevTino.festino_main.group_order.repository;

import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderSessionRepository extends JpaRepository<GroupOrderDAO, String> {
    GroupOrderDAO findByBoothIdAndTableNum(UUID boothId, Integer tableNum);
}
