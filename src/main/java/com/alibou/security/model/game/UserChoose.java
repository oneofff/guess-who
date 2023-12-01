package com.alibou.security.model.game;

import com.alibou.security.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserChoose {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private HairType hairType;

    @Enumerated(EnumType.STRING)
    private HairColor hairColor;

    @Enumerated(EnumType.STRING)
    private SkinColor skinColor; //N

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isWearingHat;
    private Integer hairLength;
    private Integer age;
    private Integer happiness;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public enum HairType {
        KARE,
        PIGTAILS,
        BLAD,
        MALLET,
        BALD_BANGS
    }
    public enum HairColor {
        RED,
        BLUE,
        BLACK,
        BROWNE,
        GINGER,
        GRAY
    }
    public enum SkinColor {
        BLACK,
        SWARTHY,
        WHITE,
        YELLOW,
        PURPLE
    }
    public enum Gender {
        MALE,
        FEMALE
    }
}
