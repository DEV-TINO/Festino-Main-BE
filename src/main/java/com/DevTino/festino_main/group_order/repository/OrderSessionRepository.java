package com.DevTino.festino_main.group_order.repository;

import com.DevTino.festino_main.group_order.domain.OrderSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSessionRepository extends JpaRepository<OrderSession, String> {
}
