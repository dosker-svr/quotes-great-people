package com.example.quotesgreatpeople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = {"quoteList", "voteList"})
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Builder.Default
    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY)//, fetch = FetchType.LAZY
    private List<QuoteEntity> quoteList = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "votedUser", fetch = FetchType.LAZY)//, fetch = FetchType.LAZY
    private Set<VoteEntity> voteList = new HashSet<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        UserEntity that = (UserEntity) o;
//        return Objects.equals(id, that.getId());
//    }
//    // && Objects.equals(name, that.name) && Objects.equals(email, that.email)
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//    //, name, email
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
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
//    public List<QuoteEntity> getQuoteList() {
//        return quoteList;
//    }
//
//    public void setQuoteList(List<QuoteEntity> quoteList) {
//        this.quoteList = quoteList;
//    }
//
//    public Set<VoteEntity> getVoteList() {
//        return voteList;
//    }
//
//    public void setVoteList(Set<VoteEntity> voteList) {
//        this.voteList = voteList;
//    }
//
//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", creationDate=" + creationDate +
//                ", quoteList=" + quoteList +
//                ", voteList=" + voteList +
//                '}';
//    }
}
