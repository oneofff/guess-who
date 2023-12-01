package com.alibou.security.controller.game;

import com.alibou.security.config.auditing.ApplicationAuditAware;
import com.alibou.security.controller.game.dto.GameResultDto;
import com.alibou.security.controller.game.dto.GameResultResponse;
import com.alibou.security.controller.game.dto.UserChooseDto;
import com.alibou.security.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        return ResponseEntity
                .created(URI.create(gameService.createGame(userId).toString()))
                .build();
    }

    @PostMapping("/results")
    public ResponseEntity<Integer> setResult(@RequestBody GameResultDto result) {
        return ResponseEntity.ok(gameService.addResult(result));
    }

    @PostMapping("/{id}/connect")
    public ResponseEntity<Void> connectToGame(@PathVariable Integer id) {
        Integer userId = context.getCurrentAuditor().get();
        gameService.connectToGame(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/results")
    public ResponseEntity<GameResultResponse> getResultOfGame(@PathVariable Integer id) {
        Integer userId = context.getCurrentAuditor().get();
        return ResponseEntity.ok(gameService.getGameResults(id, userId));
    }

    @GetMapping("/results")
    public ResponseEntity<List<GameResultResponse>> getResultsOfUser() {
        Integer userId = context.getCurrentAuditor().get();
        return ResponseEntity.ok(gameService.getListOfUserGames(userId));
    }

    @PostMapping("{id}/chooses")
    public ResponseEntity<Integer> makeChoose(@RequestBody UserChooseDto userChooseDto,
                                              @PathVariable Integer id) {
        Integer userId = context.getCurrentAuditor().get();

        return ResponseEntity
                .created(URI.create(gameService.choose(userChooseDto, id, userId)))
                .build();
    }

    @PostMapping("{id}/guess")
    public ResponseEntity<Boolean> guess(@RequestBody UserChooseDto userChooseDto,
                                         @PathVariable Integer id){
        Integer userId = context.getCurrentAuditor().get();

        return ResponseEntity.ok(gameService.guess(id,userId,userChooseDto));
    }
}
