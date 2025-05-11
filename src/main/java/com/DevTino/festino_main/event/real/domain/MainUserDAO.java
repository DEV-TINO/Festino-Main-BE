package com.DevTino.festino_main.event.real.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MainUserDAO {
    @Id
    UUID mainUserId;

}
