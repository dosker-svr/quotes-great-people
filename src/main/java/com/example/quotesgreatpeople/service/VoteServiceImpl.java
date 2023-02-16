package com.example.quotesgreatpeople.service;

import com.example.quotesgreatpeople.dto.VoteDto;
import com.example.quotesgreatpeople.entity.VoteEntity;
import com.example.quotesgreatpeople.mapper.VoteMapper;
import com.example.quotesgreatpeople.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final QuoteServiceImpl quoteService;
    private final VoteMapper voteMapper;

    @Override
    public VoteDto getVoteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("'id' argument to get the vote is 'null'.");
        }

        final Optional<VoteEntity> optEntity = voteRepository.findById(id);
        if (optEntity.isEmpty()) {
            throw new NullPointerException("Vote with id='" + id + "' not found.");
        }
        return voteMapper.toVoteDto(optEntity.get());
    }

    @Transactional
    public boolean createVote(VoteDto voteDto) {
        if (voteDto.getIsUpvote() == null
                || voteDto.getQuoteId() == null
                || voteDto.getVotedUserName() == null
        ) {
            throw new NullPointerException(
                    "Fields 'upvote/downvote', 'quote id', 'voted user name' must be present."
            );
        }
        voteDto.setVotedTime(LocalDateTime.now());
        VoteEntity voteEntity = voteMapper.toVoteEntity(voteDto);
        voteRepository.save(voteEntity);
        quoteService.changeNumberOfVotes(voteEntity);

        return true;
    }
}
