package com.DevTino.festino_main.event.real.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import com.DevTino.festino_main.event.real.service.RealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/event/real/time")
public class RealTimeController {

    RealTimeService realTimeService;

    @Autowired
    public RealTimeController(RealTimeService realTimeService) {
        this.realTimeService = realTimeService;
    }

    // 문제 조회
    @GetMapping("/question")
    public ResponseEntity<ApiResponse<Object>> getRealTimeQuestion() {

        // 질문 가져오기
        ResponseRealTimeQuestionGetDTO responseRealTimeQuestionGetDTO = realTimeService.getRealTimeQuestion();

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "문제 조회 성공", responseRealTimeQuestionGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 문제 생성
    @PostMapping("/question")
    public ResponseEntity<ApiResponse<Object>> saveRealTimeQuestion(@RequestBody RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){

        // PK값 받아오기
        UUID realTimeId = realTimeService.saveRealTimeQuestion(requestRealTimeQuestionSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "문제 생성 성공", realTimeId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 답안 저장
    @PostMapping("/answer")
    public ResponseEntity<ApiResponse<Object>> saveRealTimeAnswer(@RequestBody RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){

        // PK 값 가져오기
        UUID realTimeParticipantId = realTimeService.saveRealTimeAnswer(requestRealTimeAnswerSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "답안 저장 성공", realTimeParticipantId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 참여 여부 조회
    @GetMapping("/participated/mainUserId/{mainUserId}/realTimeQuestionId/{realTimeQuestionId}")
    public ResponseEntity<ApiResponse<Object>> getRealTimeAlreadyParticipated(@PathVariable("mainUserId") UUID mainUserId, @PathVariable("realTimeQuestionId") UUID realTimeQuestionId){

        // 참여 여부 가져오기
        boolean alreadyParticipated = realTimeService.getRealTimeAlreadyParticipated(mainUserId, realTimeQuestionId);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "참여 여부 조회 성공", alreadyParticipated);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 문제 시간 조회
    @GetMapping("/next/question")
    public ResponseEntity<ApiResponse<Object>> getRealTimeQuestionNextTime() {

        // DTO 가져오기
        ResponseRealTimeQuestionNextTimeGetDTO responseRealTimeQuestionNextTimeGetDTO = realTimeService.getRealTimeQuestionNextTime();

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "문제 시간 조회 성공", responseRealTimeQuestionNextTimeGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
