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
public class GameResult {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game gameId;

    @OneToOne
    private User winner;

    @Column(name = "moves")
    private int movesToEnd;
}
