package com.example.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VoteRequest {

    @NotNull
    private Long choiceId;


}
