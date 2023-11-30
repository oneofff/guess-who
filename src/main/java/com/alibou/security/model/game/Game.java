package com.alibou.security.model.game;

import com.alibou.security.model.token.TokenType;
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
public class Game {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id1")
    private User user1;

    @OneToOne
    @JoinColumn(name = "user_id2")
    private User user2;
}
