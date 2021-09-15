package com.example.quizapp.repo;

import com.example.quizapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByIdIn(List<Long> creatorIds);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsernameOrEmail(String username, String username1);
}
