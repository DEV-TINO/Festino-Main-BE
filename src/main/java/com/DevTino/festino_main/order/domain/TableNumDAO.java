package com.DevTino.festino_main.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TableNumDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tableNumIndex;
    UUID boothId;
    String customTableNum;
    String orderUrl;
    Integer tablePriority;

    Boolean isDeleted;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}
