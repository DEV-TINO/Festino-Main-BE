package com.DevTino.festino_main.event.photo.repository;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotoRepositoryJPA extends JpaRepository<PhotoDAO, UUID> {

    // 사진 타입 별로 정렬해서 가져오기
    List<PhotoDAO> findAllByOrderByCreateAtDesc();

    // 사진 타입 별로 정렬해서 가져오기
    List<PhotoDAO> findAllByOrderByHeartCountDesc();

    // 내 사진 타입 별로 정렬해서 가져오기
    List<PhotoDAO> findAllByMainUserIdOrderByCreateAtDesc(UUID mainUserId);

    // 내 사진 타입 별로 정렬해서 가져오기
    List<PhotoDAO> findAllByMainUserIdOrderByHeartCountDesc(UUID mainUserId);

}
