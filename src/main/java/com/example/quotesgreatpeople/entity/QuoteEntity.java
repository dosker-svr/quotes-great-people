package com.example.quotesgreatpeople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Version;

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
import java.util.HashSet;
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
}
