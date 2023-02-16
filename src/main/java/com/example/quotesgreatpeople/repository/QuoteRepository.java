package com.example.quotesgreatpeople.repository;

import com.example.quotesgreatpeople.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    List<QuoteEntity> findFirst3ByOrderByVotesDesc();

    List<QuoteEntity> findFirst3ByOrderByVotesAsc();
}
