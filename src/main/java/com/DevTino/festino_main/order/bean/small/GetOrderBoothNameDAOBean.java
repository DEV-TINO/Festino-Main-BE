package com.DevTino.festino_main.order.bean.small;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetOrderBoothNameDAOBean {

    // 부스 id로 이름 가져와서 이름 가져오기
    public String exec(UUID boothId) {

        return switch (boothId.toString()) {
            case "bcb6ddc2-1116-4729-a643-fa8f3bb5408f" -> "컴퓨터공학부";
            case "8414328c-f765-4741-bf22-59f5a52e06bf" -> "게임공학과";
            case "3aacd1de-57c1-4685-a94a-63c1664f51bf" -> "신소재공학과";
            case "71ced602-eb69-4e0e-96cd-3bcded85fd76" -> "기계공학과";
            case "5167a573-3a5f-4b40-ab9e-22c8fdf16c84" -> "에너지전기공학과";
            case "f0fe6e61-e530-4606-a003-cf224833b11b" -> "전자공학부";
            case "3d6eda5d-d9fd-427e-9335-61e70754f7fe" -> "나노반도체공학과";
            case "4c0fdc4d-4a3d-4ba7-acc3-0d5d46d7f6f1" -> "메카트로닉스공학부";
            case "a1084779-1d3b-4846-8bf7-64f2731f6bbb" -> "생명화학공학과";
            case "95261985-752a-4c1f-9f4f-9d99d79af561" -> "기계설계공학부";
            case "0b463039-6b58-4461-8ae4-d3c63de799a8" -> "경영학부";
            case "cc958624-20ae-4621-8ea1-d968886d8d6c" -> "디자인공학부";
            default -> "";
        };
    }
}