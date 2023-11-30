package com.alibou.security.service;

import com.alibou.security.controller.game.dto.CreateGameRequest;
import com.alibou.security.model.game.Game;
import com.alibou.security.repository.GameRepository;
import com.alibou.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public String createGame(CreateGameRequest req){
        Game newGame = Game.builder()
                .user1(userRepository.getReferenceById(req.getUserId1()))
                .user2(userRepository.getReferenceById(req.getUserId2()))
                .build();
        return gameRepository.save(newGame).getId().toString();
    }
}
