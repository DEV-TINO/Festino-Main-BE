package com.DevTino.festino_main.event.photo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PhotoDAO {

    @Id
    private UUID photoId;
    private UUID mainUserId;

    private String mainUserName;
    private String imageUrl;
    private Integer heartCount;
    private LocalDateTime createAt;
}
