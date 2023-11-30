package com.alibou.security.repository;

import com.alibou.security.model.game.Game;
import com.alibou.security.model.token.Token;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByUser1IdOrUser2Id(Integer user1, Integer user2);
}
