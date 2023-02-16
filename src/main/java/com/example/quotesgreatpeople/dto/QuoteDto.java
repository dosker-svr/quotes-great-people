package com.example.quotesgreatpeople.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {
    private Long id;
    @NotEmpty
    private String content;
    private Integer votes;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @NotNull
    private String createdUserName;
}
