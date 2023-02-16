package com.example.quotesgreatpeople.serviceTest;

import com.example.quotesgreatpeople.dto.DetailedQuoteDto;
import com.example.quotesgreatpeople.dto.QuoteDto;
import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.service.QuoteServiceImpl;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuoteServiceTest {
    @Autowired
    private QuoteServiceImpl quoteService;

    private static String createdUserName = "UserQuote";
    private static String createdUserEmail = "userVasyaQuote@gmail.com";

    @BeforeAll
    static void init(@Autowired QuoteServiceImpl quoteService, @Autowired UserServiceImpl userService) {
        UserDto userIn = UserDto.builder()
                .name(createdUserName)
                .email(createdUserEmail)
                .password("1234")
                .build();

        userService.createUser(userIn);

        final QuoteDto quoteIn = QuoteDto.builder()
                .content("Before Content.")
                .createdUserName(createdUserName)
                .build();

        quoteService.createQuote(quoteIn);
        quoteService.createQuote(quoteIn);
        quoteService.createQuote(quoteIn);
        quoteService.createQuote(quoteIn);
    }

    @Test
    void createQuoteTest() {
        final QuoteDto quoteIn = QuoteDto.builder()
                .content("some content.")
                .createdUserName(createdUserName)
                .build();

        final QuoteDto quoteAfter = quoteService.createQuote(quoteIn);

        assertNotNull(quoteAfter);
        assertNotNull(quoteAfter.getCreationDate());
    }

    @Test
    void modifyQuoteTest() {
        final QuoteDto quoteForCreate = QuoteDto.builder()
                .content("Content.")
                .createdUserName(createdUserName)
                .build();
        final QuoteDto quoteAfterCreate = quoteService.createQuote(quoteForCreate);
        final QuoteDto quoteForModify = QuoteDto.builder()
                .id(quoteAfterCreate.getId())
                .content("Another content.")
                .createdUserName("Admin")
                .build();

        final QuoteDto quoteAfterModify = quoteService.modifyQuote(quoteForModify);

        assertEquals(quoteAfterModify.getContent(), quoteForModify.getContent());
        assertEquals(quoteAfterModify.getCreatedUserName(), quoteForModify.getCreatedUserName());
    }

    @Test
    void getQuoteDetailsTest() {
        final QuoteDto quoteForCreate = QuoteDto.builder()
                .content("some content.")
                .createdUserName(createdUserName)
                .build();

        final QuoteDto quoteAfterCreate = quoteService.createQuote(quoteForCreate);

        final DetailedQuoteDto quoteDetails = quoteService.getQuoteDetails(quoteAfterCreate.getId());

        assertInstanceOf(DetailedQuoteDto.class, quoteDetails);
    }

    @Test
    void getRandomQuoteTest() {
        final QuoteDto randomQuote = quoteService.getRandomQuote();

        assertNotNull(randomQuote);
    }
}
