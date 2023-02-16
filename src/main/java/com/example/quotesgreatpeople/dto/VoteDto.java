package com.example.quotesgreatpeople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    // TODO: to delete comments
    private Long id;
    @NotNull
    private Boolean isUpvote;
    @NotNull
    private Long quoteId;
    @NotNull
    private String votedUserName;
    private LocalDateTime votedTime;
}
