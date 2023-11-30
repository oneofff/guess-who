package com.alibou.security.controller.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResultResponse {
    private String opponentName;
    private String result;
    private int movesToEnd;
}
