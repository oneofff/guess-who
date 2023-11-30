package com.alibou.security.controller.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStatisticsDto {
    private Integer total;
    private Integer wins;
    private Integer loses;
}
