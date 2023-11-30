package com.alibou.security.controller.game.dto;

import lombok.Data;

@Data
public class GameResultDto {
    private int gameId;
    private int winner;
    private int movesToEnd;
}
