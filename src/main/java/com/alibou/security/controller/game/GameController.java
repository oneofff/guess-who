package com.alibou.security.controller.game;

import com.alibou.security.auditing.ApplicationAuditAware;
import com.alibou.security.controller.game.dto.GameResultDto;
import com.alibou.security.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class GameController {
    private final GameService gameService;
    private final ApplicationAuditAware context;

    @PostMapping()
    public ResponseEntity<Integer> createGame() {
        Integer userId = context.getCurrentAuditor().get();
        return ResponseEntity.ok(gameService.createGame(userId));
    }

    @PostMapping("/results")
    public ResponseEntity<Integer> setResult(@RequestBody GameResultDto result){
        return ResponseEntity.ok(gameService.addResult(result));
    }

    @PostMapping("/{id}/connect")
    public ResponseEntity<Void> connectToGame(@PathVariable Integer id){
        Integer userId = context.getCurrentAuditor().get();
        gameService.connectToGame(id, userId);
        return ResponseEntity.ok().build();
    }
}
