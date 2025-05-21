package com.DevTino.festino_main.review.domain.entitiy;

import com.DevTino.festino_main.booth.others.StringConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDAO {
    @Id
    UUID reviewId;

    Integer rating;

    @Convert(converter = StringConverter.class)
    List<String> goodFunc;

    @Convert(converter = StringConverter.class)
    List<String> badFunc;

    String reason;
    String reuse;
    String feedback;
    String name;
    String phoneNum;
    String studentNum;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
