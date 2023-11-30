package com.alibou.security.service;

import com.alibou.security.controller.game.UserStatisticsController;
import com.alibou.security.controller.game.dto.UserStatisticsDto;
import com.alibou.security.model.user.UserStatistics;
import com.alibou.security.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStatisticsService {
    private final UserStatisticsRepository repository;

    public UserStatisticsDto getStatistics(Integer userId) {
        UserStatistics statistics = repository.findByUserId(userId);
        return UserStatisticsDto.builder()
                .wins(statistics.getWinsCount())
                .total(statistics.getTotal())
                .loses(statistics.getTotal() - statistics.getWinsCount())
                .build();
    }
}
