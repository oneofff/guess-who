package com.alibou.security.service;

import com.alibou.security.controller.game.dto.GameResultDto;
import com.alibou.security.controller.game.dto.GameResultResponse;
import com.alibou.security.controller.game.dto.UserChooseDto;
import com.alibou.security.model.game.Game;
import com.alibou.security.model.game.GameResult;
import com.alibou.security.model.game.UserChoose;
import com.alibou.security.model.user.User;
import com.alibou.security.model.user.UserStatistics;
import com.alibou.security.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameResultRepository gameResultRepository;
    private final UserRepository userRepository;
    private final UserStatisticsRepository userStatisticsRepository;
    private final UserChooseRepository userChooseRepository;

    public Integer createGame(Integer userId) {
        Game newGame = Game.builder()
                .user1(userRepository.getReferenceById(userId))
                .build();
        return gameRepository.save(newGame).getId();
    }

    @Transactional
    public Integer addResult(GameResultDto result) {
        GameResult resultEntity = GameResult.builder()
                .gameId(gameRepository.getReferenceById(result.getGameId()))
                .winner(userRepository.getReferenceById(result.getWinner()))
                .movesToEnd(result.getMovesToEnd())
                .build();
        updateUserStatistic(result);
        return gameResultRepository.save(resultEntity).getId();
    }

    private void updateUserStatistic(GameResultDto result) {
        Game game = gameRepository.findById(result.getGameId()).get();
        UserStatistics userStatistics1 = userStatisticsRepository.findByUserId(game.getUser1().getId());
        UserStatistics userStatistics2 = userStatisticsRepository.findByUserId(game.getUser2().getId());

        userStatistics1.setTotal(userStatistics1.getTotal() + 1);
        userStatistics2.setTotal(userStatistics2.getTotal() + 1);

        if (userStatistics1.getUser().getId().equals(result.getWinner())) {
            userStatistics1.setWinsCount(userStatistics1.getWinsCount() + 1);
        } else {
            userStatistics2.setWinsCount(userStatistics2.getWinsCount() + 1);
        }
    }

    public void connectToGame(Integer gameId, Integer userId) {
        Game game = gameRepository.findById(gameId).get();
        game.setUser2(userRepository.getReferenceById(userId));
        gameRepository.save(game);
    }

    public GameResultResponse getGameResults(Integer gameId, Integer userId) {
        Game game = gameRepository.findById(gameId).get();
        return getGameResultResponse(userId, game);
    }

    private GameResultResponse getGameResultResponse(Integer userId, Game game) {
        GameResult gameResult = gameResultRepository.findByGameId(game);

        String opponentName = getOpponent(userId, game).getUsername();

        boolean isWinner = gameResult.getWinner().getId().equals(userId);

        return GameResultResponse.builder()
                .opponentName(opponentName)
                .movesToEnd(gameResult.getMovesToEnd())
                .result(isWinner ? "WIN" : "LOSE")
                .build();
    }

    private User getOpponent(Integer userId, Game game) {
        boolean isCreator = game.getUser1().getId().equals(userId);
        return isCreator ?
                userRepository.findById(game.getUser2().getId()).get() :
                userRepository.findById(game.getUser1().getId()).get();
    }

    public List<GameResultResponse> getListOfUserGames(Integer userId) {
        List<Game> allUserGames = gameRepository.findAllByUser1IdOrUser2Id(userId, userId);
        List<GameResultResponse> responseList = new LinkedList<>();
        for (var game : allUserGames) {
            GameResultResponse response = getGameResultResponse(userId, game);
            responseList.add(response);
        }
        return responseList;
    }

    public String choose(UserChooseDto userChooseDto, Integer gameId, Integer userId) {
        UserChoose userChoose = UserChoose.builder()
                .user(userRepository.getReferenceById(userId))
                .hairType(UserChoose.HairType.valueOf(userChooseDto.getHairType().orElse("").toUpperCase()))
                .hairColor(UserChoose.HairColor.valueOf(userChooseDto.getHairColor().orElse("").toUpperCase()))
                .skinColor(UserChoose.SkinColor.valueOf(userChooseDto.getSkinColor().orElse("").toUpperCase()))
                .gender(UserChoose.Gender.valueOf(userChooseDto.getGender().orElse("").toUpperCase()))
                .isWearingHat(userChooseDto.getIsWearingHat().orElse(null))
                .hairLength(userChooseDto.getHairLength().orElse(-1))
                .age(userChooseDto.getAge().orElse(-1))
                .happiness(userChooseDto.getHappiness().orElse(-1))
                .game(gameRepository.getReferenceById(gameId))
                .build();

        return userChooseRepository.save(userChoose).getId().toString();
    }

    public Boolean guess(Integer gameId, Integer userId, UserChooseDto userChooseDto) {
        Game game = gameRepository.findById(gameId).get();
        User opponent = getOpponent(userId, game);
        UserChoose opponentChoose = userChooseRepository.findByUser(opponent);

        return isGuess(opponentChoose, userChooseDto);
    }

    private Boolean isGuess(UserChoose opponentChoose, UserChooseDto userChooseDto) {
        return Objects.equals(opponentChoose.getHairType().name(), userChooseDto.getHairType().orElse("").toUpperCase()) ||
                Objects.equals(opponentChoose.getHairColor().name(), userChooseDto.getHairColor().orElse("").toUpperCase()) ||
                Objects.equals(opponentChoose.getSkinColor().name(), userChooseDto.getSkinColor().orElse("").toUpperCase()) ||
                Objects.equals(opponentChoose.getGender().name(), userChooseDto.getGender().orElse("").toUpperCase()) ||
                Objects.equals(opponentChoose.getIsWearingHat(), userChooseDto.getIsWearingHat().orElse(null)) ||
                Objects.equals(opponentChoose.getHairLength(), userChooseDto.getHairLength().orElse(-5)) ||
                Objects.equals(opponentChoose.getAge(), userChooseDto.getAge().orElse(-5)) ||
                Objects.equals(opponentChoose.getHappiness(), userChooseDto.getHappiness().orElse(-5));
    }

}
