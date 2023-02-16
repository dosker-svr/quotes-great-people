package com.example.quotesgreatpeople.controller;

import com.example.quotesgreatpeople.dto.VoteDto;
import com.example.quotesgreatpeople.service.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteServiceImpl voteService;

    @PostMapping
    public Map<String, String> createVote(@RequestBody @Valid VoteDto vote) {
        final boolean voteIsCreated = voteService.createVote(vote);
        if (voteIsCreated) {
            return Map.of("message","Vote has been created successfully.");
        } else {
            return Map.of("message","Vote has not been created.");
        }
    }
}
