package com.example.quotesgreatpeople.mapper;

import com.example.quotesgreatpeople.dto.VoteDto;
import com.example.quotesgreatpeople.entity.QuoteEntity;
import com.example.quotesgreatpeople.entity.UserEntity;
import com.example.quotesgreatpeople.entity.VoteEntity;
import com.example.quotesgreatpeople.repository.QuoteRepository;
import com.example.quotesgreatpeople.repository.UserRepository;
import com.example.quotesgreatpeople.service.QuoteServiceImpl;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import com.example.quotesgreatpeople.service.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteMapper {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public VoteDto toVoteDto(VoteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        return VoteDto.builder()
                .id(entity.getId())
                .isUpvote(entity.isUpvote())
                .quoteId(entity.getQuote().getId())
                .votedUserName(entity.getVotedUser().getName())
                .votedTime(entity.getVotedTime())
                .build();
    }

    public VoteEntity toVoteEntity(VoteDto dto) {
        if (dto == null) {
            return null;
        }
        VoteEntity.VoteEntityBuilder voteEntity = VoteEntity.builder();
        voteEntity.id(dto.getId());
        if (dto.getIsUpvote() == null) {
            throw new NullPointerException("Upvote/downvote couldn't 'null'");
        }
        voteEntity.isUpvote(dto.getIsUpvote());
//        voteEntity.quote(quoteService.getQuoteEntityById(dto.getQuoteId()));

        final Optional<QuoteEntity> optionalQuote = quoteRepository.findById(dto.getQuoteId());
        if (optionalQuote.isEmpty()) {
            throw new NullPointerException("Quote with id='" + dto.getQuoteId() + "' not found.");
        }
        voteEntity.quote(optionalQuote.get());

        final Optional<UserEntity> optionalUser = userRepository.findByName(dto.getVotedUserName());
        if (optionalUser.isEmpty()) {
            throw new NullPointerException("User with id='" + dto.getVotedUserName() + "' not found.");
        }
        voteEntity.votedUser(optionalUser.get());
//        voteEntity.votedUser(userService.getUserEntityByName(dto.getVotedUserName()));
        voteEntity.votedTime(dto.getVotedTime());
        return voteEntity.build();
    }
}
