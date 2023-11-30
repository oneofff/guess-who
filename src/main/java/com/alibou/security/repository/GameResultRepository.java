package com.alibou.security.repository;

import com.alibou.security.model.game.Game;
import com.alibou.security.model.game.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultRepository extends JpaRepository<GameResult, Integer> {
    GameResult findByGameId(Game gameId);
}