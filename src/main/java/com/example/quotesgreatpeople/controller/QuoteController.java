package com.example.quotesgreatpeople.controller;

import com.example.quotesgreatpeople.dto.DetailedQuoteDto;
import com.example.quotesgreatpeople.dto.QuoteDto;
import com.example.quotesgreatpeople.service.QuoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteServiceImpl quoteService;

    @PostMapping
    public QuoteDto createQuote(@RequestBody @Valid QuoteDto quote) {
        return quoteService.createQuote(quote);
    }

    @PutMapping
    public QuoteDto modifyQuote(@RequestBody @Valid QuoteDto quote) {
        return quoteService.modifyQuote(quote);
    }

    @DeleteMapping
    public void deleteQuote(@Valid QuoteDto quote) {
        quoteService.deleteQuote(quote);
    }

    @GetMapping("/details")
    public DetailedQuoteDto getDetailsByQuote(@RequestParam Long id) {
        return quoteService.getQuoteDetails(id);
    }

    @GetMapping("/top10")
    public List<QuoteDto> getTop10Quotes() {
        return quoteService.getTop10Quotes();
    }

    @GetMapping("/worst10")
    public List<QuoteDto> getWorst10Quotes() {
        return quoteService.getWorst10Quotes();
    }

    @GetMapping("/random")
    public QuoteDto getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/all")
    public List<QuoteDto> getAllQuotes() {
        return quoteService.getAllQuote();
    }

}
