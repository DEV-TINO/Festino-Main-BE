package com.DevTino.festino_main.event.real.controller;

import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import com.DevTino.festino_main.event.real.service.RealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> getRealTimeQuestion() {

        // 질문 가져오기
        ResponseRealTimeQuestionGetDTO responseRealTimeQuestionGetDTO = realTimeService.getRealTimeQuestion();

        // 퀴즈 조회 성공 여부
        boolean success = (responseRealTimeQuestionGetDTO == null) ? false : true;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "문제 조회 성공" : "문제 조회 실패");
        requestMap.put("responseRealTimeQuestionGetDTO", responseRealTimeQuestionGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 문제 생성
    @PostMapping("/question")
    public ResponseEntity<Map<String, Object>> saveRealTimeQuestion(@RequestBody RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){

        // PK값 받아오기
        UUID realTimeId = realTimeService.saveRealTimeQuestion(requestRealTimeQuestionSaveDTO);

        // 실시간 퀴즈 등록 성공 여부
        boolean success = realTimeId != null;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "실시간 문제 저장 성공" : "실시간 문제 저장 실패");
        requestMap.put("realTimeId", realTimeId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 답안 저장
    @PostMapping("/answer")
    public ResponseEntity<Map<String, Object>> saveRealTimeAnswer(@RequestBody RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){

        // PK 값 가져오기
        UUID realTimeParticipantId = realTimeService.saveRealTimeAnswer(requestRealTimeAnswerSaveDTO);

        // 실시간 퀴즈 등록 성공 여부
        boolean success = realTimeParticipantId != null;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "실시간 문제 답변 저장 성공" : "실시간 문제 답변 저장 실패");
        requestMap.put("realTimeParticipantId", realTimeParticipantId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 참여 여부 조회
    @GetMapping("/participated/mainUserId/{mainUserId}/realTimeQuestionId/{realTimeQuestionId}")
    public ResponseEntity<Map<String, Object>> getRealTimeAlreadyParticipated(@PathVariable("mainUserId") UUID mainUserId, @PathVariable("realTimeQuestionId") UUID realTimeQuestionId){

        // 참여 여부 가져오기
        boolean alreadyParticipated = realTimeService.getRealTimeAlreadyParticipated(mainUserId, realTimeQuestionId);

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", alreadyParticipated);
        requestMap.put("message", alreadyParticipated ? "이미 실시간 이벤트에 참여한 사람입니다." : "실시간 이벤트에 참여하지 않은 사람입니다.");

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 문제 시간 조회
    @GetMapping("/next/question")
    public ResponseEntity<Map<String, Object>> getRealTimeQuestionNextTime() {

        // DTO 가져오기
        ResponseRealTimeQuestionNextTimeGetDTO responseRealTimeQuestionNextTimeGetDTO = realTimeService.getRealTimeQuestionNextTime();

        // DTO 객체 존재 여부
        boolean success = responseRealTimeQuestionNextTimeGetDTO != null;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "문제 시간 조회 성공" : "문제 시간 조회 실패");
        requestMap.put("responseRealTimeQuestionNextTimeGetDTO", responseRealTimeQuestionNextTimeGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
