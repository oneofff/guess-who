package com.alibou.security.controller.game.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class UserChooseDto {
    private Optional<String> hairType;
    private Optional<String> hairColor;
    private Optional<String> skinColor; //N
    private Optional<String> gender;
    private Optional<Boolean> isWearingHat;
    private Optional<Integer> hairLength;
    private Optional<Integer> age;
    private Optional<Integer> happiness;
}
