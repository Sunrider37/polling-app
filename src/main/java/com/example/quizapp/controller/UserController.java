package com.example.quizapp.controller;

import com.example.quizapp.dto.PagedResponse;
import com.example.quizapp.dto.PollResponse;
import com.example.quizapp.dto.UserProfile;
import com.example.quizapp.dto.UserSummary;
import com.example.quizapp.model.User;
import com.example.quizapp.repo.PollRepository;
import com.example.quizapp.repo.UserRepository;
import com.example.quizapp.repo.VoteRepository;
import com.example.quizapp.security.CurrentUser;
import com.example.quizapp.security.UserPrincipal;
import com.example.quizapp.service.PollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser){
        return new UserSummary(currentUser.getId(),
                currentUser.getUsername(),currentUser.getName());
    }
    @GetMapping("/checkUsernameAvailability")
    public String checkUsernameAvailability(@RequestParam(value = "username") String username){
        boolean isAvailable = !userRepository.existsByUsername(username);
        if(isAvailable){
            return "Is available";
        }
        return "username is not available";
    }

    @GetMapping("/checkEmailAvailability")
    public String checkEmailAvailability(@RequestParam(value = "email") String email){
        boolean isAvailable = !userRepository.existsByEmail(email);
        if(isAvailable){
            return "Is available";
        }
        return "email is not available";
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username){
        User user = userRepository.findByUsername(username).orElseThrow();
        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());
        return new UserProfile(
                user.getId(),user.getUsername(),user.getName(),user.getCreatedAt(),
                pollCount,voteCount
        );
    }

    @GetMapping("/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "30") int size)
    {
        return pollService.getPollsCreatedBy(username,currentUser,page,size);
    }

    @GetMapping("/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(
            @PathVariable String username,
            @CurrentUser UserPrincipal currentUser,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "30") int size
    ){
        return pollService.getPollsVotedBy(username,currentUser,page,size);
    }



}
