package com.DevTino.festino_main.event.real.controller;

import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
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
    ResponseEntity<Map<String, Object>> getRealTimeQuestion(){
        String question = realTimeService.getRealTimeQuestion();

        boolean success = (question == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "문제 조회 성공": "문제 조회 실패");
        requestMap.put("question", question);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 문제 생성
    @PostMapping("/question")
    ResponseEntity<Map<String, Object>> saveRealTimeQuestion(@RequestBody RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){

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
    ResponseEntity<Map<String, Object>> saveRealTimeAnswer(@RequestBody RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){

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
    ResponseEntity<Map<String, Object>> getRealTimeAlreadyParticipated(@PathVariable("mainUserId") UUID mainUserId, @PathVariable("realTimeQuestionId") UUID realTimeQuestionId){

        boolean alreadyParticipated = realTimeService.getRealTimeAlreadyParticipated(mainUserId, realTimeQuestionId);

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", alreadyParticipated);
        requestMap.put("message", alreadyParticipated ? "이미 실시간 이벤트에 참여한 사람입니다." : "실시간 이벤트에 참여하지 않은 사람입니다.");

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
