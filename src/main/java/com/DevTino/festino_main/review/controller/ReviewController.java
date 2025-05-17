package com.DevTino.festino_main.review.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/review")
public class ReviewController {

    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    // 리뷰 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Object>> getReview(@PathVariable("reviewId") UUID reviewId){

        ResponseReviewGetDTO responseReviewGetDTO = reviewService.getReview(reviewId);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "리뷰 조회 성공", responseReviewGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 리뷰 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getReviews(){
        List<ResponseReviewGetDTO> responseReviewGetDTOList = reviewService.getReviews();

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "리뷰 전체 조회 성공", responseReviewGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 리뷰 저장
    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> saveReview(@RequestBody RequestReviewSaveDTO requestReviewSaveDTO){

        UUID reviewId = reviewService.saveReview(requestReviewSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "리뷰 저장 성공", reviewId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
