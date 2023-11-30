package com.alibou.security.controller.game;

import com.alibou.security.auditing.ApplicationAuditAware;
import com.alibou.security.controller.game.dto.UserStatisticsDto;
import com.alibou.security.service.UserStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserStatisticsController {
    private final ApplicationAuditAware context;
    private final UserStatisticsService userStatisticsService;

    @GetMapping
    public ResponseEntity<UserStatisticsDto> getStatistics() {
        Integer userId = context.getCurrentAuditor().get();
        return ResponseEntity.ok(userStatisticsService.getStatistics(userId));
    }
}
