package com.alibou.security.repository;

import com.alibou.security.model.game.UserChoose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChooseRepository extends JpaRepository<UserChoose, Integer> {
}