package com.DevTino.festino_main.show.domain.entity;

import com.DevTino.festino_main.show.others.StringListConvert;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClubShowDAO {
    @Id
    UUID clubId;

    String clubName;
    String showDate;
    String showStartTime;
    String showEndTime;
    String clubImage;
    String clubDescription;
    String instagram;

    @Convert(converter = StringListConvert.class)
    List<Map<String, Object>> musicList;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}
