package com.alibou.security.service;

import com.alibou.security.controller.game.dto.GameResultDto;
import com.alibou.security.model.game.Game;
import com.alibou.security.model.game.GameResult;
import com.alibou.security.repository.GameRepository;
import com.alibou.security.repository.GameResultRepository;
import com.alibou.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameResultRepository gameResultRepository;
    private final UserRepository userRepository;

    public Integer createGame(Integer userId){
        Game newGame = Game.builder()
                .user1(userRepository.getReferenceById(userId))
                .build();
        return gameRepository.save(newGame).getId();
    }

    public Integer addResult(GameResultDto result) {
        GameResult resultEntity = GameResult.builder()
                .gameId(gameRepository.getReferenceById(result.getGameId()))
                .winner(userRepository.getReferenceById(result.getWinner()))
                .movesToEnd(result.getMovesToEnd())
                .build();
        return gameResultRepository.save(resultEntity).getId();
    }

    public void connectToGame(Integer gameId, Integer userId) {
        Game game = gameRepository.findById(gameId).get();
        game.setUser2(userRepository.getReferenceById(userId));
        gameRepository.save(game);
    }
}
