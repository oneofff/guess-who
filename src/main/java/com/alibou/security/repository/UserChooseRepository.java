package com.alibou.security.repository;

import com.alibou.security.model.game.UserChoose;
import com.alibou.security.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChooseRepository extends JpaRepository<UserChoose, Integer> {
    UserChoose findByUser(User user);
}