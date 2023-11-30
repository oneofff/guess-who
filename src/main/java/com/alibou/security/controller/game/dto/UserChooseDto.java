package com.alibou.security.controller.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserChooseDto {
    private String hairType;
    private String hairColor;
    private String skinColor; //N
    private String gender;
    private boolean isWearingHat;
    private Integer hairLength;
    private Integer age;
    private Integer happiness;
}
