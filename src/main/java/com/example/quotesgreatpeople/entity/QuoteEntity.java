package com.example.quotesgreatpeople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Version;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = "voteSet")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "quote_table")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuoteEntity implements BaseEntity<Long> {
    @Id
    @Version
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int votes;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id", nullable = false)
    private UserEntity createdUser;
    @Builder.Default
    @OneToMany(mappedBy = "quote", orphanRemoval = true)
    private Set<VoteEntity> voteSet = new HashSet<>();
//            fetch = FetchType.LAZY,
    //cascade = CascadeType.ALL,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            orphanRemoval = true)


//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        QuoteEntity that = (QuoteEntity) o;
//        return Objects.equals(id, that.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    public void addVote(VoteEntity voteEntity) {
//        voteSet.add(voteEntity);
//        voteEntity.setQuote(this);
//    }
//
//    public void removeVote(VoteEntity voteEntity) {
//        voteSet.remove(voteEntity);
//        voteEntity.setQuote(null);
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
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public int getVotes() {
//        return votes;
//    }
//
//    public void setVotes(int votes) {
//        this.votes = votes;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public LocalDateTime getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(LocalDateTime updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public UserEntity getCreatedUser() {
//        return createdUser;
//    }
//
//    public void setCreatedUser(UserEntity createdUser) {
//        this.createdUser = createdUser;
//    }
//
//    public Set<VoteEntity> getVoteSet() {
//        return voteSet;
//    }
//
//    public void setVoteSet(Set<VoteEntity> voteSet) {
//        this.voteSet = voteSet;
//    }
//
//    @Override
//    public String toString() {
//        return "QuoteEntity{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", votes=" + votes +
//                ", creationDate=" + creationDate +
//                ", updateDate=" + updateDate +
//                ", createdUser=" + createdUser +
//                ", voteSet=" + voteSet +
//                '}';
//    }
}
