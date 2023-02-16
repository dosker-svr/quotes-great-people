package com.example.quotesgreatpeople.service;

import com.example.quotesgreatpeople.dto.QuoteDto;

public interface QuoteService {
    QuoteDto getQuoteById(Long id);
}
