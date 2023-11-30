package com.alibou.security.controller.game;

import com.alibou.security.controller.game.dto.CreateGameRequest;
import com.alibou.security.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping()
    public ResponseEntity<String> createGame(@RequestBody CreateGameRequest req) {
        return ResponseEntity.ok(gameService.createGame(req));
    }
}
