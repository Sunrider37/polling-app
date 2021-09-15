package com.example.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private Long pollCount;
    private Long voteCount;
}
