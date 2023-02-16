package com.example.quotesgreatpeople.serviceTest;

import com.example.quotesgreatpeople.dto.QuoteDto;
import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.dto.VoteDto;
import com.example.quotesgreatpeople.service.QuoteServiceImpl;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import com.example.quotesgreatpeople.service.VoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoteServiceTest {
    @Autowired
    private VoteServiceImpl voteService;

    private static Long quoteIdForCreatedVote;

    @BeforeAll
    static void init(@Autowired QuoteServiceImpl quoteService, @Autowired UserServiceImpl userService) {
        UserDto userIn = UserDto.builder()
                .name("UserVote")
                .email("userVasyaVote@gmail.com")
                .password("1234")
                .build();

        userService.createUser(userIn);

        final QuoteDto quoteIn = QuoteDto.builder()
                .content("Before Content.")
                .createdUserName("UserVote")
                .build();
        final QuoteDto quoteAfterCreated = quoteService.createQuote(quoteIn);
        quoteIdForCreatedVote = quoteAfterCreated.getId();
    }

    @Test
    void createVoteTest() {
        final VoteDto voteIn = VoteDto.builder()
                .isUpvote(true)
                .quoteId(quoteIdForCreatedVote)
                .votedUserName("UserVote")
                .build();

        final boolean vote = voteService.createVote(voteIn);

        assertTrue(vote);
    }
}
