package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.TableNumDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableNumRepositoryJPA extends JpaRepository<TableNumDAO, Integer> {

    TableNumDAO findByTableNumIndexAndBoothId(Integer tableNumIndex, UUID boothId);

    TableNumDAO findByOrderUrl(String url);
}
