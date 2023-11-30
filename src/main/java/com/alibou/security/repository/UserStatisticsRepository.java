package com.alibou.security.repository;

import com.alibou.security.model.user.User;
import com.alibou.security.model.user.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Integer> {
    UserStatistics findByUserId(int winner);
}