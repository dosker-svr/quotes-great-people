package com.example.quotesgreatpeople.service;

import com.example.quotesgreatpeople.dto.DetailedQuoteDto;
import com.example.quotesgreatpeople.dto.QuoteDto;
import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.dto.VoteDto;
import com.example.quotesgreatpeople.entity.QuoteEntity;
import com.example.quotesgreatpeople.entity.UserEntity;
import com.example.quotesgreatpeople.entity.VoteEntity;

import com.example.quotesgreatpeople.mapper.QuoteMapper;
import com.example.quotesgreatpeople.mapper.UserMapper;
import com.example.quotesgreatpeople.mapper.VoteMapper;
import com.example.quotesgreatpeople.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserServiceImpl userService;
    private final QuoteMapper quoteMapper;
    private final VoteMapper voteMapper;

    public QuoteDto createQuote(QuoteDto quote) {
        if (quote.getCreatedUserName() == null) {
            throw new NullPointerException("Quote couldn't be created. There is no User.");
        }

        quote.setCreationDate(LocalDateTime.now());
        quote.setVotes(0);

        QuoteEntity quoteEntity = quoteMapper.toQuoteEntity(quote);
        if (quoteEntity == null) {
            throw new NullPointerException("An error occurred after mapping quote.");
        }

        return quoteMapper.toQuoteDto(quoteRepository.save(quoteEntity));
    }

    @Override
    public QuoteDto getQuoteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("'id' argument to get the quote is 'null'.");
        }
        final Optional<QuoteEntity> optQuote = quoteRepository.findById(id);
        if (optQuote.isEmpty()) {
            throw new NullPointerException("Quote with id='" + id + "' not found.");
        }

        return quoteMapper.toQuoteDto(optQuote.get());
    }

    public QuoteEntity getQuoteEntityById(Long id) {
        final Optional<QuoteEntity> optionalQuote = quoteRepository.findById(id);
        if (optionalQuote.isEmpty()) {
            throw new NullPointerException("Quote with id='" + id + "' not found.");
        }
        return optionalQuote.get();
    }

    public DetailedQuoteDto getQuoteDetails(Long id) {
        final QuoteEntity quoteEntity = getQuoteEntityById(id);
        final Set<VoteDto> voteDtoSet = quoteEntity.getVoteSet()
                .stream()
                .map(voteMapper::toVoteDto)
                .collect(Collectors.toSet());
        final UserDto userDto = UserMapper.INSTANCE.toUserDto(quoteEntity.getCreatedUser());

        return DetailedQuoteDto.builder()
                .id(quoteEntity.getId())
                .content(quoteEntity.getContent())
                .votes(quoteEntity.getVotes())
                .creationDate(quoteEntity.getCreationDate())
                .updateDate(quoteEntity.getUpdateDate())
                .createdUserName(quoteEntity.getCreatedUser().getName())
                .voteSet(voteDtoSet)
                .createdUser(userDto)
                .build();
    }

    public List<QuoteDto> getAllQuote() {
        final List<QuoteEntity> quoteEntities = quoteRepository.findAll();
        if (quoteEntities.isEmpty()) {
            throw new NullPointerException("There is no Quotes.");
        }

        return quoteEntities.stream()
                .map(quoteMapper::toQuoteDto)
                .collect(Collectors.toList());
    }

    public QuoteDto getRandomQuote() {
        final List<QuoteEntity> quoteEntities = quoteRepository.findAll();
        if (quoteEntities.isEmpty()) {
            throw new NullPointerException("There is no Quotes.");
        }

        Long randomId = (long) ((Math.random() * ((quoteEntities.size() + 1) - 1)) + 1);

        final Optional<QuoteEntity> optQuote = quoteRepository.findById(randomId);
        if (optQuote.isEmpty()) {
            throw new NullPointerException("Quote with id='" + randomId + "' not found.");
        }

        return quoteMapper.toQuoteDto(optQuote.get());
    }

    public List<QuoteDto> getTop10Quotes() {
        final List<QuoteEntity> first10ByVotes = quoteRepository.findFirst3ByOrderByVotesDesc();
        if (first10ByVotes.isEmpty()) {
            throw new NullPointerException("There is no Quotes.");
        }
        final List<QuoteDto> collect = first10ByVotes.stream()
                .map(quoteMapper::toQuoteDto)
                .sorted(Comparator.comparing(QuoteDto::getVotes).reversed())
                .collect(Collectors.toList());

        return collect;
    }

    public List<QuoteDto> getWorst10Quotes() {
        final List<QuoteEntity> first10ByVotes = quoteRepository.findFirst3ByOrderByVotesAsc();
        if (first10ByVotes.isEmpty()) {
            throw new NullPointerException("There is no Quotes.");
        }

        return first10ByVotes.stream()
                .map(quoteMapper::toQuoteDto)
                .sorted(Comparator.comparing(QuoteDto::getVotes))
                .collect(Collectors.toList());
    }

    @Transactional
    public QuoteDto modifyQuote(QuoteDto quoteDto) {
        final Optional<QuoteEntity> optionalEntity = quoteRepository.findById(quoteDto.getId());
        if (optionalEntity.isEmpty()) {
            throw new NullPointerException("Quote with id='" + quoteDto.getId() + "' not found.");
        }
        QuoteEntity quoteEntityForUpdate = optionalEntity.get();

        if (fieldsNotForUpdatingIsModified(quoteDto, quoteEntityForUpdate)) {
            throw new IllegalArgumentException("Trying to update fields not for modifying.");
        }

        QuoteEntity modifiedEntity = quoteRepository.save(
                updateQuoteForModify(quoteDto, quoteEntityForUpdate)
        );
        return quoteMapper.toQuoteDto(modifiedEntity);
    }

    public void deleteQuote(QuoteDto quote) {
        final Long quoteId = quote.getId();
        if (quoteId == null) {
            throw new NullPointerException("For delete Quote, it must have 'id'.");
        }
        if (quoteRepository.findById(quoteId).isEmpty()) {
            throw new NullPointerException("Quote with id='" + quoteId + "' not found.");
        }

        quoteRepository.deleteById(quoteId);
    }

    @Transactional
    public void changeNumberOfVotes(VoteEntity vote) {
        final Long quoteId = vote.getQuote().getId();
        final Optional<QuoteEntity> optionalQuote = quoteRepository.findById(quoteId);
        if (optionalQuote.isEmpty()) {
            throw new NullPointerException("Quote with id='" + quoteId + "' not found.");
        }

        QuoteEntity quote = optionalQuote.get();
        if (vote.isUpvote()) {
            quote.setVotes(quote.getVotes() + 1);
        } else {
            quote.setVotes(quote.getVotes() - 1);
        }
    }

    private QuoteEntity updateQuoteForModify(QuoteDto quoteDto, QuoteEntity quoteEntity) {
        final String dtoContent = quoteDto.getContent();
        final String dtoCreatedUserName = quoteDto.getCreatedUserName();

        // these fields can be update:
        boolean contentIsUpdate = dtoContent != null
                && !dtoContent.equals(quoteEntity.getContent());
        final String createdUserNameFromEntity = quoteEntity.getCreatedUser().getName();
        boolean createdUserIsUpdate = dtoCreatedUserName != null
                && !dtoCreatedUserName.equals(createdUserNameFromEntity);

        if (!contentIsUpdate && !createdUserIsUpdate) {
            throw new IllegalArgumentException("Nothing to update.");
        } else {
            if (contentIsUpdate) {
                quoteEntity.setContent(dtoContent);
            }
            if (createdUserIsUpdate) {
                UserEntity userEntity = userService.getUserEntityByName(createdUserNameFromEntity);
                userEntity.setName(dtoCreatedUserName);
                quoteEntity.setCreatedUser(userEntity);
            }
            quoteEntity.setUpdateDate(LocalDateTime.now());
        }

        return quoteEntity;
    }

    private boolean fieldsNotForUpdatingIsModified(QuoteDto quoteDto, QuoteEntity quoteEntity) {
        // these fields cannot be updated:
        final Integer votes = quoteDto.getVotes();
        final LocalDateTime creationDate = quoteDto.getCreationDate();
        final LocalDateTime updateDate = quoteDto.getUpdateDate();

        if (votes == null && creationDate == null && updateDate == null ) {
            return false;
        } else {
            boolean votesIdUpdate = false;
            if (votes != null) {
                votesIdUpdate = votes != quoteEntity.getVotes();
            }

            boolean creationDateIsUpdate = false;
            if (creationDate != null) {
                creationDateIsUpdate = !quoteEntity.getCreationDate().equals(creationDate);
            }

            boolean updateDateIsUpdate = false;
            if (updateDate != null) {
                updateDateIsUpdate = !quoteEntity.getUpdateDate().equals(updateDate);
            }

            return votesIdUpdate || creationDateIsUpdate || updateDateIsUpdate;
        }
    }
}
