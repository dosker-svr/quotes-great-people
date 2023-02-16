package com.example.quotesgreatpeople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
import java.util.Objects;

@Data
//@EqualsAndHashCode(of = "id")
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
    @ManyToOne(fetch = FetchType.LAZY)//(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})//(targetEntity = QuoteEntity.class)//(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "quote_id", nullable = false)
    private QuoteEntity quote;
    @ManyToOne(fetch = FetchType.LAZY)//(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "voted_user_id", nullable = false)
    private UserEntity votedUser;
    @Column(nullable = false)
    private LocalDateTime votedTime;
    // TODO: may be Override equals hashCode

    @Override
    public int compareTo(VoteEntity o) {
        int result = (int) (this.id - o.getId());
        if (result == 0) {
            result = this.votedTime.compareTo(o.getVotedTime());
        }
        return result;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        VoteEntity that = (VoteEntity) o;
//        return Objects.equals(id, that.getId());
//    }
//
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (!(o instanceof VoteEntity)) return false;
////        VoteEntity that = (VoteEntity) o;
////        return id.equals(that.id);
////    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public boolean isUpvote() {
//        return isUpvote;
//    }
//
//    public void setUpvote(boolean upvote) {
//        isUpvote = upvote;
//    }
//
//    public QuoteEntity getQuote() {
//        return quote;
//    }
//
    public void setQuote(QuoteEntity quote) {
        this.quote = quote;
        this.quote.getVoteSet().add(this);
    }
//
//    public UserEntity getVotedUser() {
//        return votedUser;
//    }
//
    public void setVotedUser(UserEntity votedUser) {
        this.votedUser = votedUser;
        this.votedUser.getVoteList().add(this);
    }
//
//    public LocalDateTime getVotedTime() {
//        return votedTime;
//    }
//
//    public void setVotedTime(LocalDateTime votedTime) {
//        this.votedTime = votedTime;
//    }
//
//    @Override
//    public String toString() {
//        return "VoteEntity{" +
//                "id=" + id +
//                ", isUpvote=" + isUpvote +
//                ", quote=" + quote +
//                ", votedUser=" + votedUser +
//                ", votedTime=" + votedTime +
//                '}';
//    }
}
