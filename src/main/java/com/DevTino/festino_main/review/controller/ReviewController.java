package com.DevTino.festino_main.review.controller;

import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> getReview(@PathVariable("reviewId") UUID reviewId){

        ResponseReviewGetDTO responseReviewGetDTO = reviewService.getReview(reviewId);

        boolean success = (responseReviewGetDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "리뷰 조회 성공" : "리뷰 조회 실패");
        requestMap.put("boothInfo", responseReviewGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 리뷰 저장
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveReview(@RequestBody RequestReviewSaveDTO requestReviewSaveDTO){

        UUID reviewId = reviewService.saveReview(requestReviewSaveDTO);

        boolean success = (reviewId == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "리뷰 저장 성공" : "리뷰 저장 실패");
        requestMap.put("showInfo", reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

}
