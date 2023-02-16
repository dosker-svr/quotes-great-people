package com.example.quotesgreatpeople.mapper;

import com.example.quotesgreatpeople.dto.QuoteDto;
import com.example.quotesgreatpeople.entity.QuoteEntity;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteMapper {
    private final UserServiceImpl userService;

    public QuoteDto toQuoteDto(QuoteEntity entity) {
        if (entity == null) {
            return null;
        }

        return QuoteDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .votes(entity.getVotes())
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .createdUserName(entity.getCreatedUser().getName())
                .build();
    }

    public QuoteEntity toQuoteEntity(QuoteDto dto) {
        if (dto == null) {
            return null;
        }

        QuoteEntity.QuoteEntityBuilder quoteEntity = QuoteEntity.builder();

        if (dto.getId() != null) {
            quoteEntity.id(dto.getId());
        }

        if (dto.getContent() != null && !dto.getContent().isEmpty()) {
            quoteEntity.content(dto.getContent());
        }

        if (dto.getVotes() != null) {
            quoteEntity.votes(dto.getVotes());
        }

        if (dto.getCreationDate() != null) {
            quoteEntity.creationDate(dto.getCreationDate());
        }

        if (dto.getUpdateDate() != null) {
            quoteEntity.updateDate(dto.getUpdateDate());
        }

        if (dto.getCreatedUserName() != null) {
            quoteEntity.createdUser(userService.getUserEntityByName(dto.getCreatedUserName()));
        }

        return quoteEntity.build();
    }
}
