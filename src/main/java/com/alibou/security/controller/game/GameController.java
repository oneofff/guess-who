package com.alibou.security.controller.game;

import com.alibou.security.controller.game.dto.CreateGameRequest;
import com.alibou.security.controller.game.dto.GameResultDto;
import com.alibou.security.model.game.GameResult;
import com.alibou.security.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping()
    public ResponseEntity<Integer> createGame(@RequestBody CreateGameRequest req) {
        return ResponseEntity.ok(gameService.createGame(req));
    }

    @PostMapping("/results")
    public ResponseEntity<Integer> setResult(@RequestBody GameResultDto result){
        return ResponseEntity.ok(gameService.addResult(result));
    }
}
