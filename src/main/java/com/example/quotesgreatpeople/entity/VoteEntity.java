package com.example.quotesgreatpeople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vote")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteEntity implements Comparable<VoteEntity>, BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isUpvote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    private QuoteEntity quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voted_user_id", nullable = false)
    private UserEntity votedUser;

    @Column(nullable = false)
    private LocalDateTime votedTime;


    @Override
    public int compareTo(VoteEntity o) {
        int result = (int) (this.id - o.getId());
        if (result == 0) {
            result = this.votedTime.compareTo(o.getVotedTime());
        }
        return result;
    }

    public void setQuote(QuoteEntity quote) {
        this.quote = quote;
        this.quote.getVoteSet().add(this);
    }

    public void setVotedUser(UserEntity votedUser) {
        this.votedUser = votedUser;
        this.votedUser.getVoteList().add(this);
    }
}
